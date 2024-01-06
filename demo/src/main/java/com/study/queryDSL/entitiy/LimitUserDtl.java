package com.study.queryDSL.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.study.queryDSL.entitiy.key.LimitUserDtlKey;

import com.study.queryDSL.entitiy.key.LimitUserInfoKey;

import com.study.spring_data_jpa.entitiy.Team;

import lombok.*;

import org.springframework.data.domain.Persistable;


import javax.persistence.*;

import java.time.LocalDateTime;


/**
 * 임계치 사용자 상세 : DAP_THRHLD_USER_DTL
 */

@Entity

@Getter

@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false)

@IdClass(LimitUserDtlKey.class)

public class LimitUserDtl implements Persistable<LimitUserDtlKey> {


    @Id

    private String userId;

    @Id

    private String dsbrdNm;

    @Id

    private String lmtRuleCode;

    @Transient

    private LimitUserDtlKey limitUserDtlKey;


    @ManyToOne(fetch = FetchType.LAZY)

    @JsonBackReference

    @JoinColumns({

            @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false),

            @JoinColumn(name = "dsbrdNm", referencedColumnName = "dsbrdNm", insertable = false, updatable = false)

    })

    private LimitUserInfo LimitUserDtl_limitUserInfo;


    @OneToOne

    @JoinColumn(name = "lmtRuleCode", referencedColumnName = "lmtRuleCode", insertable = false, updatable = false)

    private LimitCode limitCode;


    public LimitUserDtl(String userId, String dsbrdNm, String lmtRuleCode, LimitUserDtlKey limitUserDtlKey, LimitUserInfo limitUserDtl_limitUserInfo, LimitCode limitCode) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

        this.lmtRuleCode = lmtRuleCode;

        this.limitUserDtlKey = limitUserDtlKey;

        LimitUserDtl_limitUserInfo = limitUserDtl_limitUserInfo;

        this.limitCode = limitCode;

    }


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

    public LimitUserDtlKey getId() {

        return limitUserDtlKey;

    }


    @Override

    public boolean isNew() {

        return crt_dt == null;

    }

}