package com.oss.gitborad.controller;


import com.oss.gitborad.data.dto.CategoryDTO;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.service.CategoryService;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<ResponseDTO<Object>> saveHashtagGroup(@RequestBody CategoryDTO.GroupRequest requestDTO){
        categoryService.saveCategoryGroup(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @DeleteMapping("/hashtag-group/{id}")
    @ApiOperation(value = "해시태그 그룹 삭제")
    public ResponseEntity<ResponseDTO<Object>> deleteHashtagGroup(@PathVariable Long id){
        categoryService.deleteCategoryGroup(id);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

}
