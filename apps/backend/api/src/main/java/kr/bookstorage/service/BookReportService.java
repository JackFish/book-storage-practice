package kr.bookstorage.service;

import kr.bookstorage.dto.BookReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ksb on 16. 11. 9.
 */
public interface BookReportService {

    List<BookReportDto.Summary> findSummaryList();

    Page<BookReportDto.Summary> findSummaryList(Pageable pageable);

    Page<BookReportDto.Summary> findSummaryList(Pageable pageable, BookReportDto.Search search);

    BookReportDto.Detail findDetailOne(Long idx);

    void create(BookReportDto.Create bookReportDto);

    void update(BookReportDto.Update bookReportDto);

    void visible(BookReportDto.Visible bookReportDto);

    void remove(Long idx);

}
