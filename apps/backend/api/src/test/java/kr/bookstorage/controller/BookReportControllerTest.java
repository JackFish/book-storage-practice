package kr.bookstorage.controller;

import kr.bookstorage.domain.BookReport;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ksb on 16. 11. 16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookReportControllerTest {

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookReportController bookReportController;

    @Autowired
    private BookReportRepository bookReportRepository;

    private BookReport bookReport;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookReportController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
        bookReport = new BookReport();
    }

    @Test
    public void 추가하기1() throws Exception {
        BookReportDto.Create create = modelMapper.map(bookReport, BookReportDto.Create.class);

        this.mockMvc.perform(
                post("/book-report")
                        .content(this.json(create))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void 수정하기1() throws Exception {
        추가하기1();
        BookReportDto.Update last = modelMapper.map(getLastBookReport(), BookReportDto.Update.class);

        this.mockMvc.perform(
                patch("/book-report")
                        .content(this.json(last))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void 한개가져오기1() throws Exception {
        추가하기1();
        BookReport last = getLastBookReport();

        this.mockMvc.perform(
                get("/book-report/{idx}", last.getIdx())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.idx", is(notNullValue())))
                .andExpect(jsonPath("$.idx", instanceOf(Integer.class)))
                .andExpect(jsonPath("$.subject", is(notNullValue())))
                .andExpect(jsonPath("$.subject", instanceOf(String.class)))
                .andExpect(jsonPath("$.subject", is(last.getSubject())))
                .andExpect(status().isOk());
    }

    @Test
    public void 목록가져오기1() throws Exception {
        추가하기1();

        this.mockMvc.perform(
                get("/book-report")
                        .contentType(MediaType.ALL.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", is(notNullValue())))
//                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(status().isOk());

    }

    private BookReport getLastBookReport(){
        List<BookReport> bookReportList = (List<BookReport>)bookReportRepository.findAll();

        if(bookReportList.size() > 0) {
            return bookReportList.get(bookReportList.size() - 1);
        }
        return null;
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
