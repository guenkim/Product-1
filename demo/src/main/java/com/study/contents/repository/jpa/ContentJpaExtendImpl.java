package com.study.contents.repository.jpa;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.vo.ContentsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ContentJpaExtendImpl implements ContentJpaExtend {
    private static final String KEYWORD = "keyword";
    @PersistenceContext
    EntityManager em;

    @Override
    public PageDTO<ContentsVO> findQueryBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable) {
        StringBuilder where = new StringBuilder("");
        where.append("WHERE UPPER(b.sttcd) != 'D' and b.useyn !='N' ");
        Map<String, String> paramMap = new HashMap<String, String>();

        if (contentSearchDTO.getKeyword() != null) {
            if (where.toString() != "") {
                where.append("AND ");
            }
            where.append("(UPPER(b.contentId) LIKE :keyword or UPPER(b.contentNm) LIKE :keyword) ");
            paramMap.put(KEYWORD, "%" + contentSearchDTO.getKeyword().toUpperCase() + "%");
        }

        /* order by 처리 **/
        Sort sort = pageable.getSort();
        String sortStr = sort.toString();
        String orderBy = "";

        System.out.println("#############################");
        System.out.println("sort : " + sortStr);
        System.out.println("#############################");

        if (StringUtils.isNoneBlank(sortStr)) {
            orderBy += " ORDER BY ";
            orderBy += sortStr.replaceAll(":", "");
        }

        String query = " SELECT b FROM TB_CONTENTS b " + where.toString() + orderBy;
        //String query = " SELECT b FROM TB_CONTENTS b " + where.toString() ;
        TypedQuery<ContentsVO> typedQuery = em.createQuery(query, ContentsVO.class);
        paramMap.forEach((String key, String value) -> typedQuery.setParameter(key, value));
        PageDTO<ContentsVO> result = new PageDTO<ContentsVO>();
        result.setTotalCount(typedQuery.getResultList().size());
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        result.setList(typedQuery.getResultList());
        result.setPage(pageable);
        return result;
    }
}

