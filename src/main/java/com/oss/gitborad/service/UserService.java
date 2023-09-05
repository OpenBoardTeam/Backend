package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.UserDTO;

public interface UserService {

    UserDTO.InfoForAll findOne(String username);
    void saveForBadge(UserDTO.BadgeRequest requestDTO);

    void deleteUserById(Long userId);

}
