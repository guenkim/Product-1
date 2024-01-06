package com.study.queryDSL.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.study.queryDSL.dto.MemberSearchCondition;
import com.study.queryDSL.dto.MemberTeamDto;
import com.study.queryDSL.repository.MemberPureJpaAndQueryDslRepository;
import com.study.queryDSL.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    /**
     * 순수 jpa repository
     **/
    private final MemberPureJpaAndQueryDslRepository memberPureJpaAndQueryDslRepository;

    /**
     * spring data repository
     **/
    private final MemberRepository memberRepository;


    /****************************************************
     *  queryDSL repository 조회 메서드 호출 api
     ***************************************************/
    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberPureJpaAndQueryDslRepository.searchWhere(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition,
                                              Pageable pageable) {
        return memberRepository.searchPageSimple(condition, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition,
                                              Pageable pageable) {
        return memberRepository.searchPageComplex(condition, pageable);
    }
}
