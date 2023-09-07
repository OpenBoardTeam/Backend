package com.oss.gitboard.controller;

import com.oss.gitboard.data.dto.*;
import com.oss.gitboard.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "프로젝트 조회")
    public ResponseEntity<ResponseDTO<ProjectDTO.Info>> findOne(@PathVariable Long id) {
        ProjectDTO.Info findOneDto = projectService.findOne(id);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findOneDto));
    }

    @GetMapping("/user/{userId}") // TODO: Move to `/users/:username/repos`
    @ApiOperation(value = "사용자 별 프로젝트 글 조회")
    public ResponseEntity<ResponseDTO<List<ProjectDTO.Info>>> findListByUser(@PathVariable Long userId) {
        List<ProjectDTO.Info> findList = projectService.findListByUser(userId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @GetMapping("/search/keyword/{keyword}/page-number/{number}/size/{size}")
    @ApiOperation(value = "키워드가 포함된 게시글 검색", notes = "검색 범위: 제목, 내용")
    public ResponseEntity<ResponseDTO<List<ProjectDTO.CardInfo>>> findListBySearch(
            @PathVariable String keyword,
            @PathVariable int number,
            @PathVariable int size
    ) {
        List<ProjectDTO.CardInfo> findList = projectService.findListBySearch(number, size, keyword);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @GetMapping("/page-number/{page-number}/size/{size}")
    @ApiOperation(value = "모든 게시글 조회")
    public ResponseEntity<ResponseDTO<List<ProjectDTO.CardInfo>>> findListBySearch(
            @PathVariable("page-number") int number,
            @PathVariable int size
    ) {
        List<ProjectDTO.CardInfo> findList = projectService.findAll(number, size);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "프로젝트 생성")
    public ResponseEntity<ResponseDTO<ProjectDTO.Info>> save(@RequestBody ProjectDTO.Request requestDTO) {
        ProjectDTO.Info saveDto = projectService.save(requestDTO);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, saveDto));
    }

    @GetMapping("/repo-url")
    @ApiOperation(value = "Get basic info with url")
    public ResponseEntity<ResponseDTO<ProjectDTO.ResponseBasicInfo>> getBasicInfo(
            @RequestParam String encodedUrl
    ) {
        ProjectDTO.ResponseBasicInfo basicInfo = projectService.getBasicInfo(encodedUrl);
        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, basicInfo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "프로젝트 삭제")
    public ResponseEntity<ResponseDTO<Object>> delete(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id
    ){
        // TODO: Change to principal validation at controller.
        projectService.delete(id, principal);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }
}
