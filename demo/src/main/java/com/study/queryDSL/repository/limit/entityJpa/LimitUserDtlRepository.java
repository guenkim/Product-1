package com.study.queryDSL.repository.limit.entityJpa;

import com.study.queryDSL.entitiy.LimitUserDtl;

import com.study.queryDSL.entitiy.key.LimitDailyInfoKey;

import com.study.queryDSL.entitiy.key.LimitUserDtlKey;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitUserDtlRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;


/*************************************************************************************************************
 * 스프링 데이터 리포지토리 , 사용자 정의 레포지토리(queryDSL) 상속
 *
 * /*****************************
 *  * 사용자 정의 리포지토리
 *  * 사용자 정의 리포지토리 사용법
 *  * 1. 사용자 정의 인터페이스 작성
 *  * 2. 사용자 정의 인터페이스 구현
 *  * 3. 스프링 데이터 리포지토리에 사용자 정의 인터페이스 상속
 *************************************************************************************************************/


public interface LimitUserDtlRepository extends JpaRepository<LimitUserDtl, LimitUserDtlKey>,

        LimitUserDtlRepositoryCustom {


}