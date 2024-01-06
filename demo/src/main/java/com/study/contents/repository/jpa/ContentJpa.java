package com.study.contents.repository.jpa;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentListDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.vo.ContentsVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJpa extends JpaRepository<ContentsVO, Integer>, ContentJpaExtend, ContentJpaQueryDSLExtend {
    public PageDTO<ContentsVO> findQueryBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);

    public PageDTO<ContentListDTO> findQueryQueryDslBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);
}
