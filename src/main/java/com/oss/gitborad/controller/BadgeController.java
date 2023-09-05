package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.BadgeDTO;
import com.oss.gitborad.data.dto.ResponseCode;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.service.BadgeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<ResponseDTO<BadgeDTO.Info>> findOne(@PathVariable Long id) {
        BadgeDTO.Info findOneDto = badgeService.findOne(id);
        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findOneDto));
    }

    @PostMapping
    @ApiOperation(value = "뱃지 생성")
    public ResponseEntity<ResponseDTO<BadgeDTO.Info>> save(@ModelAttribute BadgeDTO.Request requestDTO, MultipartFile imageFile) throws IOException {
        BadgeDTO.Info saveDto = badgeService.save(requestDTO, imageFile);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, saveDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "뱃지 삭제")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id){
        badgeService.delete(id);
        // TODO: 유저 인증 로직
        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }
}
