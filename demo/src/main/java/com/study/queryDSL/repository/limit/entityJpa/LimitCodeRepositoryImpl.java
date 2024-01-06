package com.study.queryDSL.repository.limit.entityJpa;


import com.querydsl.jpa.impl.JPAQueryFactory;

import com.study.queryDSL.repository.MemberRepositoryCustom;

import com.study.queryDSL.repository.limit.entityFusionJpa.LimitCodeRepositoryCustom;


import javax.persistence.EntityManager;


public class LimitCodeRepositoryImpl implements LimitCodeRepositoryCustom {


    private final JPAQueryFactory jpaQueryFactory;


    public LimitCodeRepositoryImpl(EntityManager em) {

        this.jpaQueryFactory = new JPAQueryFactory(em);

    }

}