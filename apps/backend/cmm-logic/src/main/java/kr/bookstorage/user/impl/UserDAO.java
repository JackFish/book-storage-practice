package kr.bookstorage.user.impl;

import kr.bookstorage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("userDAO")
public interface UserDAO extends JpaRepository<User, UUID>{
	User findByEmail(String email);
}
