package com.sparta.springnewsfeed.comment.controller;

import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.comment.service.CommentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class CommentController {

    // Service 주입
    private final CommentService commentService; // CommentController > CommentService > CommentRepository

    public CommentController(CommentService commentService) { // DispatcherServlet이 내부적으로 가져다가 사용하고 있음.
        this.commentService = commentService;
    }

    // 댓글 생성, 수정, 조회, 삭제 기능
    @PostMapping("/comments")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.addComment(requestDto);
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> getComments() {
        return commentService.getComments();
    }

    @PutMapping("/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);

    }
}