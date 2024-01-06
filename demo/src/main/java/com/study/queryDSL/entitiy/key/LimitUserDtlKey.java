package com.study.queryDSL.entitiy.key;


import com.study.queryDSL.entitiy.LimitUserInfo;

import lombok.EqualsAndHashCode;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;


import javax.persistence.Embeddable;

import javax.persistence.Id;

import java.io.Serializable;


@Getter

@Setter

@NoArgsConstructor

@EqualsAndHashCode

public class LimitUserDtlKey implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userId;


    private String dsbrdNm;


    private String lmtRuleCode;


    public LimitUserDtlKey(String userId, String dsbrdNm, String lmtRuleCode) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

        this.lmtRuleCode = lmtRuleCode;

    }

}