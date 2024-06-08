package com.sparta.springnewsfeed.comment.dto;

import com.sparta.springnewsfeed.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private Long commentId; // 댓글 ID
    private String username;
    private String commentContents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getModifiedAt();
    }

}
