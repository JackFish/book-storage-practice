package kr.bookstorage.service;

import kr.bookstorage.dto.PostDto;
import kr.bookstorage.dto.ReplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ohjic on 2017-06-02.
 */
public interface QuestionService {
    Page<PostDto.Summary> findPostSummaryList(Pageable pageable, PostDto.Search search);

    PostDto.Detail findPostDetailOne(long idx);

    void createPost(PostDto.Create post);

    void updatePost(PostDto.Update post);

    void removePost(long idx);

    Page<ReplyDto.Summary> findReplySummaryList(Pageable pageable, ReplyDto.Search search);

    ReplyDto.Detail findReplyDetailOne(long idx);

    void createReply(ReplyDto.Create reply);

    void updateReply(ReplyDto.Update reply);

    void removeReply(long idx);
}
