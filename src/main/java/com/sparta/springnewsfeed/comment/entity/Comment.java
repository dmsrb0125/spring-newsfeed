package com.sparta.springnewsfeed.comment.entity;


import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private String username;
    private String commentContents;

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.commentContents = requestDto.getCommentContents();
    }
}
