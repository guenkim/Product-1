package com.study.limit;


import com.querydsl.core.BooleanBuilder;

import com.querydsl.core.QueryResults;

import com.querydsl.core.Tuple;

import com.querydsl.core.types.ExpressionUtils;

import com.querydsl.core.types.Projections;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.core.types.dsl.CaseBuilder;

import com.querydsl.core.types.dsl.Expressions;

import com.querydsl.core.types.dsl.NumberExpression;

import com.querydsl.jpa.JPAExpressions;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.dto.MemberDto;

import com.study.queryDSL.dto.QMemberDto;

import com.study.queryDSL.dto.UserDto;

import com.study.queryDSL.entitiy.*;

import com.study.queryDSL.entitiy.key.LimitDailyInfoKey;

import com.study.queryDSL.entitiy.key.LimitUserDtlKey;

import com.study.queryDSL.entitiy.key.LimitUserInfoKey;

import com.study.queryDSL.entitiy.key.LimitUserMntrngKey;

import com.study.queryDSL.repository.limit.entityJpa.*;

import com.study.spring_data_jpa.entitiy.Member;

import com.study.spring_data_jpa.entitiy.QMember;


import com.study.spring_data_jpa.entitiy.Team;

import com.study.spring_data_jpa.repository.springDataJpa.MemberSpringDataJpaRepository;

import com.study.spring_data_jpa.repository.springDataJpa.TeamSpringDataJpaRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.PersistenceContext;

import javax.persistence.PersistenceUnit;

import java.time.LocalDateTime;

import java.util.List;


import static com.study.spring_data_jpa.entitiy.QMember.member;

import static com.study.spring_data_jpa.entitiy.QTeam.team;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest

@Transactional

//@Rollback(false)

public class LimitTest {


    @Autowired

    LimitCodeRepository limitCodeRepository;

    @Autowired

    LimitDailyInfoRepository limitDailyInfoRepository;

    @Autowired

    LimitUserDtlRepository limitUserDtlRepository;

    @Autowired

    LimitUserInfoRepository limitUserInfoRepository;


    @Autowired

    LimitUserMntrngRepository limitUserMntrngRepository;


    @PersistenceContext

    EntityManager em;


    JPAQueryFactory queryFactory;


    /*************************************************************************************************************
     * 테스트 실행 전에 먼저 실행
     *************************************************************************************************************/

    @BeforeEach

