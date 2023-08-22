package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.ProjectDTO;
import com.oss.gitborad.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProjectDTO.info> findOne(@PathVariable Long id) {
        ProjectDTO.info findOneDto = projectService.findOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(findOneDto);
    }

    @PostMapping
    @ApiOperation(value = "프로젝트 생성")
    public ResponseEntity<ProjectDTO.info> save(@RequestBody ProjectDTO.request requestDTO) {
        ProjectDTO.info saveDto = projectService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(saveDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "프로젝트 삭제")
    public ResponseEntity<String> delete(@PathVariable Long id){
        projectService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 제거되었습니다.");
    }
}
