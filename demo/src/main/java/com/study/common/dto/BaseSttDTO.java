package com.study.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@ApiModel(value = "BaseSttDTO : 상태코드 , 사용여부 정보", description = "상태코드 , 사용여부 정보 Domain Class")
public abstract class BaseSttDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            name = "sttcd"
            , example = "I"
            , value = "상태코드"
            , required = false
    )
    private String sttcd;

    @ApiModelProperty(
            name = "useyn"
            , example = "true"
            , value = "사용여부"
            , required = false
    )
    @Type(type = "yes_no")
    private boolean useyn = true;

}
