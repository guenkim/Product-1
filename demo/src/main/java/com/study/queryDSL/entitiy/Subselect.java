package com.study.queryDSL.entitiy;

import lombok.Getter;

import lombok.Setter;


import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;


@Entity

@Getter

@Setter

// native 쿼리 사용

@org.hibernate.annotations.Subselect("select m.member_id ,m.username , t.team_id, t.name , m.age  from member m left outer join team t on m.team_id = t.team_id")

public class Subselect {


    @Id

    @Column(name = "member_id")

    private Long id;


    private String username;


    private Long team_id;


    private String name;


    private int age;

}