package kr.bookstorage.security.service.impl;

import kr.bookstorage.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("securityUserDAO")
public interface SecurityUserDAO extends PagingAndSortingRepository<User, UUID> {

	List<User> findByEmailAndEnabled(String email, boolean enable);
}
