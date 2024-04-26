package com.audition.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.audition.dto.CommentsDto;
import com.audition.model.Comment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CommentMapperTest {

    @Test
    public void testMapToComment() {
        // Setup
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setPostId(1);
        commentsDto.setId(2);
        commentsDto.setName("John Doe");
        commentsDto.setEmail("john.doe@example.com");
        commentsDto.setBody("Sample comment text");

        // Execution
        Comment comment = CommentMapper.mapToComment(commentsDto);

        // Assertions
        assertEquals(commentsDto.getPostId(), comment.getPostId());
        assertEquals(commentsDto.getId(), comment.getId());
        assertEquals(commentsDto.getName(), comment.getName());
        assertEquals(commentsDto.getEmail(), comment.getEmail());
        assertEquals(commentsDto.getBody(), comment.getBody());
    }

    @Test
    public void testMapToCommentDto() {
        // Setup
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setId(2);
        comment.setName("John Doe");
        comment.setEmail("john.doe@example.com");
        comment.setBody("Sample comment text");

        // Execution
        CommentsDto commentsDto = CommentMapper.mapToCommentDto(comment);

        // Assertions
        assertEquals(comment.getPostId(), commentsDto.getPostId());
        assertEquals(comment.getId(), commentsDto.getId());
        assertEquals(comment.getName(), commentsDto.getName());
        assertEquals(comment.getEmail(), commentsDto.getEmail());
        assertEquals(comment.getBody(), commentsDto.getBody());
    }


    @Test
    public void testMapToCommentList() {
        // Setup
        List<CommentsDto> dtos = Arrays.asList(
            new CommentsDto(1, 10, "John Doe", "john.doe@example.com", "Sample comment text"),
            new CommentsDto(2, 2, "John Doe", "john.doe@example.com", "Sample comment text")
        );

        List<Comment> comments = CommentMapper.mapToCommentList(dtos);

        // Assertions
        assertEquals(2, comments.size());
        assertEquals(dtos.get(0).getId(), comments.get(0).getId());
        assertEquals(dtos.get(1).getEmail(), comments.get(1).getEmail());
    }

    @Test
    public void testMapToCommentDtoList() {
        // Setup
        List<Comment> comments = Arrays.asList(
            new Comment(1, 10, "John Doe", "john.doe@example.com", "Sample comment text"),
            new Comment(2, 2, "John Doe", "john.doe@example.com", "Sample comment text")

        );

        // Execution
        List<CommentsDto> dtos = CommentMapper.mapToCommentDtoList(comments);

        assertEquals(2, comments.size());
        assertEquals(dtos.get(0).getId(), comments.get(0).getId());
        assertEquals(dtos.get(1).getEmail(), comments.get(1).getEmail());
    }
}