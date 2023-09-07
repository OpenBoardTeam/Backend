package com.oss.gitboard.service.impl;

import com.oss.gitboard.data.domain.Badge;
import com.oss.gitboard.data.dto.BadgeDTO;
import com.oss.gitboard.repository.BadgeRepository;
import com.oss.gitboard.service.BadgeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final S3Uploader s3Uploader;

    public BadgeServiceImpl(BadgeRepository badgeRepository, S3Uploader s3Uploader) {
        this.badgeRepository = badgeRepository;
        this.s3Uploader = s3Uploader;
    }

    @Override
    public BadgeDTO.Info findOne(Long id) {
        Badge badge = badgeRepository.getById(id);
        BadgeDTO.Info findDTO = new BadgeDTO.Info(badge);

        return findDTO;
    }

    @Override
    public BadgeDTO.Info save(BadgeDTO.Request requestDTO, MultipartFile imageFile) throws IOException {
        String imageUrl = s3Uploader.upload(imageFile);

        Badge badge = new Badge(requestDTO, imageUrl);

        BadgeDTO.Info save = new BadgeDTO.Info(badgeRepository.save(badge));


        return save;
    }

    @Override
    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }
}
