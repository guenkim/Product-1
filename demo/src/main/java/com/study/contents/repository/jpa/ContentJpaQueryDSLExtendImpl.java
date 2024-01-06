package com.study.contents.repository.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentListDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.vo.ContentsVO;
import com.study.contents.vo.QContentsVO;
import com.study.user.vo.QUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;

import static com.study.contents.vo.QContentsVO.contentsVO;

public class ContentJpaQueryDSLExtendImpl extends QuerydslRepositorySupport implements ContentJpaQueryDSLExtend {
    JPAQueryFactory queryFactory;

    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public ContentJpaQueryDSLExtendImpl() {
        super(ContentsVO.class);
    }

    @Override
    public PageDTO<ContentListDTO> findQueryQueryDslBySearch(ContentSearchDTO search, Pageable pageable) {
        PageDTO<ContentListDTO> result = new PageDTO<ContentListDTO>();
        QUserVO regUserVO = new QUserVO("regUserVO");
        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(contentsVO.sttcd.ne("D"));
        whereBuilder.and(contentsVO.useyn.ne(false));

        if (search.getContentId() != null && !search.getContentId().equals("")) {
            //whereBuilder.and(contentsVO.contentId.upper().like("%" + search.getContentId().toUpperCase() + "%"));
            whereBuilder.and(contentsVO.contentId.eq(Integer.parseInt(search.getContentId())));
        }

        if (search.getContentNm() != null) {
            whereBuilder.and(contentsVO.contentNm.upper().like("%" + search.getContentNm().toUpperCase() + "%"));
        }

        if (search.getContent() != null) {
            whereBuilder.and(contentsVO.content.upper().like("%" + search.getContent().toUpperCase() + "%"));
        }

        QueryResults<ContentListDTO> data = queryFactory.select(Projections.constructor(ContentListDTO.class,
                        contentsVO.contentId,
                        contentsVO.contentNm,
                        contentsVO.content,
                        contentsVO.regid,
                        contentsVO.regdt,
                        contentsVO.modid,
                        contentsVO.moddt
                ))
                .from(contentsVO)
                //.leftJoin(regUserVO).on(articleVO.regid.eq(regUserVO.loginid))
                .where(whereBuilder)
                .orderBy(contentsVO.contentId.desc())
                .offset((int) pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        result.setTotalCount((int) data.getTotal());
        result.setPage(pageable);
        result.setList(data.getResults());
        return result;
    }
}

