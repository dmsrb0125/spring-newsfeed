package com.sparta.springnewsfeed.comment.controller;


import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.comment.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/post")
public class CommentController {

    private final Map<Long, Comment> commentList = new HashMap<>();

    @PostMapping("/comments")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        Long maxId = !commentList.isEmpty() ? Collections.max(commentList.keySet()) + 1 : 1;
        comment.setCommentId(maxId);

        // DB 저장
        commentList.put(comment.getCommentId(), comment);

        // Entity -> ResponseDto
        return new CommentResponseDto(comment);
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> getAllComments() {
        // Map -> List 변환
        List<CommentResponseDto> responseList = commentList.values().stream()
                .map(CommentResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        if (!commentList.containsKey(id)) {
            throw new IllegalArgumentException("Comment with id " + id + " does not exist");
        } else { // 댓글 가져오기
            Comment comment = commentList.get(id);

            // 댓글 수정
            comment.update(requestDto);
            return comment.getCommentId();
        }
    }

    @DeleteMapping("/comments/{id}")
    public Comment deleteComment(@PathVariable Long id) {
        if (!commentList.containsKey(id)) {
            throw new IllegalArgumentException("Comment with id " + id + " does not exist");
        } else {
            // 메모 삭제하기
            return commentList.remove(id);
        }
    }
}