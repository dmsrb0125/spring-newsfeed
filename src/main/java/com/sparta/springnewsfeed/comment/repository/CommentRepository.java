package com.sparta.springnewsfeed.comment.repository;

import com.sparta.springnewsfeed.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 // Service -> Repository @Repository 달지 않아도 JpaRepository가 자동으로 달아줌. Bean으로 등록 됨.
public interface CommentRepository extends JpaRepository<Comment, Long> {

}