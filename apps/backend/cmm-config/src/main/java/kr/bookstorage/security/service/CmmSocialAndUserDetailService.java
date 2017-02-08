package kr.bookstorage.security.service;

import kr.bookstorage.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface CmmSocialAndUserDetailService extends UserDetailsService {
	UserDetails loadUserByUniqueId(UUID uuid) throws UsernameNotFoundException;
//	UserEntity loadUserByConnectionKey(ConnectionKey connectionKey);

//	void updateUserSocial(User user);

	User findByUserId(String userId);
}
