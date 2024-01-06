package com.study.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.study.component.ApplicationContextProvider;
import com.study.dto.MessageDisplayType;
public class BaseExceptionBuilder {
    private String userMessage;
    private String userMessageKey;
    private Object[] userMessageArgs;
    private String systemMessage;
    private Throwable cause;
    private boolean enableSuppression;
    private boolean writableStackTrace;
    private MessageDisplayType displayType;
    private boolean forcesOk = false;
    private String code;
    private Class<?> exceptionType;
    private MessageSource messageSource;
    public BaseExceptionBuilder(Class<?> exceptionType){
        this.exceptionType = exceptionType;
        this.messageSource = ApplicationContextProvider.getApplicationContext().getBean("messageSource" , MessageSource.class);
    }
    public BaseExceptionBuilder withUserMessage(String strUserMeassage){
        this.userMessage = strUserMeassage;
        return this;
    }

    public BaseExceptionBuilder withUserMessage(String strUserMessageFormat , Object... objArgs){
        String msg = MessageFormatter.arrayFormat(strUserMessageFormat,objArgs).getMessage();
        return this.withUserMessage(msg);
    }

    public BaseExceptionBuilder withUserMessageKey(String strUserMessageKey){
        return this.withUserMessageKey(strUserMessageKey , (Object[]) null);
    }
    public BaseExceptionBuilder withUserMessageKey(String strUserMessageKey , Object... objArgs){
        this.userMessageKey = strUserMessageKey;
        this.userMessageArgs = objArgs;
        return this;
    }
    public BaseExceptionBuilder withSystemMessage(String strSystemMeassage){
        this.systemMessage = strSystemMeassage;
        return this;
    }
    public BaseExceptionBuilder withSystemMessage(String strSystemMeassageFormat , Object... objArgs){
        return this.withSystemMessage(formatMessage(strSystemMeassageFormat,objArgs));
    }
    public BaseExceptionBuilder withCause(Throwable cause){
        this.cause = cause;
        return this;
    }
    public BaseExceptionBuilder withEnableSuppression(boolean enableSuppression){
        this.enableSuppression = enableSuppression;
        return this;
    }
    public BaseExceptionBuilder withWritableStackTrace(boolean writableStackTrace){
        this.writableStackTrace = writableStackTrace;
        return this;
    }
    public BaseExceptionBuilder withDisplayType(MessageDisplayType displayType){
        this.displayType = displayType;
        return this;
    }
    public BaseExceptionBuilder withFocusOK(boolean isForcusOK){
        this.forcesOk = isForcusOK;
        return this;
    }
    public BaseExceptionBuilder withCode(String strCode){
        this.code = strCode;
        return this;
    }

    public BaseException build(){
        if(StringUtils.isNoneBlank(this.userMessageKey)){
            this.userMessage = messageSource.getMessage(this.userMessageKey , this.userMessageArgs, LocaleContextHolder.getLocale());
        }

        if(StringUtils.isBlank(this.userMessage)){
            if(exceptionType.equals(NotFoundException.class)){
                this.userMessage = messageSource.getMessage("찾을수 없음" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(ValidationException.class)){
                this.userMessage = messageSource.getMessage("validation 오류" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(UnauthorizedException.class)){
                this.userMessage = messageSource.getMessage("인증되지 않음(로그인 요망)" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(ForbiddenException.class)){
                this.userMessage = messageSource.getMessage("권한이 없음" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(BizException.class)){
                this.userMessage = messageSource.getMessage("업무 중 에러" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(InterfaceException.class)){
                this.userMessage = messageSource.getMessage("인터페이스 오류" , null , LocaleContextHolder.getLocale());
            }else if(exceptionType.equals(NotRegisterException.class)){
                this.userMessage = messageSource.getMessage("미등록 되어 있음" , null , LocaleContextHolder.getLocale());
            }
        }

        BaseException be = null;
        if(exceptionType.equals(NotFoundException.class)) {
            be = new NotFoundException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else if(exceptionType.equals(ValidationException.class)) {
            be = new ValidationException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else if(exceptionType.equals(UnauthorizedException.class)) {
            be = new UnauthorizedException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else if(exceptionType.equals(ForbiddenException.class)) {
            be = new ForbiddenException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else if(exceptionType.equals(BizException.class)) {
            be = new BizException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
            ((BizException) be).setForcesOK(this.forcesOk);
        }else if(exceptionType.equals(InterfaceException.class)) {
            be = new InterfaceException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else if(exceptionType.equals(NotRegisterException.class)) {
            be = new NotRegisterException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }else {
            be = new BaseException(this.systemMessage
                    ,this.cause
                    , this.enableSuppression
                    , this.writableStackTrace
                    , this.userMessage);
        }
        be.setDisplayType(displayType);
        be.setCode(code);
        return be;
    }

    public static String formatMessage(String strFormat,Object... objArgs){
        return MessageFormatter.arrayFormat(strFormat,objArgs).getMessage();
    }
}
