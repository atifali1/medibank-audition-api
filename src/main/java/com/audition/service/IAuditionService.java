package com.audition.service;

import com.audition.dto.AuditionPostDto;
import com.audition.dto.CommentsDto;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import java.util.List;

public interface IAuditionService {

    public List<AuditionPostDto> getPosts();

    public AuditionPostDto getPostById(final String postId);

    public List<CommentsDto> getCommentsByPostId(final int postId);

    public List<CommentsDto> getCommentsForPost(final int postId);
}
