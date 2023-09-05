package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.HashtagDTO;
import com.oss.gitborad.data.dto.ResponseCode;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.service.HashtagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hashtags")
public class HashtagController {
    //TODO: 카테고리 -> 해시태그 코드 내 이름 변경 예정
    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("/user/{writer-id}")
    @ApiOperation(value = "해시태그 작성자 별 목록 조회", notes = "작성자(User) id로 세부 해시태그 조회")
    public ResponseEntity<ResponseDTO<List<HashtagDTO.Info>>> findListByWriter(@PathVariable("writer-id") Long writerId) {
        List<HashtagDTO.Info> findList = hashtagService.findListByWriter(writerId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    //TODO: page 정보 파라메터로 받기
    @GetMapping("/page-number/{page-number}/size/{size}")
    @ApiOperation(value = "해시태그 목록 조회", notes = "pageNumber = 페이지 번호이며 0부터 시작함."+"\n"+"size = 한 페이지에 노출될 데이터 개수")
    public ResponseEntity<ResponseDTO<List<HashtagDTO.Info>>> findAll(@PathVariable("page-number") int pageNumber, @PathVariable int size) {
        List<HashtagDTO.Info> findList = hashtagService.findAll(pageNumber, size);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList));
    }

    @GetMapping("/group/{group-id}")
    @ApiOperation(value = "그룹 별 해시태그 조회")
    public ResponseEntity<HashtagDTO.GroupListInfo> findHashtagByCertified(@PathVariable long groupId) {
        HashtagDTO.GroupListInfo findList = hashtagService.findHashtagByCertified(groupId);

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findList).getData());
    }

    @PostMapping
    @ApiOperation(value = "해시태그 생성")
    public ResponseEntity<ResponseDTO<Object>> saveHashtag(@RequestBody HashtagDTO.Request requestDTO){
        hashtagService.saveHashtag(requestDTO);

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
        hashtagService.deleteHashtag(id, userId);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }
}
