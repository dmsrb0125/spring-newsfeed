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
        comment.setId(maxId);

        // DB 저장
        commentList.put(maxId, comment);

        // Entity -> ResponseDto
        return new CommentResponseDto(comment);
    }
}