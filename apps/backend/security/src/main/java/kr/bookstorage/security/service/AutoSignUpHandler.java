package kr.bookstorage.security.service;

import kr.bookstorage.domain.User;
import kr.bookstorage.domain.UserRole;
import kr.bookstorage.domain.UserSocial;
import kr.bookstorage.domain.code.SocialProvider;
import kr.bookstorage.user.impl.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 권성봉 on 2016. 8. 29..
 */
@Slf4j
@Component
public class AutoSignUpHandler implements ConnectionSignUp {

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private volatile long userCount;

    @Override
    @Transactional
    public String execute(Connection<?> connection) {
        log.debug("CONNECTION PROVIDER USER ID : {}", connection.getKey().getProviderUserId());

        final User user = new User();
        final List<UserRole> userRoleList = new ArrayList<>();
        final UserRole userRole = new UserRole();
        final UserSocial userSocial = new UserSocial();

        UserProfile userProfile = connection.fetchUserProfile();

        user.setUserName(userProfile.getFirstName() + ' ' + (userProfile.getLastName() == null ? "" : userProfile.getLastName()));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode("bookstorage@bookstorage@" + connection.getKey().getProviderId()));
        user.setEmail(connection.getKey().getProviderUserId() + "@" + connection.getKey().getProviderId() + ".tmp.com");

        userSocial.setEmail(userProfile.getEmail());
        userSocial.setDisplayNm(connection.createData().getDisplayName());
        userSocial.setProviderId(SocialProvider.valueOf(connection.getKey().getProviderId()));
        userSocial.setProviderUserId(connection.getKey().getProviderUserId());
        userSocial.setAccessToken(connection.createData().getAccessToken());
        userSocial.setExpireTime(connection.createData().getExpireTime());
        userSocial.setRefreshToken(connection.createData().getRefreshToken());
        userSocial.setImageUrl(connection.createData().getImageUrl());
        userSocial.setProfileUrl(connection.createData().getProfileUrl());
        userSocial.setSecret(connection.createData().getSecret());
        userSocial.setUser(user);

        user.setUserSocial(userSocial);

        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        userRoleList.add(userRole);

        user.setUserRoleList(userRoleList);

        userDAO.saveAndFlush(user);

        return user.getUniqueId().toString();
    }
}
