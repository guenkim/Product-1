package com.study.contents.response;

import com.study.common.dto.PageDTO;
import com.study.contents.dto.MessageDisplayType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * toast ui 응답전문 형식
 * {
 * "result": true,
 * "data":
 * {
 * "contents": [],
 * "pagination":
 * {
 * "page": 1,
 * "totalCount": 100
 * }
 * }
 * }
 * <p>
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

public class ToastUIRestResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    public ToastUIRestResponse() {
    }

    @Getter
    @Setter
    private boolean result = true;

    @Getter
    @Setter
    private RestResponseData data = new RestResponseData();

    public ToastUIRestResponse(Object data) {
        if (data instanceof PageDTO) {
            //this.data = ((PageDTO) data).getList();
            this.data.setContents(((PageDTO) data).getList());
            /** jpa는 paging을 0 부터 시작한다. 따라서 화면으로 넘겨줄 때는 현재 페이지를 +1 처리 **/
            this.data.pagination.setPage(((PageDTO<?>) data).getPage().getPageNumber() + 1);
            this.data.pagination.setTotalCount(((PageDTO<?>) data).getTotalCount());
        }
    }

    @Getter
    @Setter
    public class RestResponseData {
        private static final long serialVersionUID = 1L;
        private List<Object> contents;
        private RestResponsePagination pagination = new RestResponsePagination();

        @Getter
        @Setter
        public class RestResponsePagination {
            private static final long serialVersionUID = 1L;
            private int page;
            private int totalCount;
        }
    }
}

