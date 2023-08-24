package com.oss.gitborad.service.impl;

import com.oss.gitborad.data.domain.User;
import com.oss.gitborad.data.domain.UserBadge;
import com.oss.gitborad.data.dto.UserDTO;
import com.oss.gitborad.data.repository.BadgeRepository;
import com.oss.gitborad.data.repository.UserBadgeRepository;
import com.oss.gitborad.repository.UserRepository;
import com.oss.gitborad.security.oauth.OAuthAttributes;
import com.oss.gitborad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends DefaultOAuth2UserService implements UserService {

    final private UserRepository userRepository;
    final private BadgeRepository badgeRepository;
    final private UserBadgeRepository userBadgeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BadgeRepository badgeRepository, UserBadgeRepository userBadgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("OAuth user : " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println("유저: " + registrationId + ", " + userNameAttributeName);
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        if(attributes == null) return null;


        User user = attributes.saveUser();
        String email = attributes.getEmail();
        User byEmail = userRepository.findByEmail(email).orElse(null);
        // repository save user

        if(byEmail == null) {
            User saved = userRepository.save(user);
            return UserDTO.Info.builder().user(saved).attributes(oAuth2User.getAttributes()).build();
        }

        return UserDTO.Info.builder()
                .user(byEmail).attributes(oAuth2User.getAttributes()).build();
    }


    @Override
    public UserDTO.infoForAll findOne(Long id) {
        User user = userRepository.getById(id);

        UserDTO.infoForAll findDTO = new UserDTO.infoForAll(user);

        return findDTO;
    }

    @Override
    public void saveForBadge(UserDTO.badgeRequest requestDTO) {
        UserBadge userBadge = UserBadge.builder()
                .user(userRepository.getById(requestDTO.getUserId()))
                .badge(badgeRepository.getById(requestDTO.getBadgeId()))
                .build();

        userBadgeRepository.save(userBadge);

    }
}