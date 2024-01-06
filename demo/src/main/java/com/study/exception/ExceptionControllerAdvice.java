package com.study.exception;

import com.study.dto.MessageDisplayType;
import com.study.response.RestResponse;



import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.util.Pair;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.net.HttpURLConnection;


@Slf4j

@ControllerAdvice

public class ExceptionControllerAdvice {

    private static final String SLASH = "/";


    //@Autowired
    //private MessageComponent messageComponent;


    @Value("${cmn.exception.responses-system-message}")
    private boolean responseSystemMessage;

    @ExceptionHandler(NotFoundException.class)
    public Object notFoundHandle(NotFoundException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("찾을 수 없음");
        }
        info.setHttpStatus(HttpStatus.NOT_FOUND);
        info.setTemplateName(String.valueOf(HttpURLConnection.HTTP_NOT_FOUND)); // 404
        return makeResponse(info,request,response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object NoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("찾을 수 없음");
        }
        info.setHttpStatus(HttpStatus.NOT_FOUND);
        info.setTemplateName(String.valueOf(HttpURLConnection.HTTP_NOT_FOUND)); // 404
        return makeResponse(info,request,response);
    }


    @ExceptionHandler({ValidationException.class , MethodArgumentNotValidException.class , BindException.class})
    public Object validationException(Exception ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("validaiton 오류");
        }
        info.setHttpStatus(HttpStatus.BAD_REQUEST);
        info.setTemplateName("4xx");
        return makeResponse(info,request,response);
    }

   @ExceptionHandler({UnauthorizedException.class})
    public Object UnauthorizedException(UnauthorizedException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("인증이 필요 , 로그인 요망");
        }

        info.setHttpStatus(HttpStatus.UNAUTHORIZED);
        info.setTemplateName(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED)); // 401
        return makeResponse(info,request,response);
    }
    @ExceptionHandler({ForbiddenException.class})
    public Object ForbiddenException(ForbiddenException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("권한이 없음");
        }
        info.setHttpStatus(HttpStatus.FORBIDDEN);
        info.setTemplateName("403"); // 403
        return makeResponse(info,request,response);
    }

    @ExceptionHandler({BizException.class})
    public Object BizException(BizException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("업무 프로세스 에러");
        }
        //강제 ok 설정이면 , status code는 ok로
        if(info.isForcesOK()){
            info.setHttpStatus(HttpStatus.OK);
        }else{
            info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        info.setTemplateName("5xx"); // 5xx
        return makeResponse(info,request,response);
    }



    @ExceptionHandler({InterfaceException.class})
    public Object InterfaceException(InterfaceException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("인터페이스 에러");
        }
        info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        info.setTemplateName("5xx"); // 5xx
        return makeResponse(info,request,response);
    }

    @ExceptionHandler({NotRegisterException.class})
    public Object NotRegisterException(NotRegisterException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("등록 되지 않음");
        }
        info.setHttpStatus(HttpStatus.NOT_FOUND);
        info.setTemplateName("499"); // 499
        return makeResponse(info,request,response);
    }


    @ExceptionHandler({BaseException.class})
    public Object BaseException(BaseException ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        if(StringUtils.isBlank(info.getUserMessage())){
            //to do
            //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
            info.setUserMessage("서버 오류");
        }

        info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        info.setTemplateName("5xx"); // 5xx
        return makeResponse(info,request,response);
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object HttpRequestMethodNotSupportedException(Exception ex, HttpServletRequest request, HttpServletResponse response){
        log.info("HttpRequestMethodNotSupportedException : uri = {}" , request.getRequestURI());
        ExceptionInfo info = new ExceptionInfo(ex);
        //to do
        //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
        info.setUserMessage("서버 오류");
        info.setSystemMessage(ex.getMessage());
        info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        info.setTemplateName("5xx"); // 5xx
        return makeResponse(info,request,response);
    }


    @ExceptionHandler({Exception.class , RuntimeException.class})
    public Object exceptionHandle(Exception ex, HttpServletRequest request, HttpServletResponse response){
        ExceptionInfo info = new ExceptionInfo(ex);
        //to do
        //info.setUserMessage(messageComponent.getMessage("cmm.code.notfound"));
        info.setUserMessage("서버 오류");
        info.setSystemMessage(ex.getMessage());
        info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        info.setTemplateName("5xx"); // 5xx
        return makeResponse(info,request,response);
    }
    public Object makeResponse(ExceptionInfo info,HttpServletRequest request, HttpServletResponse response){
        log.error("makeResponse(), {}" , ExceptionUtils.getStackTrace(info.getE()));
        response.setStatus(info.getHttpStatus().value());
        MediaType requestMediaType = null;
        if(request.getContentType() != null){
            requestMediaType = MediaType.valueOf(request.getContentType());
        }

        //rest api
        if(MediaType.APPLICATION_JSON.isCompatibleWith(requestMediaType)){
            RestResponse restResponse = new RestResponse().userMessage(info.userMessage).code(info.getCode()).displayType(info.getDisplayType());
            if(responseSystemMessage){
                restResponse.setSystemMessage(info.getSystemMessage());;
            }
            return new ResponseEntity<RestResponse>(restResponse,info.getHttpStatus());
        }

        //view
        else{
            ModelAndView mav = new ModelAndView();
            mav.addObject("userMessage" , info.getUserMessage());
            if(responseSystemMessage){
                mav.addObject("systemMessage" , info.getSystemMessage());
            }
            if("404".equals(info.getTemplateName()) ){
                mav.setViewName("error/"+info.getTemplateName());
            } else if ("409".equals(info.getTemplateName())) {
                response.setStatus(499);
                mav.setViewName("error/499");
            }else{
                mav.setViewName("error/"+info.getTemplateName());
            }
            return mav;
        }
    }


    @Getter
    @Setter
    private class ExceptionInfo{
        private Throwable e;
        private Throwable cause;
        private String userMessage;
        private String systemMessage;
        private MessageDisplayType displayType;
        private boolean forcesOK = false;
        private String code;
        private HttpStatus httpStatus;
        private String templateName;

        public ExceptionInfo(Throwable e){
            this.e = e;
            this.cause = e.getCause();
            if(e instanceof BaseException){
                BaseException be = (BaseException) e;
                this.userMessage = be.getUserMessage();
                this.systemMessage = be.getSystemMessage();
                this.displayType = be.getDisplayType();
                this.code = be.getCode();
            }

            // @Validator에서 뱉는 exception
            else if (e instanceof MethodArgumentNotValidException) {
                Pair<String,String> msg = setValidationError(((MethodArgumentNotValidException) e ).getBindingResult() );
                this.userMessage = msg.getFirst();
                this.systemMessage = msg.getSecond();
            }

            // @Validator에서 뱉는 exception
            else if (e instanceof BindException) {
                Pair<String,String> msg = setValidationError(((BindException) e ).getBindingResult() );
                this.userMessage = msg.getFirst();
                this.systemMessage = msg.getSecond();
            }else if (e instanceof BizException) {
                this.forcesOK = ((BizException) e).isForcesOK();
            }

            if(cause instanceof BaseException){
                BaseException be = (BaseException) cause;
                this.userMessage = be.getUserMessage();
                this.systemMessage = be.getSystemMessage();
                this.displayType = be.getDisplayType();
                this.code = be.getCode();
            }

            if(cause instanceof BizException){
                this.forcesOK = ((BizException) cause).isForcesOK();
            }
        }
        private Pair<String,String> setValidationError(BindingResult br){
            FieldError fe = br.getFieldError();
            String errorDefaultMessage = null;
            String errorAttribute = null;
            if(fe != null){
                errorDefaultMessage = fe.getDefaultMessage();
                errorAttribute = fe.getCodes()[0];
            }else{
                ObjectError oe = br.getGlobalError();
                errorDefaultMessage = oe.getDefaultMessage();
                errorAttribute = oe.getCode();
            }

            String key = errorDefaultMessage;
            Object[] args = null;

            if(StringUtils.indexOf(key,',') > -1 ){
                String[] errorMessageArr = key.split(",");
                key = errorMessageArr[0].trim();
                args = new Object[errorMessageArr.length-1];
                System.arraycopy(errorMessageArr,1,args,0,errorMessageArr.length-1);
            }

            String errorMessage = errorDefaultMessage;
            if(StringUtils.lastIndexOf(errorAttribute, '.') > -1){
                errorMessage = "[" + errorAttribute.substring(StringUtils.lastIndexOf(errorAttribute,'.')+1) + "]" +errorMessage;
            }

            return Pair.of(errorMessage,errorAttribute);
        }
    }
}
