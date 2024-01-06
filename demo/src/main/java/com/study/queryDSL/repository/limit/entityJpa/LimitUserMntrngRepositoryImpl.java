package com.study.queryDSL.repository.limit.entityJpa;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitUserMntrngRepositoryCustom;


import javax.persistence.EntityManager;


public class LimitUserMntrngRepositoryImpl implements LimitUserMntrngRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    public LimitUserMntrngRepositoryImpl(EntityManager em) {

        this.jpaQueryFactory = new JPAQueryFactory(em);

    }

}