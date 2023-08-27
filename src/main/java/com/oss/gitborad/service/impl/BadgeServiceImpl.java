package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.Badge;
import com.oss.gitborad.data.dto.BadgeDTO;
import com.oss.gitborad.repository.BadgeRepository;
import com.oss.gitborad.service.BadgeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Override
    public BadgeDTO.Info findOne(Long id) {
        Badge badge = badgeRepository.getById(id);
        BadgeDTO.Info findDTO = new BadgeDTO.Info(badge);

        return findDTO;
    }

    @Override
    public BadgeDTO.Info save(BadgeDTO.Request requestDTO) {
        Badge badge = new Badge(requestDTO);

        BadgeDTO.Info save = new BadgeDTO.Info(badgeRepository.save(badge));

        return save;
    }

    @Override
    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }
}
