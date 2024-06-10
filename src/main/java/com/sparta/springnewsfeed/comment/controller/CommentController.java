package com.sparta.springnewsfeed.comment.controller;

import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Comment Controller", tags = "Comment")
@RestController
@RequestMapping("/post")
public class CommentController {

    // Service 주입
    private final CommentService commentService; // CommentController > CommentService > CommentRepository

    public CommentController(CommentService commentService) { // DispatcherServlet이 내부적으로 가져다가 사용하고 있음.
        this.commentService = commentService;
    }

    // 댓글 생성, 수정, 조회, 삭제 기능
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성합니다.")
    @PostMapping("{post_id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.addComment(requestDto);
    }

    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회합니다.")
    @GetMapping("{post_id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> getComments() {
        return commentService.getComments();
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정 합니다.")
    @PutMapping("{post_id}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 합니다.")
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}