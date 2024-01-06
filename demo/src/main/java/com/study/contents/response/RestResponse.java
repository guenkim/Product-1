package com.study.contents.response;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.MessageDisplayType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * 공통 응답전문 형식
 * {
 * "data": [],
 * "meta": {
 * "userMessage": "",
 * "systemMessage": "",
 * "displayType": null,
 * "code": "",
 * "page": {
 * "sort": {
 * "empty": false,
 * "sorted": true,
 * "unsorted": false
 * },
 * "offset": 5,
 * "pageNumber": 1,
 * "pageSize": 5,
 * "paged": true,
 * "unpaged": false
 * },
 * "totalCount": 0
 * }
 * }
 */

public class RestResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Object data;

    @Getter
    @Setter
    private RestResponseMeta meta = new RestResponseMeta();

    public RestResponse() {
    }

    public RestResponse(Object data) {
        this.data = data;
        if (data instanceof PageDTO) {
            this.data = ((PageDTO) data).getList();
            this.meta.setPage(((PageDTO) data).getPage());
            this.meta.setTotalCount(((PageDTO) data).getTotalCount());
        }
    }

    public void setUserMessage(String message) {
        this.meta.setUserMessage(message);
    }

    public void setSystemMessage(String systemMessage) {
        this.meta.setSystemMessage(systemMessage);
    }

    public void setDisplayType(MessageDisplayType displayType) {
        this.meta.setDisplayType(displayType);
    }

    public void setCode(String code) {
        this.meta.setCode(code);
    }

    public RestResponse userMessage(String userMessage) {
        this.setUserMessage(userMessage);
        return this;
    }

    public RestResponse systemMessage(String systemMessage) {
        this.setSystemMessage(systemMessage);
        return this;
    }

    public RestResponse displayType(MessageDisplayType displayType) {
        this.setDisplayType(displayType);
        return this;
    }

    public RestResponse code(String code) {
        this.setCode(code);
        return this;
    }

    @Getter
    @Setter
    public class RestResponseMeta {
        private static final long serialVersionUID = 1L;
        private String userMessage = "";
        private String systemMessage = "";
        private MessageDisplayType displayType;
        private String code = "";
        private Pageable page;
        private int totalCount;
    }
}

