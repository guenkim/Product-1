package com.study.contents.repository.jpa;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.vo.ContentsVO;
import org.springframework.data.domain.Pageable;

public interface ContentJpaExtend {
    public PageDTO<ContentsVO> findQueryBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);
}

