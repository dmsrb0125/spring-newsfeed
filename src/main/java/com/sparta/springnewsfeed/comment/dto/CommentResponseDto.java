package com.sparta.springnewsfeed.comment.dto;

import com.sparta.springnewsfeed.comment.entity.Comment;
import lombok.Getter;


@Getter
public class CommentResponseDto {
    private Long commentId; // 댓글 ID
    private String username;
    private String commentContents;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
    }

    public CommentResponseDto(Long id, String username, String contents) {
        this.commentId = id;
        this.username = username;
        this.commentContents = contents;
    }
}
