package com.study.queryDSL.entitiy;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.study.queryDSL.entitiy.key.LimitUserInfoKey;

import com.study.spring_data_jpa.entitiy.Member;

import lombok.*;

import org.springframework.data.domain.Persistable;


import javax.persistence.*;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;


/**
 * 임계치 사용자 정보 : DAP_THRHLD_USER_INFO
 */

@Entity

@Getter

@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false)

@IdClass(LimitUserInfoKey.class)

public class LimitUserInfo implements Persistable<LimitUserInfoKey> {

    @Id

    private String userId;


    @Id

    private String dsbrdNm;


    @Transient

    private LimitUserInfoKey limitUserInfoKey;


    private String lmtRuleLclasDesc;


    private String emlSndngHm;


    private String emlSubscrpYn;


    public LimitUserInfo(String userId, String dsbrdNm, LimitUserInfoKey limitUserInfoKey, String lmtRuleLclasDesc, String emlSndngHm, String emlSubscrpYn) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

        this.limitUserInfoKey = limitUserInfoKey;

        this.lmtRuleLclasDesc = lmtRuleLclasDesc;

        this.emlSndngHm = emlSndngHm;

        this.emlSubscrpYn = emlSubscrpYn;

    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "LimitUserDtl_limitUserInfo")

    @JsonManagedReference

    List<LimitUserDtl> listLimitUserDtl_limitUserInfo = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "LimitUserMntrng_limitUserInfo")

    @JsonManagedReference

    List<LimitUserMntrng> listLimitUserMntrng_limitUserInfo = new ArrayList<>();


    @Column(updatable = false)

    private LocalDateTime crt_dt;


    private LocalDateTime chg_dt;


    @PrePersist

    public void prePersist() {

        LocalDateTime now = LocalDateTime.now();

        crt_dt = now;

        chg_dt = now;

    }


    @PreUpdate

    public void preUpdate() {

        chg_dt = LocalDateTime.now();

    }


    @Override

    public LimitUserInfoKey getId() {

        return limitUserInfoKey;

    }


    @Override

    public boolean isNew() {

        return crt_dt == null;

    }

}