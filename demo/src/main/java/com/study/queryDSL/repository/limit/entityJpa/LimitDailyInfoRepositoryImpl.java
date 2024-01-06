package com.study.queryDSL.repository.limit.entityJpa;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitDailyInfoRepositoryCustom;


import javax.persistence.EntityManager;


public class LimitDailyInfoRepositoryImpl implements LimitDailyInfoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public LimitDailyInfoRepositoryImpl(EntityManager em) {

        this.jpaQueryFactory = new JPAQueryFactory(em);

    }

}