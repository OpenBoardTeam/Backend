package com.oss.gitboard.service;

import com.oss.gitboard.data.dto.UserDTO;

public interface UserService {

    UserDTO.InfoForAll findOne(String username);
    void saveForBadge(UserDTO.BadgeRequest requestDTO);

    void deleteUserById(Long userId);

}
