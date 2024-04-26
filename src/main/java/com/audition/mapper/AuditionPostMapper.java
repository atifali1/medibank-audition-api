package com.audition.mapper;

import com.audition.dto.AuditionPostDto;
import com.audition.model.AuditionPost;
import java.util.List;
import java.util.stream.Collectors;

public class AuditionPostMapper {

    public static AuditionPost mapToAuditionPost(AuditionPostDto auditionPostDto) {
        AuditionPost auditionPost = new AuditionPost();
        auditionPost.setId(auditionPostDto.getId());
        auditionPost.setUserId(auditionPostDto.getUserId());
        auditionPost.setTitle(auditionPostDto.getTitle());
        auditionPost.setBody(auditionPostDto.getBody());
        return auditionPost;
    }

    public static AuditionPostDto mapToAuditionPostDto(AuditionPost auditionPost) {
        AuditionPostDto auditionPostDto = new AuditionPostDto();
        auditionPostDto.setId(auditionPost.getId());
        auditionPostDto.setUserId(auditionPost.getUserId());
        auditionPostDto.setTitle(auditionPost.getTitle());
        auditionPostDto.setBody(auditionPost.getBody());
        return auditionPostDto;
    }

    public static List<AuditionPost> mapToAuditionPostList(List<AuditionPostDto> auditionPostDtos) {
        return auditionPostDtos.stream()
            .map(AuditionPostMapper::mapToAuditionPost)
            .collect(Collectors.toList());
    }

    public static List<AuditionPostDto> mapToAuditionPostDtoList(List<AuditionPost> auditionPosts) {
        return auditionPosts.stream()
            .map(AuditionPostMapper::mapToAuditionPostDto)
            .collect(Collectors.toList());
    }
}
