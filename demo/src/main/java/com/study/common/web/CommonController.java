package com.study.common.web;


import com.study.component.MessageComponent;

import com.study.contents.dto.ContentSearchDTO;

import com.study.contents.response.RestResponse;

import com.study.contents.response.ToastUIRestResponse;

import io.swagger.annotations.ApiImplicitParam;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.servlet.support.RequestContextUtils;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.util.Locale;


@RestController

public class CommonController {



    /* -------------------------------------------------------------

        API

    --------------------------------------------------------------*/

    @ApiOperation(value = "다국어 지원, 언어셋 변경"

            , notes = "다국어 지원, 언어셋 변경")

    /**

     @ApiImplicitParam( name = "lang"

     , value = "언어셋"

     , required = true

     , dataType = "Array"

     , paramType = "path"

     //, defaultValue = 0

     ,example = "ko"

     )
     **/

    @PostMapping(value = "/api/common/language")

    public Boolean changeLanguage(@RequestBody String[] langs, HttpServletRequest request, HttpServletResponse response) {

        try {


            String langCode = LocaleContextHolder.getLocale().toString();

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");

            System.out.println("previous langCode :" + langCode);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");


            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

            localeResolver.setLocale(request, response, new Locale(langs[0]));


            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");

            System.out.println("current langCode :" + LocaleContextHolder.getLocale().toString());

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");


        } catch (Exception e) {

            return false;

        }

        return true;

    }


    @ApiOperation(value = "현재 언어 셋 조회"

            , notes = "현재 언어 셋 조회")

    @GetMapping(value = "/api/common/languageInfo")

    public String findLangInfo() {

        String langCode = LocaleContextHolder.getLocale().toString();

        String langInfo = new String(langCode);

        return langInfo;

    }


}



        