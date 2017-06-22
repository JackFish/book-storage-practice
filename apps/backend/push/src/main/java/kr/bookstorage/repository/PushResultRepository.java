package kr.bookstorage.repository;

import kr.bookstorage.domain.batch.PushResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohjic on 2017-06-22.
 */
@Repository
public interface PushResultRepository extends JpaRepository<PushResult, Long>
{
}
