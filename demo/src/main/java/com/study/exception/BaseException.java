package com.study.exception;

import com.study.dto.MessageDisplayType;
import lombok.Getter;
import lombok.Setter;


public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    /**
     * 사용자에게 노출되는 오류 설명
     */
    @Getter
    private String userMessage;

    /**
     * exception에 대한 프로그래밍적인 설명. 사용자에게 직접적으로 노출되지 않음
     */
    @Getter
    private String systemMessage;

    /**
     *오류 메세지에 대한 표시 방법
     */
    @Getter
    @Setter
    private MessageDisplayType displayType;


    /**
     * 오류에 대한 업무적으로 정의한 코드(http status 코드가 아님)
     */
    @Getter
    @Setter
    private String code;


    /**
     * 기본 예외 처리 생성자
     */
    public BaseException() {super();}


    /**
     * 기본 예외 처리 생성자
     **/
    public BaseException(String systemMessage,Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(systemMessage,cause,enableSuppression,writableStackTrace);
        this.systemMessage = systemMessage;
    }

    /**
     *
     */
    public BaseException(String systemMessage,Throwable cause){
        super(systemMessage,cause);
        //throwable message는 baseException에서는 systemMessage인 것으로 간주함
        this.systemMessage = systemMessage;
    }

    /**
     *
     */

    public BaseException(String systemMessage){
        super(systemMessage);
        //throwable message는 baseException에서는 systemMessage인 것으로 간주함
        this.systemMessage = systemMessage;
    }

    /**
     *
     */
    public BaseException(String systemMessage,Throwable cause, boolean enableSuppression, boolean writableStackTrace , String userMessage){
        super(systemMessage,cause,enableSuppression,writableStackTrace);
        //throwable message는 baseException에서는 systemMessage인 것으로 간주함
        this.systemMessage = systemMessage;
        this.userMessage = userMessage;
    }
}
