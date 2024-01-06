package com.study.contents.repository.jpa;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentListDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.vo.ContentsVO;
import org.springframework.data.domain.Pageable;

public interface ContentJpaQueryDSLExtend {
    public PageDTO<ContentListDTO> findQueryQueryDslBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);

}

