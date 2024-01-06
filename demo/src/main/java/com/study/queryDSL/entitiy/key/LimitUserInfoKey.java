package com.study.queryDSL.entitiy.key;

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

public class LimitUserInfoKey implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userId;


    private String dsbrdNm;


    public LimitUserInfoKey(String userId, String dsbrdNm) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

    }


}
