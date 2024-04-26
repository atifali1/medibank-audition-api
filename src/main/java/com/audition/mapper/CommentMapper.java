package com.audition.mapper;

import com.audition.dto.CommentsDto;
import com.audition.model.Comment;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment mapToComment(CommentsDto commentsDto) {
        Comment comment = new Comment();
        comment.setPostId(commentsDto.getPostId());
        comment.setId(commentsDto.getId());
        comment.setName(commentsDto.getName());
        comment.setEmail(commentsDto.getEmail());
        comment.setBody(commentsDto.getBody());
        return comment;
    }

    public static CommentsDto mapToCommentDto(Comment comment) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setPostId(comment.getPostId());
        commentsDto.setId(comment.getId());
        commentsDto.setName(comment.getName());
        commentsDto.setEmail(comment.getEmail());
        commentsDto.setBody(comment.getBody());
        return commentsDto;
    }

    public static List<Comment> mapToCommentList(List<CommentsDto> commentsDto) {
        return commentsDto.stream()
            .map(CommentMapper::mapToComment)
            .collect(Collectors.toList());
    }

    public static List<CommentsDto> mapToCommentDtoList(List<Comment> comments) {
        return comments.stream()
            .map(CommentMapper::mapToCommentDto)
            .collect(Collectors.toList());
    }
}
