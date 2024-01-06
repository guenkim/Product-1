package com.study.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@ApiModel(value = "BaseDTO : 등록자,등록일자,수정자,수정일자 정보", description = "등록자,등록일자,수정자,수정일자 정보 Domain Class")
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionID = 1L;

    @ApiModelProperty(
            name = "regid"
            , example = "user1"
            , value = "등록자아이디"
            , required = false
    )
    @ApiParam(value = "등록자아이디", required = false)
    protected String regid;

    @ApiModelProperty(
            name = "modid"
            , example = "user1"
            , value = "수정자아이디"
            , required = false
    )
    @ApiParam(value = "수정자아이디", required = false)
    protected String modid;

    @ApiModelProperty(
            name = "regdt"
            , example = "yyyy-MM-dd HH:mm:ss"
            , value = "등록일자"
            , required = false
    )
    @ApiParam(value = "등록일자", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime regdt;


    @ApiModelProperty(
            name = "moddt"
            , example = "yyyy-MM-dd HH:mm:ss"
            , value = "수정일자"
            , required = false
    )
    @ApiParam(value = "수정일자", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime moddt;

}
