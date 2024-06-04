package com.sparta.springnewsfeed.entity;

public class Comment {
    private long username; // id
    private long post_id; // 게시글 id
    private long author_id; // 작성자 id
    private String content; // 내용물
    private long comment_like; // 좋아요 갯수
    private long comment_created_at; // 댓글 생성일자
    private long comment_updated_at; // 댓글 수정일자
}
