package com.oss.gitborad.controller;


import com.oss.gitborad.data.dto.HashtagDTO;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.service.HashtagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final HashtagService hashtagService;

    public AdminController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }


    //Hashtag Group
    @PostMapping("/hashtag-group")
    @ApiOperation(value = "해시태그 그룹 생성")
    public ResponseEntity<ResponseDTO<Object>> saveHashtagGroup(@RequestBody HashtagDTO.GroupRequest requestDTO){
        hashtagService.saveHashtagGroup(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @DeleteMapping("/hashtag-group/{id}")
    @ApiOperation(value = "해시태그 그룹 삭제")
    public ResponseEntity<ResponseDTO<Object>> deleteHashtagGroup(@PathVariable Long id){
        hashtagService.deleteHashtagGroup(id);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

}
