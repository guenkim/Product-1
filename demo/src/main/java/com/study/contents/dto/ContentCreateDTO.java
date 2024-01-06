package com.study.contents.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "ContentCreateDTO : 컨텐트 등록 정보", description = "컨텐트 등록 정보 Domain Class")
public class ContentCreateDTO {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            name = "contentNm"
            , example = "컨텐트명"
            , value = "컨텐트명"
            , required = true
    )
    @NotBlank
    @Length(max = 128)
    private String contentNm;


    @ApiModelProperty(
            name = "content"
            , example = "컨텐트"
            , value = "컨텐트"
            , required = true
    )
    @NotBlank
    @Length(max = 1000)
    private String content;

}
