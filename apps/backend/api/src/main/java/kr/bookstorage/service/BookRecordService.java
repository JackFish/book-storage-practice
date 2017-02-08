package kr.bookstorage.service;

import kr.bookstorage.dto.BookRecordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ksb on 16. 11. 9.
 */
public interface BookRecordService {

    List<BookRecordDto.Summary> findSummaryList();

    Page<BookRecordDto.Summary> findSummaryList(Pageable pageable);

    Page<BookRecordDto.Summary> findSummaryList(Pageable pageable, BookRecordDto.Search search);

    BookRecordDto.Detail findDetailOne(Long idx);

    void create(BookRecordDto.Create bookRecord);

    void update(BookRecordDto.Update bookRecord);

    void visible(BookRecordDto.Visible bookRecord);

    void remove(Long idx);

    boolean existSubject(String subject);

}
