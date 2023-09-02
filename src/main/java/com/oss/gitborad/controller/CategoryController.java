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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Category
    @GetMapping("/list/{id}")
    @ApiOperation(value = "카테고리 목록 조회", notes = "그룹 id로 세부 카테고리 조회")
    public ResponseEntity<ResponseDTO<List<CategoryDTO.Info>>> findListByGroup(@PathVariable Long id) {
        List<CategoryDTO.Info> findList = categoryService.findListByGroup(id);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "카테고리 생성")
    public ResponseEntity<ResponseDTO<Object>> saveCategory(@RequestBody CategoryDTO.Request requestDTO){
        categoryService.saveCategory(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "카테고리 삭제")
    public ResponseEntity<ResponseDTO<Object>> deleteCategory(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id){
        if(principal == null) return ResponseEntity.ok(ResponseDTO.ofUnauthorized());
        Long userId = principal.getUser().getId();

        categoryService.deleteCategory(id, userId);
        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    //Group
    @PostMapping("/group")
    @ApiOperation(value = "카테고리 그룹 생성")
    public ResponseEntity<ResponseDTO<Object>> saveCategoryGroup(@RequestBody CategoryDTO.GroupRequest requestDTO){
        categoryService.saveCategoryGroup(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @DeleteMapping("/group/{id}")
    @ApiOperation(value = "카테고리 그룹 삭제")
    public ResponseEntity<ResponseDTO<Object>> deleteCategoryGroup(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id){
        // TODO: 삭제 예정. (일반 유저에게는 카테고리 그룹 삭제 권한이 없어야함.)
        if(principal == null) return ResponseEntity.ok(ResponseDTO.ofUnauthorized());
        Long userId = principal.getUser().getId();

        categoryService.deleteCategoryGroup(id);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

}
