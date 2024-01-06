package com.study.component;


import com.study.config.KeySearchableResourceBundleMessageSource;

import groovyjarjarantlr4.v4.runtime.misc.FlexibleHashMap;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ArrayUtils;

import org.apache.commons.lang3.StringUtils;

import org.springframework.aop.framework.Advised;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.MessageSource;

import org.springframework.context.annotation.Lazy;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.stereotype.Component;


import java.util.HashMap;

import java.util.List;

import java.util.Map;


@Component("messageComponent")

@Slf4j

public class MessageComponent {


    public static final String JS_MESSAGES_KEY_PATTERN = "__MESSAGE_KEY_PATTERN__";

    public static final String JS_MESSAGES_PROGRAM_IDS = "__MESSAGE_PROGRAM_IDS__";


    /**
     * 사용하는 언어 코드 목록
     **/

    private List<String> langSupportCodeList;


    /**
     * 메세지 소스
     **/

    private MessageSource messageSource;


    /**
     * MessageComponent 생성자
     *
     * @Param langSupportCodeList 다국어지원코드목록
     * @Param messageSource MessageSource
     */

    @Autowired

    public MessageComponent(@Value("#{'${cmn.language.support}'.split(',')}") List<String> langSupportedCodeList, @Lazy MessageSource messageSource) {

        this.messageSource = messageSource;

        this.langSupportCodeList = langSupportedCodeList;

    }


    /**
     * 사용중인 언어의 index 조회
     * <p>
     * application.properties의  cmn.language.support에 정의된 언어
     *
     * @Return 사용중인 언어의 index
     */

    public int getLanguageIndex() {

        int langIndex = 0;

        if (this.langSupportCodeList == null) {

            return langIndex;

        }


        String langCode = LocaleContextHolder.getLocale().toString();

        for (int i = 0, max = this.langSupportCodeList.size(); i < max; i++) {

            if (langCode.equals(this.langSupportCodeList.get(i))) {

                langIndex = i;

                break;

            }

        }

        return langIndex;

    }


    /**
     * 메세지 조회
     *
     * @param strCode message key
     * @return 설정언어의 메세지
     */

    public String getMessage(String strCode) {

        return messageSource.getMessage(strCode, null, null, LocaleContextHolder.getLocale());

    }


    /**
     * 메세지 조회 - 메시지에 대체 단어가 있는 경우
     *
     * @param strCode message key
     * @param objArgs 메세지의 {0},{1},{2} 부분을 대체할 객체들에 대한 array
     * @return 설정언어의 메세지
     */

    public String getMessage(String strCode, Object[] objArgs) {

        return messageSource.getMessage(strCode, objArgs, null, LocaleContextHolder.getLocale());

    }


    /**
     * 메세지 조회 - 메시지에 대체 단어가 있는 경우
     *
     * @param strCode message key
     * @param strArgs 메세지의 {0},{1},{2} 부분을 대체할 객체들에 대한 array
     * @return 설정언어의 메세지
     */

    public String getMessage(String strCode, String strArgs) {

        return messageSource.getMessage(strCode, new Object[]{strArgs}, null, LocaleContextHolder.getLocale());

    }


    /**
     * 메세지 조회
     *
     * @param strCode        message key
     * @param objArgs        메세지의 {0},{1},{2} 부분을 대체할 객체들에 대한 array
     * @param defaultMEssage 메세지 기본값
     * @return 설정언어의 메세지
     */

    public String getMessage(String strCode, Object[] objArgs, String defaultMEssage) {

        return messageSource.getMessage(strCode, objArgs, defaultMEssage, LocaleContextHolder.getLocale());

    }


    /**
     * 메시지 키 패턴에 해당하는 메시지 조회
     * <p>
     * ResourceBundleMessageSource에 해당되는 메세지들을 검색
     *
     * @Param strKeyPatterns ResourceBundleMessageSource에서 검색할 key 패턴에 대한 배열<br>
     * <p>
     * e.g "cmn.message.exception.*"
     * @Return 메시지 Map
     */

    public Map<String, String> filterWith(String[] strKeyPatterns) {

        Map<String, String> resultMap = new HashMap<String, String>();

        //keyPattern에 해당하는 메세지들 담기.

        for (String strKeyPattern : ArrayUtils.nullToEmpty(strKeyPatterns)) {


            if (StringUtils.isNotBlank(strKeyPattern)) {

                KeySearchableResourceBundleMessageSource keyMessageSource = null;

                if (messageSource instanceof KeySearchableResourceBundleMessageSource) {

                    keyMessageSource = (KeySearchableResourceBundleMessageSource) messageSource;

                } else if (messageSource instanceof Advised) {

                    Object messageSourceTarget = null;

                    try {

                        messageSourceTarget = ((Advised) messageSource).getTargetSource().getTarget();

                    } catch (Exception e) {

                        if (log.isErrorEnabled()) {

                            log.error(e.getMessage());

                        }

                    }


                    if (keyMessageSource != null) {

                        List<String> keyList = keyMessageSource.filterKeys(strKeyPattern);

                        for (String strKey : keyList) {

                            resultMap.put(strKey, keyMessageSource.getMessage(strKey, null, LocaleContextHolder.getLocale()));

                        }

                    }


                }

            }

        }

        return resultMap;

    }


}

