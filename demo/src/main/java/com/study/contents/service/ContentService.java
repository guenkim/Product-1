package com.study.contents.service;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentCreateDTO;
import com.study.contents.dto.ContentListDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.dto.ContentUpdateDTO;
import com.study.contents.vo.ContentsVO;
import org.springframework.data.domain.Pageable;

public interface ContentService {
    public PageDTO<ContentsVO> findQueryBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);

    public PageDTO<ContentListDTO> findQueryQueryDslBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable);

    ContentsVO create(ContentCreateDTO contentCreateDTO);

    ContentsVO update(ContentUpdateDTO contentUpdateDTO);

    ContentsVO delete(int contentId);

    ContentsVO findById(int contentId);
}

