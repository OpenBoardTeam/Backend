package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.BookmarkDTO;
import com.oss.gitborad.service.BookmarkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/list/{userId}")
    @ApiOperation(value = "북마크 리스트 조회", notes = "사용자 id로 북마크 리스트 조회")
    public ResponseEntity<List<BookmarkDTO.infoForList>> findListByUser(@PathVariable Long userId) {
        List<BookmarkDTO.infoForList> findList = bookmarkService.findListByUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(findList);
    }

    @PostMapping
    @ApiOperation(value = "북마크 생성")
    public ResponseEntity<BookmarkDTO.info> save(@RequestBody BookmarkDTO.request requestDTO){
        BookmarkDTO.info saveDTO = bookmarkService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(saveDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "북마크 취소")
    public ResponseEntity<String> delete(@PathVariable Long id){
        bookmarkService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
