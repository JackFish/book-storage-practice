package kr.bookstorage.repository;

import kr.bookstorage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	User findByEmail(String email);
	User findByName(String name);
}