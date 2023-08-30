package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.InterestDTO;
import com.oss.gitborad.data.dto.UserDTO;

import java.util.List;

public interface InterestService {

    List<InterestDTO.Info> findListByUser(Long userId);
    void save(InterestDTO.Request requestDTO, UserDTO.Info principal);
    void delete(Long id);
}
