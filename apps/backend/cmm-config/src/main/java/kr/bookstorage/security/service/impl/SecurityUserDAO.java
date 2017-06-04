package kr.bookstorage.security.service.impl;

import kr.bookstorage.domain.User;
import kr.bookstorage.domain.code.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("securityUserDAO")
public interface SecurityUserDAO extends JpaRepository<User, UUID> {

	List<User> findByEmailAndEnabled(String email, boolean enable);

	User findByUserSocial_ProviderIdAndUserSocial_ProviderUserId(SocialProvider providerId, String providerUserId);

}
