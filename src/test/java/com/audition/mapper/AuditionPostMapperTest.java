package com.audition.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.audition.dto.AuditionPostDto;
import com.audition.model.AuditionPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AuditionPostMapperTest {

    @Test
    public void testMapToAuditionPost() {
        // Setup
        AuditionPostDto auditionPostDto = new AuditionPostDto();
        auditionPostDto.setId(1);
        auditionPostDto.setUserId(10);
        auditionPostDto.setTitle("Test Title");
        auditionPostDto.setBody("Test Body");

        // Execution
        AuditionPost auditionPost = AuditionPostMapper.mapToAuditionPost(auditionPostDto);

        // Assertions
        assertEquals(auditionPostDto.getId(), auditionPost.getId());
        assertEquals(auditionPostDto.getUserId(), auditionPost.getUserId());
        assertEquals(auditionPostDto.getTitle(), auditionPost.getTitle());
        assertEquals(auditionPostDto.getBody(), auditionPost.getBody());
    }

    @Test
    public void testMapToAuditionPostDto() {
        // Setup
        AuditionPost auditionPost = new AuditionPost();
        auditionPost.setId(1);
        auditionPost.setUserId(10);
        auditionPost.setTitle("Test Title");
        auditionPost.setBody("Test Body");

        // Execution
        AuditionPostDto auditionPostDto = AuditionPostMapper.mapToAuditionPostDto(auditionPost);

        // Assertions
        assertEquals(auditionPost.getId(), auditionPostDto.getId());
        assertEquals(auditionPost.getUserId(), auditionPostDto.getUserId());
        assertEquals(auditionPost.getTitle(), auditionPostDto.getTitle());
        assertEquals(auditionPost.getBody(), auditionPostDto.getBody());
    }

    @Test
    public void testMapToAuditionPostList() {
        // Setup
        List<AuditionPostDto> dtos = Arrays.asList(
            new AuditionPostDto(1, 10, "Title 1", "Body 1"),
            new AuditionPostDto(2, 20, "Title 2", "Body 2")
        );

        // Execution
        List<AuditionPost> posts = AuditionPostMapper.mapToAuditionPostList(dtos);

        // Assertions
        assertEquals(2, posts.size());
        assertEquals(dtos.get(0).getId(), posts.get(0).getId());
        assertEquals(dtos.get(1).getTitle(), posts.get(1).getTitle());
    }

    @Test
    public void testMapToAuditionPostDtoList() {
        // Setup
        List<AuditionPost> posts = Arrays.asList(
            new AuditionPost(1, 10, "Title 1", "Body 1"),
            new AuditionPost(2, 20, "Title 2", "Body 2")
        );

        // Execution
        List<AuditionPostDto> dtos = AuditionPostMapper.mapToAuditionPostDtoList(posts);

        // Assertions
        assertEquals(2, dtos.size());
        assertEquals(posts.get(0).getId(), dtos.get(0).getId());
        assertEquals(posts.get(1).getTitle(), dtos.get(1).getTitle());
    }
}
