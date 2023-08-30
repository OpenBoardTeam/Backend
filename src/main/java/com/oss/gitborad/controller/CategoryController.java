package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.CategoryDTO;
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
    public ResponseEntity<List<CategoryDTO.Info>> findListByGroup(@PathVariable Long id) {
        List<CategoryDTO.Info> findList = categoryService.findListByGroup(id);

        return ResponseEntity.status(HttpStatus.OK).body(findList);
    }

    @PostMapping
    @ApiOperation(value = "카테고리 생성")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDTO.Request requestDTO){
        categoryService.saveCategory(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 생성되었습니다.");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "카테고리 삭제")
    public ResponseEntity<String> deleteCategory(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id){
        if(principal == null) return ResponseEntity.status(HttpStatus.OK).body("인가되지 않은 작업입니다.");
        Long userId = principal.getUser().getId();

        categoryService.deleteCategory(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    //Group
    @PostMapping("/group")
    @ApiOperation(value = "카테고리 그룹 생성")
    public ResponseEntity<String> saveCategoryGroup(@RequestBody CategoryDTO.GroupRequest requestDTO){
        categoryService.saveCategoryGroup(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 생성되었습니다.");
    }

    @DeleteMapping("/group/{id}")
    @ApiOperation(value = "카테고리 그룹 삭제")
    public ResponseEntity<String> deleteCategoryGroup(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id){
        if(principal == null) return ResponseEntity.status(HttpStatus.OK).body("인가되지 않은 작업입니다.");
        Long userId = principal.getUser().getId();

        categoryService.deleteCategoryGroup(id, userId);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
