package kr.bookstorage.controller;

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
 * Created by ksb on 16. 11. 10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookRecordControllerTest {

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
    private BookRecordController bookRecordController;

    @Autowired
    private BookRecordRepository bookRecordRepository;

    private BookRecord bookRecord;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookRecordController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
        bookRecord = new BookRecord();
        bookRecord.setAuthor("author");
        bookRecord.setPublisher("publisher");
        bookRecord.setSubject("subject");
        bookRecord.setSummary("summary");
    }

    @Test
    public void 추가하기1() throws Exception {
        BookRecordDto.Create create = modelMapper.map(bookRecord, BookRecordDto.Create.class);

        this.mockMvc.perform(
                post("/book-record")
                        .content(this.json(create))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void 수정하기1() throws Exception {
        추가하기1();
        BookRecordDto.Update last = modelMapper.map(getLastBookRecord(), BookRecordDto.Update.class);

        this.mockMvc.perform(
                patch("/book-record")
                        .content(this.json(last))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void 한개가져오기1() throws Exception {
        추가하기1();
        BookRecord last = getLastBookRecord();

        this.mockMvc.perform(
                get("/book-record/{idx}", last.getIdx())
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
                get("/book-record")
                        .contentType(MediaType.ALL.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", is(notNullValue())))
//                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(status().isOk());
    }

    private BookRecord getLastBookRecord(){
        List<BookRecord> bookRecordList = (List<BookRecord>)bookRecordRepository.findAll();

        if(bookRecordList.size() > 0) {
            return bookRecordList.get(bookRecordList.size() - 1);
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
