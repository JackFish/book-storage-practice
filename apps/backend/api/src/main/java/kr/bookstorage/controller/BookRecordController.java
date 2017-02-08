package kr.bookstorage.controller;

import kr.bookstorage.domain.code.BookRecordSearchStatus;
import kr.bookstorage.domain.code.EnumEnumerator;
import kr.bookstorage.dto.BookRecordDto;
import kr.bookstorage.exception.ErrorStatus;
import kr.bookstorage.exception.exceptions.ForbiddenException;
import kr.bookstorage.service.BookRecordService;
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
@RequestMapping("/book_record")
@RestController
public class BookRecordController {

    @Autowired
    private BookRecordService bookRecordServiceImpl;

    @GetMapping
    public Page<BookRecordDto.Summary> findSummaryList(@PageableDefault(value = 20, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @ModelAttribute BookRecordDto.Search search) {
        return bookRecordServiceImpl.findSummaryList(pageable, search);
    }

    @GetMapping("/{idx}")
    public BookRecordDto.Detail findDetailOne(@PathVariable("idx") long idx) {
        return bookRecordServiceImpl.findDetailOne(idx);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestPart("data") BookRecordDto.Create bookRecord, @RequestPart("file") List<MultipartFile> fileList) {
        bookRecord.setImageGroupFileList(fileList);
        bookRecordServiceImpl.create(bookRecord);
    }

    @PatchMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestPart("data") BookRecordDto.Update bookRecord, @RequestPart("file") List<MultipartFile> fileList) {
        bookRecord.setImageGroupFileList(fileList);
        bookRecordServiceImpl.update(bookRecord);
    }

    @PatchMapping("/visible")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVisible(@RequestBody BookRecordDto.Visible bookRecord) {
        bookRecordServiceImpl.visible(bookRecord);
    }

    @DeleteMapping("/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("idx") long idx) {
        bookRecordServiceImpl.remove(idx);
    }

    @GetMapping("/exist/subject/{subject}")
    public void existSubject(@PathVariable("subject") String subject) {
        if(bookRecordServiceImpl.existSubject(subject)) throw new ForbiddenException(ErrorStatus.BOOK_RECORD_SUBJECT_EXIST_MESSAGE);
    }

    @GetMapping("/search_status")
    public List<Map<String, String>> bookRecordSearchStatus() {
        return new EnumEnumerator<>(BookRecordSearchStatus.class).getCodeList();
    }

}
