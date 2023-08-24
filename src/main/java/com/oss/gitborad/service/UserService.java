package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.UserDTO;

public interface UserService {

    UserDTO.infoForAll findOne(Long id);
    void saveForBadge(UserDTO.badgeRequest requestDTO);

}
