package com.oss.gitborad.controller;


import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //Hashtag Group
    @PostMapping("/hashtag-group")
    @ApiOperation(value = "해시태그 그룹 생성")
    public ResponseEntity<String> saveHashtagGroup(@RequestBody CategoryDTO.GroupRequest requestDTO){
        categoryService.saveCategoryGroup(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 생성되었습니다.");
    }

    @DeleteMapping("/hashtag-group/{id}")
    @ApiOperation(value = "해시태그 그룹 삭제")
    public ResponseEntity<String> deleteHashtagGroup(@PathVariable Long id){
        categoryService.deleteCategoryGroup(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
