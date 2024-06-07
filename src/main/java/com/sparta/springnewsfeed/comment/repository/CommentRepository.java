package com.sparta.springnewsfeed.comment.repository;

import com.sparta.springnewsfeed.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.comment.entity.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository // Service -> Repository
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Comment save(Comment comment) { // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO comment (username, commentContents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, comment.getUsername());
                    preparedStatement.setString(2, comment.getCommentContents());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        comment.setCommentId(id);

        return comment;
    }

    public List<CommentResponseDto> findAll() { // DB 조회
        String sql = "SELECT * FROM comment";

        return jdbcTemplate.query(sql, new RowMapper<CommentResponseDto>() {
            @Override
            public CommentResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Comment 데이터들을 CommentResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("commentId");
                String username = rs.getString("username");
                String contents = rs.getString("commentContents");
                return new CommentResponseDto(id, username, contents);
            }
        });
    }

    public void update(Long id, CommentRequestDto requestDto) {
        String sql = "UPDATE comment SET username = ?, commentContents = ? WHERE commentId = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getCommentContents(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM comment WHERE commentId = ?";
        jdbcTemplate.update(sql, id);
    }

    public Comment findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM comment WHERE commentId = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setUsername(resultSet.getString("username"));
                comment.setCommentContents(resultSet.getString("commentContents"));
                return comment;
            } else {
                return null;
            }
        }, id);
    }
}