package kr.bookstorage.security.service;

import kr.bookstorage.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.UUID;

public interface CmmSocialAndUserDetailService extends SocialUserDetailsService, UserDetailsService {
	UserDetails loadUserByUniqueId(UUID uuid) throws UsernameNotFoundException;
	User loadUserByConnectionKey(ConnectionKey connectionKey);

	void updateUserSocial(User user);

	User findByUserId(String userId);
}
