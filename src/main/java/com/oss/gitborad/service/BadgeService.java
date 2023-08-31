package com.oss.gitborad.service;

import com.oss.gitborad.data.dto.BadgeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BadgeService {
    BadgeDTO.Info findOne(Long id);
    BadgeDTO.Info save(BadgeDTO.Request requestDTO, MultipartFile imageFile) throws IOException;
    void delete(Long id);
}
