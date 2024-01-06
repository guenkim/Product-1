package com.study.contents.service;


import com.study.common.dto.PageDTO;
import com.study.contents.dto.ContentCreateDTO;
import com.study.contents.dto.ContentListDTO;
import com.study.contents.dto.ContentSearchDTO;
import com.study.contents.dto.ContentUpdateDTO;
import com.study.contents.repository.jpa.ContentJpa;
import com.study.contents.vo.ContentsVO;
import com.study.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentJpa contentJpa;

    @Override
    public PageDTO<ContentsVO> findQueryBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable) {
        return contentJpa.findQueryBySearch(contentSearchDTO, pageable);
    }

    @Override
    public PageDTO<ContentListDTO> findQueryQueryDslBySearch(ContentSearchDTO contentSearchDTO, Pageable pageable) {
        return contentJpa.findQueryQueryDslBySearch(contentSearchDTO, pageable);
    }

    @Override
    public ContentsVO create(ContentCreateDTO contentCreateDTO) {
        ContentsVO saveContentsVO = new ContentsVO(contentCreateDTO.getContentNm(), contentCreateDTO.getContent());
        ContentsVO contentsVO = contentJpa.save(saveContentsVO);
        return contentsVO;
    }

    @Override
    public ContentsVO update(ContentUpdateDTO contentUpdateDTO) {
        ContentsVO updateContentVO = contentJpa.findById(Integer.parseInt(contentUpdateDTO.getContentId())).orElse(null);
        if (updateContentVO == null) {
            //throw new NotFoundException("");
        }
        updateContentVO.setContentNm(contentUpdateDTO.getContentNm());
        updateContentVO.setContent(contentUpdateDTO.getContent());
        ContentsVO contentsVO = contentJpa.save(updateContentVO);
        return contentsVO;
    }

    @Override
    public ContentsVO delete(int contentId) {
        ContentsVO updateContentVO = contentJpa.findById(contentId).orElse(null);
        if (updateContentVO == null) {
            //throw new NotFoundException("");
        }
        updateContentVO.setUseyn(false);
        ContentsVO contentsVO = contentJpa.save(updateContentVO);
        return contentsVO;
    }

    @Override
    public ContentsVO findById(int contentId) {
        ContentsVO contentsVO = contentJpa.findById(contentId).orElse(null);
        if (contentsVO == null) {
            //throw new Exception("게시글이 없습니다.");
        }
        return contentsVO;
    }
}

