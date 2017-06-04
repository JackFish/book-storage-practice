package kr.bookstorage.security.service.impl;

import kr.bookstorage.domain.User;
import kr.bookstorage.domain.code.SocialProvider;
import kr.bookstorage.security.service.CmmSocialAndUserDetailService;
import kr.bookstorage.security.service.CmmUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 권성봉 on 8/10/16.
 */
@Slf4j
@Service("cmmUserDetailsService")
public class CmmSocialAndUserDetailsServiceImpl implements CmmSocialAndUserDetailService {

    @Resource(name = "securityUserDAO")
    private SecurityUserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public CmmUserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        log.debug("uniqueId: {}", uniqueId);
        log.debug("UUID: {}", UUID.fromString(uniqueId));
        User user = userDAO.findOne(UUID.fromString(uniqueId));

        if(user == null){
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        log.debug("ROLE: {}", user.getUserRoleList().toString());
        user.getUserRoleList().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRole()))
        );

        return new CmmUserDetails(
                user.getUniqueId().toString(),
                user.getPassword(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                authorities,
                user
        );
    }

    @Override
    public UserDetails loadUserByUniqueId(UUID uuid) throws UsernameNotFoundException {
        User user = userDAO.findOne(uuid);
        log.debug("ROLE: {}", user.getUserRoleList().toString());

        return loadUserByUsername(uuid.toString());
    }

    @Override
    public User loadUserByConnectionKey(ConnectionKey connectionKey) {
        User user = userDAO.findByUserSocial_ProviderIdAndUserSocial_ProviderUserId(SocialProvider.valueOf(connectionKey.getProviderId()), connectionKey.getProviderUserId());
        return checkUser(user);
    }

    @Override
    public void updateUserSocial(User user) {
        userDAO.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userDAO.findOne(UUID.fromString(userId));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String uniqueId) throws UsernameNotFoundException {
        log.debug("SOCIAL USER ID : {}", uniqueId);
        return loadUserByUsername(uniqueId);
    }

    private User checkUser(User user){
        if(user == null)
            throw new UsernameNotFoundException("");
        return user;
    }

}
