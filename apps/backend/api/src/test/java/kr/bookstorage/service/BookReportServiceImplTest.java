package kr.bookstorage.service;

import kr.bookstorage.domain.*;
import kr.bookstorage.dto.BookReportDto;
import kr.bookstorage.repository.BookReportRepository;
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
 * Created by ksb on 16. 11. 16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookReportServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookReportService bookReportService;

    @Autowired
    private BookReportRepository bookReportRepository;

    private BookReport bookReport;

    @Before
    public void setUp() {
        bookReport = new BookReport();
    }

    @Test
    public void 추가하기1() {
        try {
//            bookReportService.create(modelMapper.map(bookReport, BookReportDto.Create.class), null);
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);

    }

    @Test
    public void 수정하기1() {
        추가하기1();
        BookReport bookReport = getLastBookReport();

        try {
//            bookReportService.update(modelMapper.map(bookReport, BookReportDto.Update.class), null);
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void 한개가져오기1() {
        추가하기1();
        BookReport bookReport = getLastBookReport();

        BookReportDto.Detail detail = bookReportService.findDetailOne(bookReport.getIdx());

        if(bookReport.getSubject().equals(detail.getSubject())){
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void 목록가져오기1() {
        추가하기1();

        List<BookReportDto.Summary> bookReportList = bookReportService.findSummaryList();
    }

    private BookReport getLastBookReport(){
        List<BookReport> bookReportList = (List<BookReport>)bookReportRepository.findAll();

        if(bookReportList.size() > 0) {
            return bookReportList.get(bookReportList.size() - 1);
        }
        return null;
    }

}
