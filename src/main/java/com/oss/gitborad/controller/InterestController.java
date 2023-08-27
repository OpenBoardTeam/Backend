package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.InterestDTO;
import com.oss.gitborad.service.InterestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/interest")
public class InterestController {
    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/list/{userId}")
    @ApiOperation(value = "관심사 리스트 조회", notes = "사용자 id로 관심사 리스트 조회")
    public ResponseEntity<List<InterestDTO.Info>> findListByUser(@PathVariable Long userId) {
        List<InterestDTO.Info> findList = interestService.findListByUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(findList);
    }

    @PostMapping
    @ApiOperation(value = "관심사 등록")
    public ResponseEntity<List<InterestDTO.Info>> save(@RequestBody InterestDTO.Request requestDTO){
        interestService.save(requestDTO);
        List<InterestDTO.Info> saveList = interestService.findListByUser(requestDTO.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(saveList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "북마크 취소")
    public ResponseEntity<String> delete(@PathVariable Long id){
        interestService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
