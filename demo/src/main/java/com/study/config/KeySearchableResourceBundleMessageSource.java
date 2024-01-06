package com.study.config;

import org.apache.commons.lang3.StringUtils;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.context.support.ResourceBundleMessageSource;


import java.util.*;


/**
 * ResourceBundleMessageSource에 key 검색기능이 추가된 MessageSource
 */

public class KeySearchableResourceBundleMessageSource extends ResourceBundleMessageSource {


    /**
     * KeyPattern에 해당하는 key들을 리턴
     * <p>
     * 검색 대항은 이 ResourceBundleMessageSource 객체내에 한 함
     * <p>
     * Parent MessageSource나 commonMessage는 검색하지 않음
     *
     * @Param keyPattern
     * <p>
     * ex) "cmn.message.exception.*" 형태의 message key에 대한 검색 패턴
     * @Return 검색된 key 목록
     */

    public List<String> filterKeys(String strKeyPattern) {

        List<String> resultList = new ArrayList<String>();

        if (StringUtils.isBlank(strKeyPattern)) {

            return resultList;

        }


        Set<String> basenameSet = this.getBasenameSet();

        for (String baseName : basenameSet) {

            ResourceBundle bundle = this.getResourceBundle(baseName, LocaleContextHolder.getLocale());

            for (Enumeration<String> keys = bundle.getKeys(); keys.hasMoreElements(); ) {

                String strKey = keys.nextElement();

                if (strKey.matches(strKeyPattern)) {

                    resultList.add(strKey);

                }

            }

        }

        return resultList;

    }

}