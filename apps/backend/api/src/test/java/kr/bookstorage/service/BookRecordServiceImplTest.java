package kr.bookstorage.service;

import kr.bookstorage.domain.BookRecord;
import kr.bookstorage.dto.BookRecordDto;
import kr.bookstorage.repository.BookRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ksb on 16. 11. 10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookRecordServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRecordService bookRecordService;

    @Autowired
    private BookRecordRepository bookRecordRepository;

    private BookRecord bookRecord;

    @Before
    public void setUp() {
        bookRecord = new BookRecord();
        bookRecord.setAuthor("author");
        bookRecord.setPublisher("publisher");
        bookRecord.setSubject("subject");
        bookRecord.setSummary("summary");
    }

    @Test
    public void 추가하기1(){
        try {
//            bookRecordService.create(modelMapper.map(bookRecord, BookRecordDto.Create.class), null);
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void 수정하기1(){
        추가하기1();
        BookRecord bookRecord = getLastBookRecord();

        try {
//            bookRecordService.update(modelMapper.map(bookRecord, BookRecordDto.Update.class), null);
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void 한개가져오기1(){
        추가하기1();
        BookRecord bookRecord = getLastBookRecord();

        BookRecordDto.Detail detail = bookRecordService.findDetailOne(bookRecord.getIdx());

        if(bookRecord.getAuthor().equals(detail.getAuthor())){
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void 목록가져오기1(){
        추가하기1();

        List<BookRecordDto.Summary> bookRecordList = bookRecordService.findSummaryList();
    }

    private BookRecord getLastBookRecord(){
        List<BookRecord> bookRecordList = (List<BookRecord>)bookRecordRepository.findAll();

        if(bookRecordList.size() > 0) {
            return bookRecordList.get(bookRecordList.size() - 1);
        }
        return null;
    }

}
