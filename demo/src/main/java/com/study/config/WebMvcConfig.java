package com.study.config;


import com.querydsl.core.annotations.Config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;

import org.modelmapper.spi.MatchingStrategy;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.MessageSource;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.http.MediaType;

import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import org.thymeleaf.spring5.SpringTemplateEngine;

import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import org.thymeleaf.templatemode.TemplateMode;

import javax.swing.*;

import java.nio.charset.StandardCharsets;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;


@Configuration

@EnableWebMvc

public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 기본 언어 코드
     **/

    @Value("${cmn.language.default}")

    private String langDefaultCode;


    /**
     * 언어 설정용 쿠키명
     **/

    @Value("${cmn.language.cookie.name}")

    private String langCookieName;


    /**
     * 언어 설정용 쿠키 만료기간
     **/

    @Value("${cmn.language.cookie.maxage}")

    private Integer langCookieMaxAge;


    /**
     * thymeleaf 캐시 설정
     **/

    @Value("${cmn.thymeleaf.cache}")

    private boolean isThymeleafCache;


    /**
     * 정적 리소스 접근 설정
     *
     * @param registry
     */

    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");

        registry.addResourceHandler("/source/**").addResourceLocations("classpath:static/source/");


        /** swagger ui 정적 리소스 추가 **/

        registry.addResourceHandler("swagger-ui.html")

                .addResourceLocations("classpath:/META-INF/resources/");

    }


    /**
     * 기본 content-type을 json으로 설정
     *
     * @return
     */


    @Override

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.defaultContentType(MediaType.APPLICATION_JSON);

        WebMvcConfigurer.super.configureContentNegotiation(configurer);

    }


    /**
     * view Resolver 설정 : thymeleaf
     **/

    private SpringResourceTemplateResolver templateResolver() {

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setPrefix("classpath:/templates/");

        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateResolver.setCacheable(isThymeleafCache);

        return templateResolver;

    }

    /**
     * view Resolver 설정 : thymeleaf
     **/

    private SpringTemplateEngine templateEngine() {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.setTemplateResolver(templateResolver());

        templateEngine.addDialect(layoutDialect());

        return templateEngine;

    }


    @Bean

    public ThymeleafViewResolver viewResolver() {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

        viewResolver.setTemplateEngine(templateEngine());

        return viewResolver;

    }


    @Bean

    public LayoutDialect layoutDialect() {

        return new LayoutDialect();

    }


    @Bean

    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;

    }


    /**
     * 쿠키를 이용한 Locale 설정
     * <p>
     * LocaleResolver
     **/

    @Bean

    public LocaleResolver localeResolver() {

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();

        cookieLocaleResolver.setDefaultLocale(Locale.forLanguageTag(langDefaultCode));

        cookieLocaleResolver.setCookieName(langCookieName);

        cookieLocaleResolver.setCookieMaxAge(langCookieMaxAge);

        return cookieLocaleResolver;

    }


    /**
     * MessageSource 설정
     * <p>
     * message값은 db보다 messages properties 우선순위를 갖는다.
     * <p>
     * return messageSource
     */

    @Bean

    public MessageSource messageSource() {

        //서버 기본 locale 설정

        Locale.setDefault(new Locale(langDefaultCode));


        KeySearchableResourceBundleMessageSource bundleMessageSource = new KeySearchableResourceBundleMessageSource();

        bundleMessageSource.setBasename("messages/messages");

        bundleMessageSource.setDefaultEncoding("UTF-8");


        return bundleMessageSource;

    }

    /**
     * * Auditing
     * - SPRING DATA JPA 사용
     * <p>
     * 등록자, 수정자를 처리해주는 AuditorAware 스프링 빈 등록
     **/
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {

                //여기서는 ID를 UUID로 처리
                //실제에서는 SESSION에서 ID를 뽑아서 넣어준다.
                return Optional.of(UUID.randomUUID().toString());
            }
        };
    }


}



