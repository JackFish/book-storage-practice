package kr.bookstorage.repository;

import kr.bookstorage.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ksb on 2016-08-07.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
