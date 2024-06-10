package com.sparta.springnewsfeed.comment.repository;

import com.sparta.springnewsfeed.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Service -> Repository @Repository 달지 않아도 JpaRepository가 자동으로 달아줌. Bean으로 등록 됨.
// Query Method 기능 구현
public interface CommentRepository extends JpaRepository<Comment, Long> {
 List<Comment> findAllByOrderByModifiedAt();
}