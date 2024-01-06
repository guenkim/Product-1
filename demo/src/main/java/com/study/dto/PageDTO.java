package com.study.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class PageDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<T> list;
    private Pageable page;
    private int totalCount;

}