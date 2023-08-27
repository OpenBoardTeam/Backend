package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.UserDTO;

public interface UserService {

    UserDTO.InfoForAll findOne(Long id);
    void saveForBadge(UserDTO.BadgeRequest requestDTO);

}
