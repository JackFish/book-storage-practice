package kr.bookstorage.service;

import kr.bookstorage.domain.Post;
import kr.bookstorage.domain.Reply;
import kr.bookstorage.repository.PostRepository;
import kr.bookstorage.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ksb on 16. 11. 16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuestionServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionService questionServiceImpl;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    private Post post;

    private Reply reply;

    @Before
    public void setUp() {
        post = new Post();
        reply = new Reply();
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

}