    public void before() {

        queryFactory = new JPAQueryFactory(em);


        /** 임계치 룰 코드 : LimitCode*/
        /**
         LimitCode limitCode1 = new LimitCode("rule1", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode2 = new LimitCode("rule2", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode3 = new LimitCode("rule3", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode4 = new LimitCode("rule4", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode5 = new LimitCode("rule5", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode6 = new LimitCode("rule6", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode7 = new LimitCode("rule7", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         LimitCode limitCode8 = new LimitCode("rule8", "rule 설명", "rule detail code", "rule detail desc", "column1", "column2", "column3", "column4");

         em.persist(limitCode1);

         em.persist(limitCode2);

         em.persist(limitCode3);

         em.persist(limitCode4);

         em.persist(limitCode5);

         em.persist(limitCode6);

         em.persist(limitCode7);

         em.persist(limitCode8);
         **/
        /** 임계치 사용자 정보 : LimitUserInfo **/

        /**
         LimitUserInfoKey limitUserInfoKey1 = new LimitUserInfoKey("geun1", "dsbd1");

         LimitUserInfoKey limitUserInfoKey2 = new LimitUserInfoKey("geun1", "dsbd2");

         LimitUserInfoKey limitUserInfoKey3 = new LimitUserInfoKey("geun1", "dsbd3");

         LimitUserInfoKey limitUserInfoKey4 = new LimitUserInfoKey("geun1", "dsbd4");


         LimitUserInfo limitUserInfo1 = new LimitUserInfo("geun1", "dsbd1", limitUserInfoKey1, "rule 설명", "12시", "Y");

         LimitUserInfo limitUserInfo2 = new LimitUserInfo("geun1", "dsbd2", limitUserInfoKey2, "rule 설명", "12시", "Y");

         LimitUserInfo limitUserInfo3 = new LimitUserInfo("geun1", "dsbd3", limitUserInfoKey3, "rule 설명", "12시", "Y");

         LimitUserInfo limitUserInfo4 = new LimitUserInfo("geun1", "dsbd4", limitUserInfoKey4, "rule 설명", "12시", "Y");


         em.persist(limitUserInfo1);

         em.persist(limitUserInfo2);

         em.persist(limitUserInfo3);

         em.persist(limitUserInfo4);
         **/

        /**
         * 임계치 사용자 상세 : LimitUserDtl
         *
         * this.userId =userId;
         *         this.dsbrdNm = dsbrdNm;
         *         this.lmtRuleCode = lmtRuleCode;
         *         this.limitUserDtlKey = limitUserDtlKey;
         *         LimitUserDtl_limitUserInfo = limitUserDtl_limitUserInfo;
         *         this.limitCode = limitCode;
         *

         */

        /**
         LimitUserDtlKey limitUserDtlKey1 = new LimitUserDtlKey("geun1", "dsbd1", "rule1");

         LimitUserDtlKey limitUserDtlKey2 = new LimitUserDtlKey("geun1", "dsbd1", "rule2");

         LimitUserDtlKey limitUserDtlKey3 = new LimitUserDtlKey("geun1", "dsbd1", "rule3");

         LimitUserDtlKey limitUserDtlKey4 = new LimitUserDtlKey("geun1", "dsbd2", "rule4");

         LimitUserDtlKey limitUserDtlKey5 = new LimitUserDtlKey("geun1", "dsbd2", "rule5");

         LimitUserDtlKey limitUserDtlKey6 = new LimitUserDtlKey("geun1", "dsbd2", "rule6");

         LimitUserDtlKey limitUserDtlKey7 = new LimitUserDtlKey("geun1", "dsbd3", "rule7");

         LimitUserDtlKey limitUserDtlKey8 = new LimitUserDtlKey("geun1", "dsbd3", "rule8");


         LimitUserDtl limitUserDtl1 = new LimitUserDtl("geun1", "dsbd1", "rule1", limitUserDtlKey1, limitUserInfo1, limitCode1);

         LimitUserDtl limitUserDtl2 = new LimitUserDtl("geun1", "dsbd1", "rule2", limitUserDtlKey2, limitUserInfo1, limitCode2);

         LimitUserDtl limitUserDtl3 = new LimitUserDtl("geun1", "dsbd1", "rule3", limitUserDtlKey3, limitUserInfo1, limitCode3);

         LimitUserDtl limitUserDtl4 = new LimitUserDtl("geun1", "dsbd2", "rule4", limitUserDtlKey4, limitUserInfo2, limitCode4);

         LimitUserDtl limitUserDtl5 = new LimitUserDtl("geun1", "dsbd2", "rule5", limitUserDtlKey5, limitUserInfo2, limitCode5);

         LimitUserDtl limitUserDtl6 = new LimitUserDtl("geun1", "dsbd2", "rule6", limitUserDtlKey6, limitUserInfo2, limitCode6);

         LimitUserDtl limitUserDtl7 = new LimitUserDtl("geun1", "dsbd3", "rule7", limitUserDtlKey7, limitUserInfo3, limitCode7);

         LimitUserDtl limitUserDtl8 = new LimitUserDtl("geun1", "dsbd3", "rule8", limitUserDtlKey8, limitUserInfo3, limitCode8);


         em.persist(limitUserDtl1);

         em.persist(limitUserDtl2);

         em.persist(limitUserDtl3);

         em.persist(limitUserDtl4);

         em.persist(limitUserDtl5);

         em.persist(limitUserDtl6);

         em.persist(limitUserDtl7);

         em.persist(limitUserDtl8);
         **/

        /**
         * 임계치 사용자 모니터링 : LimitUserMntrng
         * this.userId = userId;
         *         this.dsbrdNm = dsbrdNm;
         *         this.mntrngDt = mntrngDt;
         *         this.limitUserMntrngKey = limitUserMntrngKey;
         *         this.mntrngCd = mntrngCd;
         *         this.emlSndngDt = emlSndngDt;
         *         this.emlSndngYn = emlSndngYn;
         *         LimitUserMntrng_limitUserInfo = limitUserMntrng_limitUserInfo;

         */

        /**
         LocalDateTime now = LocalDateTime.now(); // 현재시간


         LimitUserMntrngKey limitUserMntrngKey1 = new LimitUserMntrngKey("geun1", "dsbd1", now);

         LimitUserMntrngKey limitUserMntrngKey2 = new LimitUserMntrngKey("geun1", "dsbd1", now.minusDays(2));

         LimitUserMntrngKey limitUserMntrngKey3 = new LimitUserMntrngKey("geun1", "dsbd1", now.minusDays(3));

         LimitUserMntrngKey limitUserMntrngKey4 = new LimitUserMntrngKey("geun1", "dsbd2", now);

         LimitUserMntrngKey limitUserMntrngKey5 = new LimitUserMntrngKey("geun1", "dsbd2", now.minusDays(3));

         LimitUserMntrngKey limitUserMntrngKey6 = new LimitUserMntrngKey("geun1", "dsbd2", now.minusDays(4));

         LimitUserMntrngKey limitUserMntrngKey7 = new LimitUserMntrngKey("geun1", "dsbd3", now);

         LimitUserMntrngKey limitUserMntrngKey8 = new LimitUserMntrngKey("geun1", "dsbd3", now.minusDays(5));


         LimitUserMntrng LimitUserMntrng1 = new LimitUserMntrng("geun1", "dsbd1", now, limitUserMntrngKey1, "Red", now, "Y", limitUserInfo1);

         LimitUserMntrng LimitUserMntrng2 = new LimitUserMntrng("geun1", "dsbd1", now.minusDays(2), limitUserMntrngKey1, "Red", now.minusDays(2), "Y", limitUserInfo1);

         LimitUserMntrng LimitUserMntrng3 = new LimitUserMntrng("geun1", "dsbd1", now.minusDays(3), limitUserMntrngKey1, "Red", now.minusDays(3), "Y", limitUserInfo1);

         LimitUserMntrng LimitUserMntrng4 = new LimitUserMntrng("geun1", "dsbd2", now, limitUserMntrngKey1, "Red", now, "Y", limitUserInfo2);

         LimitUserMntrng LimitUserMntrng5 = new LimitUserMntrng("geun1", "dsbd2", now.minusDays(3), limitUserMntrngKey1, "Red", now.minusDays(3), "Y", limitUserInfo2);

         LimitUserMntrng LimitUserMntrng6 = new LimitUserMntrng("geun1", "dsbd2", now.minusDays(4), limitUserMntrngKey1, "Red", now.minusDays(4), "Y", limitUserInfo2);

         LimitUserMntrng LimitUserMntrng7 = new LimitUserMntrng("geun1", "dsbd3", now, limitUserMntrngKey1, "Red", now, "Y", limitUserInfo1);

         LimitUserMntrng LimitUserMntrng8 = new LimitUserMntrng("geun1", "dsbd3", now.minusDays(5), limitUserMntrngKey1, "Red", now.minusDays(5), "Y", limitUserInfo1);


         em.persist(LimitUserMntrng1);

         em.persist(LimitUserMntrng2);

         em.persist(LimitUserMntrng3);

         em.persist(LimitUserMntrng4);

         em.persist(LimitUserMntrng5);

         em.persist(LimitUserMntrng6);

         em.persist(LimitUserMntrng7);

         em.persist(LimitUserMntrng8);
         **/

        /**  임계치 일별 정보 : LimitDailyInfo
         * this.mntrngDt = mntrngDt;
         *         this.site_nm_detail = site_nm_detail;
         *         this.lmtRuleCode = lmtRuleCode;
         *         this.limitDailyInfoKey = limitDailyInfoKey;
         *         this.abnrmlCd = abnrmlCd;
         *         this.lmtColVl1 = lmtColVl1;
         *         this.lmtColVl2 = lmtColVl2;
         *         this.lmtColVl3 = lmtColVl3;
         *         this.lmtColVl4 = lmtColVl4;
         *         this.limitCode = limitCode;
         *
         * */

        /**
         LimitDailyInfoKey limitDailyInfoKey1 = new LimitDailyInfoKey(now, "site1", "rule1");

         LimitDailyInfo LimitDailyInfo1 = new LimitDailyInfo(now, "site1", "rule1", limitDailyInfoKey1, "RED", "value1", "value2", "value3", "value4", limitCode1);

         LimitDailyInfoKey limitDailyInfoKey2 = new LimitDailyInfoKey(now, "site1", "rule2");

         LimitDailyInfo LimitDailyInfo2 = new LimitDailyInfo(now, "site1", "rule2", limitDailyInfoKey2, "RED", "value1", "value2", "value3", "value4", limitCode2);

         LimitDailyInfoKey limitDailyInfoKey3 = new LimitDailyInfoKey(now, "site1", "rule3");

         LimitDailyInfo LimitDailyInfo3 = new LimitDailyInfo(now, "site1", "rule3", limitDailyInfoKey3, "RED", "value1", "value2", "value3", "value4", limitCode3);

         LimitDailyInfoKey limitDailyInfoKey4 = new LimitDailyInfoKey(now, "site1", "rule4");

         LimitDailyInfo LimitDailyInfo4 = new LimitDailyInfo(now, "site1", "rule4", limitDailyInfoKey4, "RED", "value1", "value2", "value3", "value4", limitCode4);

         LimitDailyInfoKey limitDailyInfoKey5 = new LimitDailyInfoKey(now, "site1", "rule5");

         LimitDailyInfo LimitDailyInfo5 = new LimitDailyInfo(now, "site1", "rule5", limitDailyInfoKey5, "RED", "value1", "value2", "value3", "value4", limitCode5);

         LimitDailyInfoKey limitDailyInfoKey6 = new LimitDailyInfoKey(now, "site1", "rule6");

         LimitDailyInfo LimitDailyInfo6 = new LimitDailyInfo(now, "site1", "rule6", limitDailyInfoKey6, "RED", "value1", "value2", "value3", "value4", limitCode6);

         LimitDailyInfoKey limitDailyInfoKey7 = new LimitDailyInfoKey(now, "site1", "rule7");

         LimitDailyInfo LimitDailyInfo7 = new LimitDailyInfo(now, "site1", "rule7", limitDailyInfoKey7, "RED", "value1", "value2", "value3", "value4", limitCode7);

         LimitDailyInfoKey limitDailyInfoKey8 = new LimitDailyInfoKey(now, "site1", "rule8");

         LimitDailyInfo LimitDailyInfo8 = new LimitDailyInfo(now, "site1", "rule8", limitDailyInfoKey8, "RED", "value1", "value2", "value3", "value4", limitCode8);


         LimitDailyInfoKey limitDailyInfoKey9 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule1");

         LimitDailyInfo LimitDailyInfo9 = new LimitDailyInfo(now.minusDays(2), "site1", "rule1", limitDailyInfoKey9, "RED", "value1", "value2", "value3", "value4", limitCode1);

         LimitDailyInfoKey limitDailyInfoKey10 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule2");

         LimitDailyInfo LimitDailyInfo10 = new LimitDailyInfo(now.minusDays(2), "site1", "rule2", limitDailyInfoKey10, "RED", "value1", "value2", "value3", "value4", limitCode2);

         LimitDailyInfoKey limitDailyInfoKey11 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule3");

         LimitDailyInfo LimitDailyInfo11 = new LimitDailyInfo(now.minusDays(2), "site1", "rule3", limitDailyInfoKey11, "RED", "value1", "value2", "value3", "value4", limitCode3);

         LimitDailyInfoKey limitDailyInfoKey12 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule4");

         LimitDailyInfo LimitDailyInfo12 = new LimitDailyInfo(now.minusDays(2), "site1", "rule4", limitDailyInfoKey12, "RED", "value1", "value2", "value3", "value4", limitCode4);

         LimitDailyInfoKey limitDailyInfoKey13 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule5");

         LimitDailyInfo LimitDailyInfo13 = new LimitDailyInfo(now.minusDays(2), "site1", "rule5", limitDailyInfoKey13, "RED", "value1", "value2", "value3", "value4", limitCode5);

         LimitDailyInfoKey limitDailyInfoKey14 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule6");

         LimitDailyInfo LimitDailyInfo14 = new LimitDailyInfo(now.minusDays(2), "site1", "rule6", limitDailyInfoKey14, "RED", "value1", "value2", "value3", "value4", limitCode6);

         LimitDailyInfoKey limitDailyInfoKey15 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule7");

         LimitDailyInfo LimitDailyInfo15 = new LimitDailyInfo(now.minusDays(2), "site1", "rule7", limitDailyInfoKey15, "RED", "value1", "value2", "value3", "value4", limitCode7);

         LimitDailyInfoKey limitDailyInfoKey16 = new LimitDailyInfoKey(now.minusDays(2), "site1", "rule8");

         LimitDailyInfo LimitDailyInfo16 = new LimitDailyInfo(now.minusDays(2), "site1", "rule8", limitDailyInfoKey16, "RED", "value1", "value2", "value3", "value4", limitCode8);


         LimitDailyInfoKey limitDailyInfoKey17 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule1");

         LimitDailyInfo LimitDailyInfo17 = new LimitDailyInfo(now.minusDays(3), "site1", "rule1", limitDailyInfoKey17, "RED", "value1", "value2", "value3", "value4", limitCode1);

         LimitDailyInfoKey limitDailyInfoKey18 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule2");

         LimitDailyInfo LimitDailyInfo18 = new LimitDailyInfo(now.minusDays(3), "site1", "rule2", limitDailyInfoKey18, "RED", "value1", "value2", "value3", "value4", limitCode2);

         LimitDailyInfoKey limitDailyInfoKey19 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule3");

         LimitDailyInfo LimitDailyInfo19 = new LimitDailyInfo(now.minusDays(3), "site1", "rule3", limitDailyInfoKey19, "RED", "value1", "value2", "value3", "value4", limitCode3);

         LimitDailyInfoKey limitDailyInfoKey20 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule4");

         LimitDailyInfo LimitDailyInfo20 = new LimitDailyInfo(now.minusDays(3), "site1", "rule4", limitDailyInfoKey20, "RED", "value1", "value2", "value3", "value4", limitCode4);

         LimitDailyInfoKey limitDailyInfoKey21 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule5");

         LimitDailyInfo LimitDailyInfo21 = new LimitDailyInfo(now.minusDays(3), "site1", "rule5", limitDailyInfoKey21, "RED", "value1", "value2", "value3", "value4", limitCode5);

         LimitDailyInfoKey limitDailyInfoKey22 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule6");

         LimitDailyInfo LimitDailyInfo22 = new LimitDailyInfo(now.minusDays(3), "site1", "rule6", limitDailyInfoKey22, "RED", "value1", "value2", "value3", "value4", limitCode6);

         LimitDailyInfoKey limitDailyInfoKey23 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule7");

         LimitDailyInfo LimitDailyInfo23 = new LimitDailyInfo(now.minusDays(3), "site1", "rule7", limitDailyInfoKey23, "RED", "value1", "value2", "value3", "value4", limitCode7);

         LimitDailyInfoKey limitDailyInfoKey24 = new LimitDailyInfoKey(now.minusDays(3), "site1", "rule8");

         LimitDailyInfo LimitDailyInfo24 = new LimitDailyInfo(now.minusDays(3), "site1", "rule8", limitDailyInfoKey24, "RED", "value1", "value2", "value3", "value4", limitCode8);


         LimitDailyInfoKey limitDailyInfoKey25 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule1");

         LimitDailyInfo LimitDailyInfo25 = new LimitDailyInfo(now.minusDays(4), "site1", "rule1", limitDailyInfoKey25, "RED", "value1", "value2", "value3", "value4", limitCode1);

         LimitDailyInfoKey limitDailyInfoKey26 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule2");

         LimitDailyInfo LimitDailyInfo26 = new LimitDailyInfo(now.minusDays(4), "site1", "rule2", limitDailyInfoKey26, "RED", "value1", "value2", "value3", "value4", limitCode2);

         LimitDailyInfoKey limitDailyInfoKey27 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule3");

         LimitDailyInfo LimitDailyInfo27 = new LimitDailyInfo(now.minusDays(4), "site1", "rule3", limitDailyInfoKey27, "RED", "value1", "value2", "value3", "value4", limitCode3);

         LimitDailyInfoKey limitDailyInfoKey28 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule4");

         LimitDailyInfo LimitDailyInfo28 = new LimitDailyInfo(now.minusDays(4), "site1", "rule4", limitDailyInfoKey28, "RED", "value1", "value2", "value3", "value4", limitCode4);

         LimitDailyInfoKey limitDailyInfoKey29 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule5");

         LimitDailyInfo LimitDailyInfo29 = new LimitDailyInfo(now.minusDays(4), "site1", "rule5", limitDailyInfoKey29, "RED", "value1", "value2", "value3", "value4", limitCode5);

         LimitDailyInfoKey limitDailyInfoKey30 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule6");

         LimitDailyInfo LimitDailyInfo30 = new LimitDailyInfo(now.minusDays(4), "site1", "rule6", limitDailyInfoKey30, "RED", "value1", "value2", "value3", "value4", limitCode6);

         LimitDailyInfoKey limitDailyInfoKey31 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule7");

         LimitDailyInfo LimitDailyInfo31 = new LimitDailyInfo(now.minusDays(4), "site1", "rule7", limitDailyInfoKey31, "RED", "value1", "value2", "value3", "value4", limitCode7);

         LimitDailyInfoKey limitDailyInfoKey32 = new LimitDailyInfoKey(now.minusDays(4), "site1", "rule8");

         LimitDailyInfo LimitDailyInfo32 = new LimitDailyInfo(now.minusDays(4), "site1", "rule8", limitDailyInfoKey32, "RED", "value1", "value2", "value3", "value4", limitCode8);


         LimitDailyInfoKey limitDailyInfoKey33 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule1");

         LimitDailyInfo LimitDailyInfo33 = new LimitDailyInfo(now.minusDays(5), "site1", "rule1", limitDailyInfoKey33, "RED", "value1", "value2", "value3", "value4", limitCode1);

         LimitDailyInfoKey limitDailyInfoKey34 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule2");

         LimitDailyInfo LimitDailyInfo34 = new LimitDailyInfo(now.minusDays(5), "site1", "rule2", limitDailyInfoKey34, "RED", "value1", "value2", "value3", "value4", limitCode2);

         LimitDailyInfoKey limitDailyInfoKey35 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule3");

         LimitDailyInfo LimitDailyInfo35 = new LimitDailyInfo(now.minusDays(5), "site1", "rule3", limitDailyInfoKey35, "RED", "value1", "value2", "value3", "value4", limitCode3);

         LimitDailyInfoKey limitDailyInfoKey36 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule4");

         LimitDailyInfo LimitDailyInfo36 = new LimitDailyInfo(now.minusDays(5), "site1", "rule4", limitDailyInfoKey36, "RED", "value1", "value2", "value3", "value4", limitCode4);

         LimitDailyInfoKey limitDailyInfoKey37 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule5");

         LimitDailyInfo LimitDailyInfo37 = new LimitDailyInfo(now.minusDays(5), "site1", "rule5", limitDailyInfoKey37, "RED", "value1", "value2", "value3", "value4", limitCode5);

         LimitDailyInfoKey limitDailyInfoKey38 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule6");

         LimitDailyInfo LimitDailyInfo38 = new LimitDailyInfo(now.minusDays(5), "site1", "rule6", limitDailyInfoKey38, "RED", "value1", "value2", "value3", "value4", limitCode6);

         LimitDailyInfoKey limitDailyInfoKey39 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule7");

         LimitDailyInfo LimitDailyInfo39 = new LimitDailyInfo(now.minusDays(5), "site1", "rule7", limitDailyInfoKey39, "RED", "value1", "value2", "value3", "value4", limitCode7);

         LimitDailyInfoKey limitDailyInfoKey40 = new LimitDailyInfoKey(now.minusDays(5), "site1", "rule8");

         LimitDailyInfo LimitDailyInfo40 = new LimitDailyInfo(now.minusDays(5), "site1", "rule8", limitDailyInfoKey40, "RED", "value1", "value2", "value3", "value4", limitCode8);


         em.persist(LimitDailyInfo1);

         em.persist(LimitDailyInfo2);

         em.persist(LimitDailyInfo3);

         em.persist(LimitDailyInfo4);

         em.persist(LimitDailyInfo5);

         em.persist(LimitDailyInfo6);

         em.persist(LimitDailyInfo7);

         em.persist(LimitDailyInfo8);

         em.persist(LimitDailyInfo9);

         em.persist(LimitDailyInfo10);

         em.persist(LimitDailyInfo11);

         em.persist(LimitDailyInfo12);

         em.persist(LimitDailyInfo13);

         em.persist(LimitDailyInfo14);

         em.persist(LimitDailyInfo15);

         em.persist(LimitDailyInfo16);

         em.persist(LimitDailyInfo17);

         em.persist(LimitDailyInfo18);

         em.persist(LimitDailyInfo19);

         em.persist(LimitDailyInfo20);

         em.persist(LimitDailyInfo21);

         em.persist(LimitDailyInfo22);

         em.persist(LimitDailyInfo23);

         em.persist(LimitDailyInfo24);

         em.persist(LimitDailyInfo25);

         em.persist(LimitDailyInfo26);

         em.persist(LimitDailyInfo27);

         em.persist(LimitDailyInfo28);

         em.persist(LimitDailyInfo29);

         em.persist(LimitDailyInfo30);

         em.persist(LimitDailyInfo31);

         em.persist(LimitDailyInfo32);

         em.persist(LimitDailyInfo33);

         em.persist(LimitDailyInfo34);

         em.persist(LimitDailyInfo35);

         em.persist(LimitDailyInfo36);

         em.persist(LimitDailyInfo37);

         em.persist(LimitDailyInfo38);

         em.persist(LimitDailyInfo39);

         em.persist(LimitDailyInfo40);
         **/

    }


