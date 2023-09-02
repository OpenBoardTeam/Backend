package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.*;
import com.oss.gitborad.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/project")
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

    @GetMapping("/list/{userId}")
    @ApiOperation(value = "내가 작성한 프로젝트 글 조회")
    public ResponseEntity<ResponseDTO<List<ProjectDTO.Info>>> findListByUser(@PathVariable Long userId) {
        List<ProjectDTO.Info> findList = projectService.findListByUser(userId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "프로젝트 생성")
    public ResponseEntity<ResponseDTO<ProjectDTO.Info>> save(@RequestBody ProjectDTO.Request requestDTO) {
        ProjectDTO.Info saveDto = projectService.save(requestDTO);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, saveDto));
    }

    @GetMapping("/repo")
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
