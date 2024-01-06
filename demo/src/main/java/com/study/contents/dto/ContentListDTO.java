package com.study.contents.dto;

import com.study.common.dto.BaseSttDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentListDTO extends BaseSttDTO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            name = "contentId"
            , example = "123"
    )
    @ApiParam(value = "컨텐트아이디", required = true)
    private int contentId;


    @ApiModelProperty(
            name = "contentNm"
            , example = "컨텐트 이름"
    )
    @ApiParam(value = "컨텐트명", required = false)
    private String contentNm;

    @ApiModelProperty(
            name = "content"
            , example = "컨텐트"
    )
    @ApiParam(value = "컨텐트", required = false)
    private String content;

    public ContentListDTO(int contentId, String contentNm, String content, String regId, LocalDateTime regDt, String modId, LocalDateTime modDt) {
        this.contentId = contentId;
        this.contentNm = contentNm;
        this.content = content;
        this.setRegid(regId);
        this.setRegdt(regDt);
        this.setModid(modId);
        this.setModdt(modDt);
    }
}