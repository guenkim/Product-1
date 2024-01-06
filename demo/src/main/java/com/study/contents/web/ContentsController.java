package com.study.contents.web;

import com.study.component.MessageComponent;

import com.study.contents.dto.ContentCreateDTO;

import com.study.contents.dto.ContentSearchDTO;

import com.study.contents.dto.ContentUpdateDTO;

import com.study.contents.response.RestResponse;

import com.study.contents.response.ToastUIRestResponse;

import com.study.contents.service.ContentService;

import io.swagger.annotations.ApiImplicitParam;

import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;


@RestController


public class ContentsController {


    @Autowired

    ContentService contentService;



    /* -------------------------------------------------------------

        VIEW

    --------------------------------------------------------------*/

    @GetMapping(value = "/view/list")

    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.header.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.list.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.alert.*");

        modelAndView.setViewName("/contents/list");

        return modelAndView;

    }


    @GetMapping(value = "/view/create")


    public ModelAndView create() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.header.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.create.label.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.alert.*");

        modelAndView.setViewName("/contents/create");

        return modelAndView;

    }


    @GetMapping(value = "/view/update/{contentId}")

    public ModelAndView update(@PathVariable int contentId, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.header.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.update.label.*");

        modelAndView.addObject(MessageComponent.JS_MESSAGES_KEY_PATTERN, "cmn.message.view.alert.*");

        modelAndView.setViewName("/contents/update");

        model.addAttribute("contentId", contentId);

        return modelAndView;

    }


    @GetMapping(value = "/view/detail")

    public ModelAndView viewArticleList() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/contents/detail");

        return modelAndView;

    }



    /* -------------------------------------------------------------

        API

    --------------------------------------------------------------*/

    /**
     * typedQuery로 작성한 게시글 조회
     * <p>
     * 페이징 포함
     *
     * @return
     */

    @ApiOperation(value = "typedQuery로 작성한 게시글 조회 (페이징 포함)"

            , notes = "typed query 게시글조회")

    @GetMapping(value = "/api/typedQuery/list")

    public RestResponse findQueryByKeyword(ContentSearchDTO contentSearchDTO,

                                           @PageableDefault(sort = {"contentId"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return new RestResponse(contentService.findQueryBySearch(contentSearchDTO, pageable));

    }


    /**
     * queryDsl로 작성한 게시글 조회
     * <p>
     * 페이징 포함
     *
     * @return
     */

    @ApiOperation(value = "queryDsl로 작성한 게시글 조회 (페이징 포함)"

            , notes = "queryDsl 게시글조회")

    @GetMapping(value = "/api/queryDsl/list")

    public RestResponse findQueryByKeyword_queryDsl(ContentSearchDTO contentSearchDTO,

                                                    @PageableDefault(sort = {"contentId"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return new RestResponse(contentService.findQueryQueryDslBySearch(contentSearchDTO, pageable));

    }


    /**
     * toast 연동 게시글 조회
     * <p>
     * 페이징 포함
     *
     * @return
     */

    @ApiOperation(value = "toast 연동 게시글 조회 , (queryDsl, 페이징 포함)"

            , notes = "toast 연동 게시글 조회")

    @GetMapping(value = "/api/toast/list")

    public ToastUIRestResponse findQueryByKeyword_queryDsl_toast(ContentSearchDTO contentSearchDTO

            , @RequestParam("perPage") String perPage, @RequestParam("page") String page) {

        /** pageable 생성 **/

        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(perPage), Sort.by("contentId").descending());

        return new ToastUIRestResponse(contentService.findQueryQueryDslBySearch(contentSearchDTO, pageable));

    }


    /**
     * 게시글 단건 조회
     *
     * @return
     */

    @ApiOperation(value = "게시글 단건 조회", notes = "게시글 단건 조회")

    @ApiImplicitParam(

            name = "contentId"

            , value = "컨텐츠 아이디"

            , required = true

            , dataType = "int"

            , paramType = "path"

            //, defaultValue = 0

            , example = "0"

    )

    @GetMapping(value = "/api/content/{contentId}")

    public RestResponse findById(@PathVariable int contentId) {

        return new RestResponse(contentService.findById(contentId));

    }


    /**
     * toast 연동 게시글 등록
     *
     * @return
     */

    @ApiOperation(value = "게시글 등록", notes = "게시글 등록")

    @PostMapping(value = "/api/create")

    public RestResponse create(@RequestBody @Valid ContentCreateDTO contentCreateDTO) {

        return new RestResponse(contentService.create(contentCreateDTO));

    }


    /**
     * toast 연동 게시글 수정
     *
     * @return
     */

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정")

    @PutMapping(value = "/api/update")

    public RestResponse update(@RequestBody @Valid ContentUpdateDTO contentUpdateDTO) {

        return new RestResponse(contentService.update(contentUpdateDTO));

    }


    /**
     * toast 연동 게시글 삭제
     *
     * @return
     */

    @ApiOperation(value = "게시글 삭제 , 사용여부(N) 처리", notes = "게시글 삭제")

    @ApiImplicitParam(

            name = "contentId"

            , value = "컨텐츠 아이디"

            , required = true

            , dataType = "int"

            , paramType = "path"

            //, defaultValue = 0

            , example = "0"

    )

    @DeleteMapping(value = "/api/delete/{contentId}")

    public RestResponse delete(@PathVariable int contentId) {

        return new RestResponse(contentService.delete(contentId));

    }

}

