package com.audition.service.impl;

import com.audition.dto.AuditionPostDto;
import com.audition.dto.CommentsDto;
import com.audition.integration.AuditionIntegrationClient;
import com.audition.mapper.AuditionPostMapper;
import com.audition.mapper.CommentMapper;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import com.audition.service.IAuditionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuditionServiceImpl implements IAuditionService {


    private AuditionIntegrationClient auditionIntegrationClient;


    public List<AuditionPostDto> getPosts() {
        List<AuditionPost> auditionPostList = auditionIntegrationClient.getPosts();
        List<AuditionPostDto> auditionPostDtoList = AuditionPostMapper.mapToAuditionPostDtoList(auditionPostList);
        return auditionPostDtoList;
    }

    public AuditionPostDto getPostById(final String postId) {
        AuditionPost auditionPost = auditionIntegrationClient.getPostById(postId);
        AuditionPostDto auditionPostDto = AuditionPostMapper.mapToAuditionPostDto(auditionPost);
        return auditionPostDto;
    }

    public List<CommentsDto> getCommentsByPostId(final int postId) {
        List<Comment> commentList = auditionIntegrationClient.getCommentsByPostId(postId);
        List<CommentsDto> commentListDto = CommentMapper.mapToCommentDtoList(commentList);
        return commentListDto;
    }

    public List<CommentsDto> getCommentsForPost(final int postId) {
        List<Comment> commentList = auditionIntegrationClient.getCommentsForPost(postId);
        List<CommentsDto> commentListDto = CommentMapper.mapToCommentDtoList(commentList);
        return commentListDto;
    }


}
