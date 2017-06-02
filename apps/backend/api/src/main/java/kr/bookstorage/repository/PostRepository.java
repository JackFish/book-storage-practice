package kr.bookstorage.repository;

import kr.bookstorage.domain.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ohjic on 2017-06-02.
 */
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, JpaSpecificationExecutor {

    @Modifying
    @Query("update Post p set p.enabled = false where p.idx = :idx")
    int remove(@Param("idx") Long idx);

}
