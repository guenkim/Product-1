package com.study.exception;

public class NotRegisterException extends BaseException{

    private static final long serialVersionUID = 1L;

    public NotRegisterException(String systemMessage,Throwable cause, boolean enableSuppression, boolean writableStackTrace , String userMessage){
        super(systemMessage,cause,enableSuppression,writableStackTrace,userMessage);
    }

    public static BaseExceptionBuilder withUserMessage(String strUserMessage){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotRegisterException.class);
        builder.withUserMessage(strUserMessage);
        return builder;
    }


    public static BaseExceptionBuilder withUserMessage(String strUserMessageFormat, Object... objArgs){
        return NotRegisterException.withUserMessage(BaseExceptionBuilder.formatMessage(strUserMessageFormat,objArgs));
    }


    public static BaseExceptionBuilder withUserMessageKey(String strUserMessageKey){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotRegisterException.class);
        builder.withUserMessageKey(strUserMessageKey);
        return builder;
    }


    public static BaseExceptionBuilder withUserMessageKey(String strUserMessageKey , Object... objArgs){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotRegisterException.class);
        builder.withUserMessageKey(strUserMessageKey , objArgs);
        return builder;
    }


    public static BaseExceptionBuilder withSystemMessage(String strSystemMessage){
        BaseExceptionBuilder builder = new BaseExceptionBuilder(NotRegisterException.class);
        builder.withSystemMessage(strSystemMessage);
        return builder;
    }


    public static BaseExceptionBuilder withSystemMessage(String strSystemMessageFormat , Object... objArgs){
        return NotRegisterException.withSystemMessage(BaseExceptionBuilder.formatMessage(strSystemMessageFormat,objArgs));
    }

}
