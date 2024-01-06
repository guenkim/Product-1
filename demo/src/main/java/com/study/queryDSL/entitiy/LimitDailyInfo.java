package com.study.queryDSL.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.study.queryDSL.entitiy.key.LimitDailyInfoKey;

import com.study.queryDSL.entitiy.key.LimitUserMntrngKey;

import com.study.spring_data_jpa.entitiy.Team;

import lombok.*;

import org.springframework.data.domain.Persistable;


import javax.persistence.*;

import java.time.LocalDateTime;


/**
 * 임계치 일별 정보 : DAP_THRHLD_DAILY_INFO
 */

@Entity

@Getter

@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false)

@IdClass(LimitDailyInfoKey.class)

public class LimitDailyInfo implements Persistable<LimitDailyInfoKey> {


    @Id

    private LocalDateTime mntrngDt;


    @Id

    private String site_nm_detail;


    @Id

    private String lmtRuleCode;


    @Transient

    private LimitDailyInfoKey limitDailyInfoKey;

    private String abnrmlCd;

    private String lmtColVl1;

    private String lmtColVl2;

    private String lmtColVl3;

    private String lmtColVl4;


    @OneToOne

    @JoinColumn(name = "lmtRuleCode", referencedColumnName = "lmtRuleCode", insertable = false, updatable = false)

    private LimitCode limitCode;


    public LimitDailyInfo(LocalDateTime mntrngDt, String site_nm_detail, String lmtRuleCode, LimitDailyInfoKey limitDailyInfoKey, String abnrmlCd, String lmtColVl1, String lmtColVl2, String lmtColVl3, String lmtColVl4, LimitCode limitCode) {

        this.mntrngDt = mntrngDt;

        this.site_nm_detail = site_nm_detail;

        this.lmtRuleCode = lmtRuleCode;

        this.limitDailyInfoKey = limitDailyInfoKey;

        this.abnrmlCd = abnrmlCd;

        this.lmtColVl1 = lmtColVl1;

        this.lmtColVl2 = lmtColVl2;

        this.lmtColVl3 = lmtColVl3;

        this.lmtColVl4 = lmtColVl4;

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

    public LimitDailyInfoKey getId() {

        return limitDailyInfoKey;

    }


    @Override

    public boolean isNew() {

        return crt_dt == null;

    }


    public LimitDailyInfo(LimitDailyInfoKey limitDailyInfoKey, String abnrmlCd, String lmtColVl1, String lmtColVl2, String lmtColVl3, String lmtColVl4) {

        this.limitDailyInfoKey = limitDailyInfoKey;

        this.abnrmlCd = abnrmlCd;

        this.lmtColVl1 = lmtColVl1;

        this.lmtColVl2 = lmtColVl2;

        this.lmtColVl3 = lmtColVl3;

        this.lmtColVl4 = lmtColVl4;

    }

}
