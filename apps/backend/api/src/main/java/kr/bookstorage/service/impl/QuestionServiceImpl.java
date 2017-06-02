package kr.bookstorage.service.impl;

import kr.bookstorage.domain.Post;
import kr.bookstorage.domain.Reply;
import kr.bookstorage.dto.PostDto;
import kr.bookstorage.dto.ReplyDto;
import kr.bookstorage.repository.PostRepository;
import kr.bookstorage.repository.ReplyRepository;
import kr.bookstorage.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ohjic on 2017-06-02.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PostDto.Summary> findPostSummaryList(Pageable pageable, PostDto.Search search) {
        Specification<PostDto.Search> searchSpecification = new PostSpecification(search);
        Page<Post> page = postRepository.findAll(searchSpecification, pageable);
        List<PostDto.Summary> content = page.getContent().stream().map(post -> modelMapper.map(post, PostDto.Summary.class)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public PostDto.Detail findPostDetailOne(long idx) {
        return modelMapper.map(postRepository.findOne(idx), PostDto.Detail.class);
    }

    @Override
    public void createPost(PostDto.Create post) {
        Post result =  modelMapper.map(post, Post.class);
        postRepository.save(result);
    }

    @Transactional
    @Override
    public void updatePost(PostDto.Update post) {
        Post result =  postRepository.findOne(post.getIdx());
        result.setSubject(post.getSubject());
        result.setContent(post.getContent());
    }

    @Transactional
    @Override
    public void removePost(long idx) {
        if(postRepository.exists(idx)){
            postRepository.remove(idx);
        }
    }

    @Override
    public Page<ReplyDto.Summary> findReplySummaryList(Pageable pageable, ReplyDto.Search search) {
        Specification<ReplyDto.Search> searchSpecification = new ReplySpecification(search);
        Page<Reply> page = replyRepository.findAll(searchSpecification, pageable);
        List<ReplyDto.Summary> content = page.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDto.Summary.class)).collect(toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    public ReplyDto.Detail findReplyDetailOne(long idx) {
        return modelMapper.map(replyRepository.findOne(idx), ReplyDto.Detail.class);
    }

    @Override
    public void createReply(ReplyDto.Create reply) {
        Reply result =  modelMapper.map(reply, Reply.class);
        replyRepository.save(result);
    }

    @Transactional
    @Override
    public void updateReply(ReplyDto.Update reply) {
        Reply result =  replyRepository.findOne(reply.getIdx());
        result.setContent(reply.getContent());
    }

    @Override
    public void removeReply(long idx) {
        if(replyRepository.exists(idx)){
            replyRepository.remove(idx);
        }
    }
}
