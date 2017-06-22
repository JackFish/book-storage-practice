package kr.bookstorage.repository;

import kr.bookstorage.domain.batch.PushQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ohjic on 2017-06-22.
 */
@Repository
public interface PushQueueRepository extends JpaRepository<PushQueue, Long>, JpaSpecificationExecutor {
}
