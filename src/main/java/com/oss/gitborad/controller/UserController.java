package com.oss.gitborad.controller;

import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "사용자 조회")
    public ResponseEntity<UserDTO.infoForAll> findOne(@PathVariable Long id) {
        UserDTO.infoForAll findOneDto = userService.findOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(findOneDto);
    }

    @PostMapping("/badge")
    @ApiOperation(value = "뱃지 부여", notes = "사용자에게 뱃지를 부여")
    public ResponseEntity<UserDTO.infoForAll> save(@RequestBody UserDTO.badgeRequest requestDTO) {
        userService.saveForBadge(requestDTO);
        UserDTO.infoForAll saveDto = userService.findOne(requestDTO.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(saveDto);
    }

}
