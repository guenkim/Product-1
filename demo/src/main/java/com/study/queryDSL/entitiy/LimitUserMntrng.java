package com.study.queryDSL.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.study.queryDSL.entitiy.key.LimitUserDtlKey;

import com.study.queryDSL.entitiy.key.LimitUserMntrngKey;

import lombok.*;

import org.springframework.data.domain.Persistable;


import javax.persistence.*;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;


/**
 * 임계치 사용자 모니터링 : DAP_THRHLD_USER_MNTRNG
 */

@Entity

@Getter

@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false)

@IdClass(LimitUserMntrngKey.class)

public class LimitUserMntrng implements Persistable<LimitUserMntrngKey> {


    @Id

    private String userId;


    @Id

    private String dsbrdNm;


    @Id

    private LocalDateTime mntrngDt;

    @Transient

    private LimitUserMntrngKey limitUserMntrngKey;


    private String mntrngCd;

    private LocalDateTime emlSndngDt;

    private String emlSndngYn;


    @ManyToOne(fetch = FetchType.LAZY)

    @JsonBackReference

    @JoinColumns({

            @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false),

            @JoinColumn(name = "dsbrdNm", referencedColumnName = "dsbrdNm", insertable = false, updatable = false)

    })

    private LimitUserInfo LimitUserMntrng_limitUserInfo;


    public LimitUserMntrng(String userId, String dsbrdNm, LocalDateTime mntrngDt, LimitUserMntrngKey limitUserMntrngKey, String mntrngCd, LocalDateTime emlSndngDt, String emlSndngYn, LimitUserInfo limitUserMntrng_limitUserInfo) {

        this.userId = userId;

        this.dsbrdNm = dsbrdNm;

        this.mntrngDt = mntrngDt;

        this.limitUserMntrngKey = limitUserMntrngKey;

        this.mntrngCd = mntrngCd;

        this.emlSndngDt = emlSndngDt;

        this.emlSndngYn = emlSndngYn;

        LimitUserMntrng_limitUserInfo = limitUserMntrng_limitUserInfo;

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

    public LimitUserMntrngKey getId() {

        return limitUserMntrngKey;

    }


    @Override

    public boolean isNew() {

        return crt_dt == null;

    }

}