package com.study.config.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


public class LogDBFilter extends Filter<ILoggingEvent> {

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();


    /**
     * <pre>
     * "==DEBUG_LOG==" 해당 문자열이 포함된 로그 정보 필터 처리
     * </pre>
     * @param iLoggingEvent
     * @return
     *@See ch.qos.logback.core.filter.FILTER#decide(E)
     **/

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        StackTraceElement caller = extractFirstCaller(iLoggingEvent.getCallerData());

        if(iLoggingEvent.getLoggerName().equals("jdbc.sqlonly") || caller.getFileName().equals("BaseJdbcLogger.java") || caller.getFileName().equals("Slf4jSpyLogDelegator.java")){
            if(iLoggingEvent.getLevel().levelInt < Level.ERROR_INT){
                if(iLoggingEvent.getMessage().contains("==DEBUG_LOG==")) {
                    return FilterReply.ACCEPT;
                } else if (iLoggingEvent.getMessage().contains("INSERT INTO logging_event")) {
                    return FilterReply.DENY;
                }else{
                    return FilterReply.NEUTRAL;
                }
            }else{
                return FilterReply.ACCEPT;
            }

        } else if (iLoggingEvent.getLoggerName().equals("springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator")
                || iLoggingEvent.getLoggerName().equals("org.springframework.security.web.DefaultSecurityFilterChain")
                || iLoggingEvent.getLoggerName().equals("org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker")
        ) {
            return FilterReply.DENY;
        }else{
            return FilterReply.NEUTRAL;
        }
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray){
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if(hasAtLeastOneNonNullElement(callerDataArray))
            caller = callerDataArray[0];
        return caller;
    }


    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }
}
