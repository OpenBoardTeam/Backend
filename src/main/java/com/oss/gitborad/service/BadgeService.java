package com.oss.gitborad.service;

import com.oss.gitborad.data.domain.Badge;
import com.oss.gitborad.data.dto.BadgeDTO;

public interface BadgeService {
    BadgeDTO.info findOne(Long id);
    BadgeDTO.info save(BadgeDTO.request requestDTO);
    void delete(Long id);
}
