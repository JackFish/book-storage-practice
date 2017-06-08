package kr.bookstorage.batch;

import kr.bookstorage.domain.batch.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ohjic on 2017-06-09.
 */
@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {
}
