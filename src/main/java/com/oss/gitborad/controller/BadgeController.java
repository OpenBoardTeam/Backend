package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.BadgeDTO;
import com.oss.gitborad.service.BadgeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/badge")
public class BadgeController {
    private final BadgeService badgeService;


    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "뱃지 정보 조회")
    public ResponseEntity<BadgeDTO.Info> findOne(@PathVariable Long id) {
        BadgeDTO.Info findOneDto = badgeService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(findOneDto);
    }


    @PostMapping
    @ApiOperation(value = "뱃지 생성")
    public ResponseEntity<BadgeDTO.Info> save(@RequestBody BadgeDTO.Request requestDTO) {
        BadgeDTO.Info saveDto = badgeService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(saveDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "뱃지 삭제")
    public ResponseEntity<String> delete(@PathVariable Long id){
        badgeService.delete(id);
        // TODO: 유저 인증 로직
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
