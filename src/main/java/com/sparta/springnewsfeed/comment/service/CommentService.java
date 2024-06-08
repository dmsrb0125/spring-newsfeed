package com.sparta.springnewsfeed.comment.service;

import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.comment.entity.Comment;
import com.sparta.springnewsfeed.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Controller -> Service (@Service에 Component가 포함되어 있다.)
public class CommentService { // commentService

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    } // 객체의 불변성을 지킬 수 있기 때문에 생성자 방식으로 DI 사용


    public List<CommentResponseDto> getComments() {
        // DB 조회
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }


    // DB 생성
    public CommentResponseDto addComment(CommentRequestDto requestDto) {

        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        return new CommentResponseDto(saveComment);
    }

    // DB 수정
    @Transactional
    public Long updateComment(Long id, CommentRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Comment comment = findComment(id);
        comment.update(requestDto);
        return id;
    }


    // 댓글 삭제
    public Long deleteComment(Long id) {
        Comment comment = findComment(id);
        commentRepository.delete(comment);
        return id;
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }


}