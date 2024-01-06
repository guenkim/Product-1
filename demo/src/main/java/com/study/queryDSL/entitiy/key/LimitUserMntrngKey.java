package com.study.queryDSL.entitiy.key;


import lombok.EqualsAndHashCode;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;


import javax.persistence.Embeddable;

import javax.persistence.Id;

import java.io.Serializable;

import java.time.LocalDateTime;


@Getter

@Setter

@NoArgsConstructor

@EqualsAndHashCode

public class LimitUserMntrngKey implements Serializable {


    private static final long serialVersionUID = 1L;


    private String userId;


    private String dsbrdNm;


    private LocalDateTime mntrngDt;


    public LimitUserMntrngKey(String userId, String dsbrdNm, LocalDateTime mntrngDt) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

        this.mntrngDt = mntrngDt;

    }

}