    @Test
    public void springDataJpaTest() {
    }


    @Test
    public void QueryDslTest() {
        queryFactory = new JPAQueryFactory(em);

        QLimitUserMntrng mntrngSub3 = new QLimitUserMntrng("mntrngSub3");

        long count = queryFactory.select(QLimitUserInfo.limitUserInfo)

                .from(QLimitUserInfo.limitUserInfo)

                .innerJoin(QLimitUserMntrng.limitUserMntrng)

                .on(

                        QLimitUserInfo.limitUserInfo.userId.eq(QLimitUserMntrng.limitUserMntrng.userId),

                        QLimitUserInfo.limitUserInfo.dsbrdNm.eq(QLimitUserMntrng.limitUserMntrng.dsbrdNm)

                )

                .where(QLimitUserMntrng.limitUserMntrng.mntrngDt.eq(

                        JPAExpressions

                                .select(mntrngSub3.mntrngDt.max())

                                .from(mntrngSub3)

                ))

                .fetchCount();


        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        System.out.println(count);

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        QLimitUserMntrng mntrngSub2 = new QLimitUserMntrng("mntrngSub2");

        List<Tuple> tuples = queryFactory.select(

                        QLimitUserInfo.limitUserInfo.lmtRuleLclasDesc,

                        QLimitUserInfo.limitUserInfo.dsbrdNm

                )

                .from(QLimitUserInfo.limitUserInfo)

                .innerJoin(QLimitUserMntrng.limitUserMntrng)

                .on(

                        QLimitUserInfo.limitUserInfo.userId.eq(QLimitUserMntrng.limitUserMntrng.userId),

                        QLimitUserInfo.limitUserInfo.dsbrdNm.eq(QLimitUserMntrng.limitUserMntrng.dsbrdNm)

                )

                .where(QLimitUserMntrng.limitUserMntrng.mntrngDt.eq(

                                JPAExpressions

                                        .select(mntrngSub2.mntrngDt.max())

                                        .from(mntrngSub2)

                        )

                        , QLimitUserInfo.limitUserInfo.userId.eq("geun1")

                )

                .orderBy(QLimitUserInfo.limitUserInfo.dsbrdNm.asc())

                .fetch();


        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        for (Tuple tuple : tuples) {

            System.out.println("tuple :" + tuple);

        }

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");


        QLimitUserMntrng mntrngSub = new QLimitUserMntrng("mntrngSub");

        List<Tuple> tuples1 = queryFactory.select(

                        QLimitUserDtl.limitUserDtl.dsbrdNm,

                        QLimitUserDtl.limitUserDtl.lmtRuleCode

                )

                .from(QLimitUserDtl.limitUserDtl)

                .leftJoin(QLimitCode.limitCode)

                .on(

                        QLimitUserDtl.limitUserDtl.lmtRuleCode.eq(QLimitCode.limitCode.lmtRuleCode)

                )

                .innerJoin(QLimitDailyInfo.limitDailyInfo)

                .on(

                        QLimitCode.limitCode.lmtRuleCode.eq(QLimitDailyInfo.limitDailyInfo.lmtRuleCode)

                )

                .where(QLimitDailyInfo.limitDailyInfo.mntrngDt.eq(

                                JPAExpressions

                                        .select(mntrngSub.mntrngDt.max())

                                        .from(mntrngSub)

                                        .where(

                                                mntrngSub.userId.eq(QLimitUserDtl.limitUserDtl.userId),

                                                mntrngSub.dsbrdNm.eq(QLimitUserDtl.limitUserDtl.dsbrdNm)

                                        )

                        )

                        , QLimitUserDtl.limitUserDtl.userId.eq("geun1")

                )

                .orderBy(QLimitDailyInfo.limitDailyInfo.lmtRuleCode.asc(), QLimitDailyInfo.limitDailyInfo.site_nm_detail.asc())

                .fetch();


        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        for (Tuple tuple : tuples1) {

            System.out.println("tuple :" + tuple);

        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");


    }


}