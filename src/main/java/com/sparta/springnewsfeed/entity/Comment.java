package com.sparta.springnewsfeed.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data // // Lombok 어노테이션으로 getter, setter, toString, equals, hashCode를 자동 생성
@NoArgsConstructor
public class Comment {

    @Id
    private Long commentId; // 댓글 ID

    private long postId; // 게시글 ID
    private long username; // 작성자 이름
    private String commentContent; // 내용물
    private long commentLike; // 좋아요 갯수
}