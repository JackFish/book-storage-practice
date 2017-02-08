package kr.bookstorage.repository;

import kr.bookstorage.domain.BookReport;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ksb on 16. 11. 9.
 */
@Repository("bookReportRepository")
public interface BookReportRepository extends PagingAndSortingRepository<BookReport, Long>, JpaSpecificationExecutor {

    @Modifying
    @Query("update BookReport br set br.visible = :visible where br.idx = :idx")
    int updateVisible(@Param("idx") Long idx, @Param("visible") boolean visible);

    @Modifying
    @Query("update BookReport br set br.enabled = false where br.idx = :idx")
    int remove(@Param("idx") Long idx);

}
