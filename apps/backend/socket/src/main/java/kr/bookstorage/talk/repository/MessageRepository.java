package kr.bookstorage.talk.repository;

import kr.bookstorage.domain.talk.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by ksb on 2017. 6. 4..
 */
public interface MessageRepository  extends JpaRepository<Message, Long>, JpaSpecificationExecutor {
}
