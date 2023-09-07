package com.oss.gitboard.service;

import com.oss.gitboard.data.dto.InterestDTO;
import com.oss.gitboard.data.dto.UserDTO;

import java.util.List;

public interface InterestService {

    List<InterestDTO.Info> findListByUser(Long userId);
    void save(InterestDTO.Request requestDTO, UserDTO.Info principal);
    void delete(Long id);
}
