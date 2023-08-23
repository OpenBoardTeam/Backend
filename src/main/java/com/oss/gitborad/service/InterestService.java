package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.InterestDTO;

import java.util.List;

public interface InterestService {

    List<InterestDTO.info> findListByUser(Long userId);
    void save(InterestDTO.request requestDTO);
    void delete(Long id);
}
