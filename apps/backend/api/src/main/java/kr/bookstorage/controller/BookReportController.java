package kr.bookstorage.controller;

import kr.bookstorage.domain.code.BookReportSearchStatus;
import kr.bookstorage.domain.code.EnumEnumerator;
import kr.bookstorage.dto.BookReportDto;
import kr.bookstorage.service.BookReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by ksb on 16. 11. 9.
 */
@RequestMapping("/book_report")
@RestController
public class BookReportController {

    @Autowired
    private BookReportService bookReportServiceImpl;

    @GetMapping
    public Page<BookReportDto.Summary> findSummaryList(@PageableDefault(value = 20, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @ModelAttribute BookReportDto.Search search){
        return bookReportServiceImpl.findSummaryList(pageable, search);
    }

    @GetMapping("/{idx}")
    public BookReportDto.Detail findDetailOne(@PathVariable("idx") long idx){
        return bookReportServiceImpl.findDetailOne(idx);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestPart("data") BookReportDto.Create bookReport, @RequestPart("file") List<MultipartFile> fileList) {
        bookReport.setImageBlockFileList(fileList);
        bookReportServiceImpl.create(bookReport);
    }

    @PatchMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestPart("data") BookReportDto.Update bookReport, @RequestPart("file") List<MultipartFile> fileList) {
        bookReport.setImageBlockFileList(fileList);
        bookReportServiceImpl.update(bookReport);
    }

    @PatchMapping("/visible")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVisible(@RequestBody BookReportDto.Visible bookReport) {
        bookReportServiceImpl.visible(bookReport);
    }

    @DeleteMapping("/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("idx") long idx) {
        bookReportServiceImpl.remove(idx);
    }

    @GetMapping("/search_status")
    public List<Map<String, String>> bookReportSearchStatus() {
        return new EnumEnumerator<>(BookReportSearchStatus.class).getCodeList();
    }

}
