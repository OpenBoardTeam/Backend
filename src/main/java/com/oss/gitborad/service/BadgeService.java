package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BadgeDTO;

public interface BadgeService {
    BadgeDTO.Info findOne(Long id);
    BadgeDTO.Info save(BadgeDTO.Request requestDTO);
    void delete(Long id);
}
