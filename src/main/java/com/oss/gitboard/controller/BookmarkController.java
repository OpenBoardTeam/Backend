package com.oss.gitboard.controller;

import com.oss.gitboard.data.dto.BookmarkDTO;
import com.oss.gitboard.data.dto.ResponseCode;
import com.oss.gitboard.data.dto.ResponseDTO;
import com.oss.gitboard.data.dto.UserDTO;
import com.oss.gitboard.service.BookmarkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/user/{userId}") // TODO: Move to `/users/:username/bookmarks`
    @ApiOperation(value = "북마크 리스트 조회", notes = "사용자 id로 북마크 리스트 조회")
    public ResponseEntity<ResponseDTO<List<BookmarkDTO.InfoForList>>> findListByUser(@PathVariable Long userId) {
        List<BookmarkDTO.InfoForList> findList = bookmarkService.findListByUser(userId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @PostMapping
    @ApiOperation(value = "북마크 생성") // TODO: Get user info by `@AuthenticationPrincipal`
    public ResponseEntity<ResponseDTO<BookmarkDTO.Info>> save(@RequestBody BookmarkDTO.Request requestDTO){
        BookmarkDTO.Info saveDTO = bookmarkService.save(requestDTO);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, saveDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "북마크 취소")
    public ResponseEntity<ResponseDTO<Object>> delete(
            @AuthenticationPrincipal UserDTO.Info principal,
            @PathVariable Long id
    ){
        if(principal == null)
            return ResponseEntity.ok(ResponseDTO.ofUnauthorized("삭제 권한이 없습니다. 로그인을 해주세요."));

        Long userId = principal.getUser().getId(); // Extract user_id
        bookmarkService.delete(id, userId);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

}
