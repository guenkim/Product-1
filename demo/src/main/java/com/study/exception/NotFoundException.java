package com.study.exception;


import org.slf4j.helpers.MessageFormatter;


/**

 Not found 유형의 오류

 client에 response 될 때에는 http status code 404로 리턴 됨

 **/

public class NotFoundException extends BaseException{

    private static final long serialVersionUID = 1L;

    public NotFoundException(String systemMessage,Throwable cause, boolean enableSuppression, boolean writableStackTrace , String userMessage){
        super(systemMessage,cause,enableSuppression,writableStackTrace,userMessage);
    }


    public static BaseExceptionBuilder withUserMessage(String strUserMessage){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotFoundException.class);
        builder.withUserMessage(strUserMessage);
        return builder;
    }


    public static BaseExceptionBuilder withUserMessage(String strUserMessageFormat, Object... objArgs){
        return NotFoundException.withUserMessage(BaseExceptionBuilder.formatMessage(strUserMessageFormat,objArgs));
    }


    public static BaseExceptionBuilder withUserMessageKey(String strUserMessageKey){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotFoundException.class);
        builder.withUserMessageKey(strUserMessageKey);
        return builder;
    }

    public static BaseExceptionBuilder withUserMessageKey(String strUserMessageKey , Object... objArgs){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotFoundException.class);
        builder.withUserMessageKey(strUserMessageKey , objArgs);
        return builder;
    }


    public static BaseExceptionBuilder withSystemMessage(String strSystemMessage){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotFoundException.class);
        builder.withSystemMessage(strSystemMessage);
        return builder;
    }

    public static BaseExceptionBuilder withSystemMessage(String strSystemMessageFormat , Object... objArgs){
        return NotFoundException.withSystemMessage(BaseExceptionBuilder.formatMessage(strSystemMessageFormat,objArgs));
    }

}
