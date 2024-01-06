package com.study.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 component가 아닌 pojo에서 applicationContext를 접근 할 수 있도록 제공
 **/

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
    public static ApplicationContext getApplicationContext() {return context;}


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        context = applicationContext;
    }
}
