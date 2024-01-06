package com.study.queryDSL.repository.limit.entityJpa;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitUserInfoRepositoryCustom;


import javax.persistence.EntityManager;


public class LimitUserInfoRepositoryImpl implements LimitUserInfoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public LimitUserInfoRepositoryImpl(EntityManager em) {

        this.jpaQueryFactory = new JPAQueryFactory(em);

    }

}