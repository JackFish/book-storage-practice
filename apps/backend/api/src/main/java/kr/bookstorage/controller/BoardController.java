package kr.bookstorage.controller;

import kr.bookstorage.dto.PostDto;
import kr.bookstorage.dto.ReplyDto;
import kr.bookstorage.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ohjic on 2017-06-02.
 */
@RequestMapping("/board")
@RestController
public class BoardController {

    @Autowired
    private BoardService boardServiceImpl;

    @GetMapping("/post")
    public Page<PostDto.Summary> findPostSummaryList(@PageableDefault(value = 20, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @ModelAttribute PostDto.Search search) {
        return boardServiceImpl.findPostSummaryList(pageable, search);
    }

    @GetMapping("/post/{idx}")
    public PostDto.Detail findDetailOne(@PathVariable("idx") long idx) {
        return boardServiceImpl.findPostDetailOne(idx);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto.Create post){
        boardServiceImpl.createPost(post);
    }

    @PatchMapping("/post")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@RequestBody PostDto.Update post){
        boardServiceImpl.updatePost(post);
    }

    @DeleteMapping("/post/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable("idx") long idx) {
        boardServiceImpl.removePost(idx);
    }

    @GetMapping("/reply")
    public Page<ReplyDto.Summary> findReplySummaryList(@PageableDefault(value = 20, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                                                     @ModelAttribute ReplyDto.Search search) {
        return boardServiceImpl.findReplySummaryList(pageable, search);
    }

    @GetMapping("/reply/{idx}")
    public ReplyDto.Detail finReplyDetailOne(@PathVariable("idx") long idx) {
        return boardServiceImpl.findReplyDetailOne(idx);
    }

    @PostMapping("/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReply(@RequestBody ReplyDto.Create Reply){
        boardServiceImpl.createReply(Reply);
    }

    @PatchMapping("/reply")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReply(@RequestBody ReplyDto.Update Reply){
        boardServiceImpl.updateReply(Reply);
    }

    @DeleteMapping("/reply/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeReply(@PathVariable("idx") long idx) {
        boardServiceImpl.removeReply(idx);
    }

}
