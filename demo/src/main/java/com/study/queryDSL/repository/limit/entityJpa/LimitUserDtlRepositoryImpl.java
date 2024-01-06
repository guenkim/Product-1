package com.study.queryDSL.repository.limit.entityJpa;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitUserDtlRepositoryCustom;


import javax.persistence.EntityManager;


public class LimitUserDtlRepositoryImpl implements LimitUserDtlRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public LimitUserDtlRepositoryImpl(EntityManager em) {

        this.jpaQueryFactory = new JPAQueryFactory(em);

    }

}