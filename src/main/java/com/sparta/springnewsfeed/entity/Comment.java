package com.sparta.springnewsfeed.entity;

import jakarta.persistence.*;

@Entity
public record Comment(long username, String commentContent, long commentLike)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long commentId; // 댓글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private static Post postId; // 게시글 ID

}