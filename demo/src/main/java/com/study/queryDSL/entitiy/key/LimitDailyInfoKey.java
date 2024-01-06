package com.study.queryDSL.entitiy.key;

import com.sun.source.doctree.SerialDataTree;

import lombok.EqualsAndHashCode;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

import org.springframework.context.annotation.EnableMBeanExport;


import javax.persistence.Embeddable;

import javax.persistence.Id;

import java.io.Serializable;

import java.time.LocalDateTime;


@Getter

@Setter

@NoArgsConstructor

@EqualsAndHashCode

public class LimitDailyInfoKey implements Serializable {


    private static final long serialVersionUID = 1L;


    private LocalDateTime mntrngDt;


    private String site_nm_detail;


    private String lmtRuleCode;


    public LimitDailyInfoKey(LocalDateTime mntrngDt, String site_nm_detail, String lmtRuleCode) {

        this.mntrngDt = mntrngDt;

        this.site_nm_detail = site_nm_detail;

        this.lmtRuleCode = lmtRuleCode;

    }

}

