package com.sparta.springnewsfeed.comment.entity;

import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 500)
    private String commentContents;

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.commentContents = requestDto.getCommentContents();
    }

    public void update(CommentRequestDto requestDto) {
        this.commentContents = requestDto.getCommentContents();
    }
}
