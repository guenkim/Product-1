package com.study.contents.dto;

import com.study.common.dto.BaseSttDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "ContentUpdateDTO : 컨텐트 수정 정보", description = "컨텐트 수정 정보 Domain Class")
//public class ContentUpdateDTO extends BaseSttDTO{
public class ContentUpdateDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            name = "contentId"
            , example = "123"
            , value = "아이디"
            , required = true
    )
    @NotBlank
    private String contentId;

    @ApiModelProperty(
            name = "contentNm"
            , example = "컨텐트 이름"
            , value = "컨텐트 이름"
            , required = false
    )
    @NotBlank
    @Length(max = 128)
    private String contentNm;

    @ApiModelProperty(
            name = "content"
            , example = "컨텐트"
            , value = "컨텐트"
            , required = false
    )
    @NotBlank
    @Length(max = 1000)
    private String content;
}