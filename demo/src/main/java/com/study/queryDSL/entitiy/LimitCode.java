package com.study.queryDSL.entitiy;


import lombok.*;

import org.springframework.data.domain.Persistable;


import javax.persistence.*;

import java.time.LocalDateTime;


/**
 * 임계치 룰 코드 : DAP_THRHLD_CODE
 */

@Entity

@Getter

@Setter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false)

public class LimitCode implements Persistable<String> {


    @Id

    private String lmtRuleCode;


    private String lmtRuleDesc;

    private String lmtRuleLclasCode;

    private String lmtRuleLclasDesc;

    private String lmtColnm1;

    private String lmtColnm2;

    private String lmtColnm3;

    private String lmtColnm4;


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

    public String getId() {

        return lmtRuleCode;

    }


    @Override

    public boolean isNew() {

        return crt_dt == null;

    }


    public LimitCode(String lmtRuleCode, String lmtRuleDesc, String lmtRuleLclasCode, String lmtRuleLclasDesc, String lmtColnm1, String lmtColnm2, String lmtColnm3, String lmtColnm4) {

        this.lmtRuleCode = lmtRuleCode;

        this.lmtRuleDesc = lmtRuleDesc;

        this.lmtRuleLclasCode = lmtRuleLclasCode;

        this.lmtRuleLclasDesc = lmtRuleLclasDesc;

        this.lmtColnm1 = lmtColnm1;

        this.lmtColnm2 = lmtColnm2;

        this.lmtColnm3 = lmtColnm3;

        this.lmtColnm4 = lmtColnm4;

    }

}

