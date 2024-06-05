package com.sparta.springnewsfeed.comment.dto;

import com.sparta.springnewsfeed.comment.entity.Comment;
import lombok.Getter;


@Getter
public class CommentResponseDto {
    private Long commentId; // 댓글 ID
    private String username;
    private String commentContents;


    public CommentResponseDto(Comment comment) {
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
    }
}
