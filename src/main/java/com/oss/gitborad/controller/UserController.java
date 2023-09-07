package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.ResponseCode;
import com.oss.gitborad.data.dto.ResponseDTO;
import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<?>> get(@AuthenticationPrincipal UserDTO.Info principal) {
        if(principal == null)  return ResponseEntity.ok(ResponseDTO.ofFailure());
        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, principal.getUser().getEmail(), principal.getUser().getName()));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO<?>> deleteUser(@AuthenticationPrincipal UserDTO.Info principal) {
        if(principal == null) return ResponseEntity.badRequest().body(ResponseDTO.ofUnauthorized());
        Long userId = principal.getUser().getId();
        userService.deleteUserById(userId);
        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

    @GetMapping("/profile/{username}") // TODO: Change to `/users/:username`
    @ApiOperation(value = "사용자 조회")
    public ResponseEntity<ResponseDTO<UserDTO.InfoForAll>> findOne(@PathVariable String username) {
        UserDTO.InfoForAll findOneDto = userService.findOne(username);
        if(findOneDto == null) return ResponseEntity.ok(ResponseDTO.ofFailure("Not found user."));

        return ResponseEntity.ok(ResponseDTO.of(ResponseCode.SUCCESS, null, findOneDto));
    }

    @PostMapping("/badge")
    @ApiOperation(value = "뱃지 부여", notes = "사용자에게 뱃지를 부여")
    public ResponseEntity<ResponseDTO<Object>> save(@RequestBody UserDTO.BadgeRequest requestDTO) {
        userService.saveForBadge(requestDTO);

        return ResponseEntity.ok(ResponseDTO.ofSuccess());
    }

}
