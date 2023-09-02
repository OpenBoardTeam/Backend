package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.data.dto.ResponseCode;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/hashtag")
public class HashtagController {
    //TODO: 카테고리 -> 해시태그 코드 내 이름 변경 예정
    private final CategoryService categoryService;

    public HashtagController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Category
    @GetMapping("/list/{groupId}")
    @ApiOperation(value = "해시태그 그룹 별 목록 조회", notes = "그룹 id로 세부 해시태그 조회")
    public ResponseEntity<ResponseDTO<List<CategoryDTO.Info>>> findListByGroup(@PathVariable Long groupId) {
        List<CategoryDTO.Info> findList = categoryService.findListByGroup(groupId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @GetMapping("/list/{writerId}")
    @ApiOperation(value = "해시태그 작성자 별 목록 조회", notes = "작성자(User) id로 세부 해시태그 조회")
    public ResponseEntity<ResponseDTO<List<CategoryDTO.Info>>> findListByWriter(@PathVariable Long writerId) {
        List<CategoryDTO.Info> findList = categoryService.findListByWriter(writerId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @GetMapping("/list/all/{pageNumber}/{size}")
    @ApiOperation(value = "해시태그 목록 조회", notes = "pageNumber = 페이지 번호이며 0부터 시작함."+"\n"+"size = 한 페이지에 노출될 데이터 개수")
    public ResponseEntity<ResponseDTO<List<CategoryDTO.Info>>> findAll(@PathVariable int pageNumber, @PathVariable int size) {
        List<CategoryDTO.Info> findList = categoryService.findAll(pageNumber, size);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "해시태그 생성")
    public ResponseEntity<ResponseDTO<Object>> saveHashtag(@RequestBody CategoryDTO.Request requestDTO){
        categoryService.saveCategory(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "해시태그 삭제")
    public ResponseEntity<ResponseDTO<Object>> deleteHashtag (
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id){
        if(principal == null)
            return ResponseEntity.ok(ResponseDTO.ofUnauthorized());
        Long userId = principal.getUser().getId();
        categoryService.deleteCategory(id, userId);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }
}
