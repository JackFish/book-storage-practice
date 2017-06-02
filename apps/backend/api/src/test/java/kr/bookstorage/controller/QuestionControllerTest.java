package kr.bookstorage.controller;

import kr.bookstorage.domain.Post;
import kr.bookstorage.domain.Reply;
import kr.bookstorage.repository.PostRepository;
import kr.bookstorage.repository.ReplyRepository;
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
public class QuestionControllerTest {

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
    private QuestionController questionController;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    private Post post;

    private Reply reply;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void 포스트추가하기1() {
    }

    @Test
    public void 포스트수정하기1() {
    }

    @Test
    public void 포스트한개가져오기1() {
    }

    @Test
    public void 포스트목록가져오기1() {
    }

    @Test
    public void 포스트삭제하기1() {
    }

    @Test
    public void 리플라이추가하기1() {
    }

    @Test
    public void 리플라이수정하기1() {
    }

    @Test
    public void 리플라이한개가져오기1() {
    }

    @Test
    public void 리플라이목록가져오기1() {
    }

    @Test
    public void 리플라이삭제하기1() {
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
