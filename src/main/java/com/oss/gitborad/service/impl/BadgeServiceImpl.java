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
    public BadgeDTO.info findOne(Long id) {
        Badge badge = badgeRepository.getById(id);
        BadgeDTO.info findDTO = new BadgeDTO.info(badge);

        return findDTO;
    }

    @Override
    public BadgeDTO.info save(BadgeDTO.request requestDTO) {
        Badge badge = new Badge(requestDTO);

        BadgeDTO.info save = new BadgeDTO.info(badgeRepository.save(badge));

        return save;
    }

    @Override
    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }
}
