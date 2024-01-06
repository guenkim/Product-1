package com.study.spring_data_jpa.repository.springDataJpa;


import com.study.queryDSL.repository.MemberPureJpaAndQueryDslRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import com.study.queryDSL.dto.MemberSearchCondition;
import com.study.queryDSL.dto.MemberTeamDto;
import com.study.spring_data_jpa.entitiy.Team;
import com.study.spring_data_jpa.entitiy.Member;
import com.study.queryDSL.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class MemberPureJpaAndQueryDslRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberPureJpaAndQueryDslRepository memberPureJpaAndQueryDslRepository; //순수 JPA Repository (queryDsl 포함)

    @Autowired
    MemberRepository memberRepository; //Spring Data Repository (사용자 정의 repository 포함)


    /*************************************************************************************************************
     * 순수 JPA 리포지토리와 Querydsl
     *************************************************************************************************************/
    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberPureJpaAndQueryDslRepository.save(member);

        Member findMember = memberPureJpaAndQueryDslRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(findMember);

        List<Member> result1 = memberPureJpaAndQueryDslRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberPureJpaAndQueryDslRepository.findAll_Querydsl();
        assertThat(result2).containsExactly(member);


        List<Member> result3 = memberPureJpaAndQueryDslRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);

        List<Member> result4 = memberPureJpaAndQueryDslRepository.findByUsername_Querydsl("member1");
        assertThat(result2).containsExactly(member);
    }


    /*************************************************************************************************************
     * * 조회 예제 테스트
     * Querydsl
     * BooleanBuilder
     * Where절에 파라미터를 사용한 예제
     *************************************************************************************************************/
    @Test
    public void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");
        List<MemberTeamDto> result =
/**********************
 * booleanbuilder
 * where 파라미터 사용 예시
 **********************/
//memberJPARepository.searchByBuilder(condition); //booleanBuilder 사용
                memberPureJpaAndQueryDslRepository.searchWhere(condition); //where 파라미터 사용

        assertThat(result).extracting("username").containsExactly("member4");
    }

    /*************************************************************************************************************
     * 조회 예제 테스트
     Querydsl
     BooleanBuilder
     Where절에 파라미터를 사용한 예제
     사용자 정의 repository 이용
     *************************************************************************************************************/
    @Test
    public void searchCustomTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");
        List<MemberTeamDto> result =
/**********************
 * booleanbuilder
 * where 파라미터 사용 예시
 **********************/
//memberJPARepository.searchByBuilder(condition); //booleanBuilder 사용
                memberRepository.search(condition); //where 파라미터 사용

        assertThat(result).extracting("username").containsExactly("member4");
    }


    /*************************************************************************************************************
     * queryDsl paging test
     * 단순한 페이징, fetchResults() 사용
     *************************************************************************************************************/
    @Test
    public void searchSimplePage() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();

//jpa는 page index가 0
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("age").ascending());
//condition.setAgeGoe(35);
//condition.setAgeLoe(40);
//condition.setTeamName("teamB");

        Page<MemberTeamDto> result =
/**********************
 * booleanbuilder
 * where 파라미터 사용 예시
 **********************/
//memberJPARepository.searchByBuilder(condition); //booleanBuilder 사용
                memberRepository.searchPageSimple(condition, pageRequest); //where 파라미터 사용

        assertThat(result.getContent()).extracting("username").containsExactly("member1", "member2", "member3");
    }

    /*************************************************************************************************************
     * 복잡한 페이징
     * 데이터 조회 쿼리와, 전체 카운트 쿼리를 분리
     *************************************************************************************************************/
    @Test
    public void searchPageComplex() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();

//jpa는 page index가 0
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("age").ascending().and(Sort.by("username").ascending()).and(Sort.by("name").ascending()));
//PageRequest pageRequest = PageRequest.of(0, 3,Sort.by("id").descending().and(Sort.by("username").descending()));
        condition.setUsername("member1");
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        Page<MemberTeamDto> result =
/**********************
 * booleanbuilder
 * where 파라미터 사용 예시
 **********************/
//memberJPARepository.searchByBuilder(condition); //booleanBuilder 사용
                memberRepository.searchPageComplex(condition, pageRequest); //where 파라미터 사용
        for (MemberTeamDto memberTeamDto : result) {
            System.out.println("memberTeamDto :" + memberTeamDto);
        }

//assertThat(result.getContent()).extracting("username").containsExactly("member1", "member2", "member3");
    }

}