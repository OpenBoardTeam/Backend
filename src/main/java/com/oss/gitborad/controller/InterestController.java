package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.InterestDTO;
import com.oss.gitborad.data.dto.ResponseCode;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.service.InterestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/interests")
public class InterestController {
    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "관심사 리스트 조회", notes = "사용자 id로 관심사 리스트 조회")
    public ResponseEntity<ResponseDTO<List<InterestDTO.Info>>> findListByUser(@PathVariable Long userId) {
        List<InterestDTO.Info> findList = interestService.findListByUser(userId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "관심사 등록")
    public ResponseEntity<ResponseDTO<List<InterestDTO.Info>>> save(
            @AuthenticationPrincipal UserDTO.Info principal,
            @RequestBody InterestDTO.Request requestDTO
    ){
        if(principal == null) return ResponseEntity.badRequest().body(null); // Unexpected user
        interestService.save(requestDTO, principal);
        List<InterestDTO.Info> saveList = interestService.findListByUser(principal.getUser().getId());

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, saveList));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "북마크 취소")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id){
        interestService.delete(id);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }
}
