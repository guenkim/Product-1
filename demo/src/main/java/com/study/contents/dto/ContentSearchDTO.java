package com.study.contents.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ContentSearchDTO {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            name = "keyword"
            , example = "내용검색"
            , value = "키워드"
            , required = false
    )
    //@ApiParam(value = "키워드", required = false)
    @Length(max = 20)
    private String keyword;

    @ApiModelProperty(
            name = "contentId"
            , example = "123"
            , value = "컨텐트아이디"
            , required = false
    )
    //@ApiParam(value = "컨텐트아이디", required = false)
    private String contentId;


    @ApiModelProperty(
            name = "contentNm"
            , example = "컨텐트 이름"
            , value = "컨텐트명"
            , required = false
    )
    //@ApiParam(value = "컨텐트명", required = false)
    private String contentNm;

    @ApiModelProperty(
            name = "content"
            , example = "컨텐트"
            , value = "컨텐트"
            , required = false
    )
    private String content;

}

