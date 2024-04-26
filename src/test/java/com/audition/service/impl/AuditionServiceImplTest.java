package com.audition.service.impl;

import com.audition.dto.AuditionPostDto;
import com.audition.dto.CommentsDto;
import com.audition.integration.AuditionIntegrationClient;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuditionServiceImplTest {

    @Mock
    private AuditionIntegrationClient auditionIntegrationClient;

    @InjectMocks
    private AuditionServiceImpl auditionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPostsReturnsPostDtos() {
        List<AuditionPost> auditionPost = Arrays.asList(new AuditionPost(1, 1, "Test Title", "Test Body"));
        when(auditionIntegrationClient.getPosts()).thenReturn(auditionPost);
        List<AuditionPostDto> auditionPostDto = auditionService.getPosts();
        assertNotNull(auditionPostDto);
        assertEquals(1, auditionPostDto.size());
        assertEquals("Test Title", auditionPostDto.get(0).getTitle());
        verify(auditionIntegrationClient).getPosts();
        verifyNoMoreInteractions(auditionIntegrationClient);
    }

    @Test
    void getPostByIdReturnsPostDto() {
        AuditionPost post = new AuditionPost(1, 1, "Specific Title", "Specific Body");
        when(auditionIntegrationClient.getPostById("1")).thenReturn(post);
        AuditionPostDto result = auditionService.getPostById("1");
        assertNotNull(result);
        assertEquals("Specific Title", result.getTitle());
        verify(auditionIntegrationClient).getPostById("1");
        verifyNoMoreInteractions(auditionIntegrationClient);
    }


    @Test
    void testGetCommentsByPostId() {
        Comment comment1 = new Comment(1, 1, "Name 1", "email1@example.com", "Comment 1");
        Comment comment2 = new Comment(2, 2, "Name 2", "email2@example.com", "Comment 2");
        List<Comment> commentList = Arrays.asList(comment1, comment2);
        when(auditionIntegrationClient.getCommentsByPostId(1)).thenReturn(commentList);
        List<CommentsDto> result = auditionService.getCommentsByPostId(1);  // Verifying the interactions and assertions
        assertEquals(2, result.size());
        assertEquals(comment1.getName(), result.get(0).getName());
        assertEquals(comment2.getEmail(), result.get(1).getEmail());
        // Verifying that the method in the mock was called
        verify(auditionIntegrationClient, times(1)).getCommentsByPostId(1);
    }



    @Test
    void testGetCommentsForPost() {

        List<Comment> mockComments = Arrays.asList(
            new Comment(1, 1, "Name 1", "email1@example.com", "Comment 1"),
            new Comment(1, 2, "Name 2", "email2@example.com", "Comment 2")
        );

        when(auditionIntegrationClient.getCommentsForPost(1)).thenReturn(mockComments);

        List<CommentsDto> actualDtos = auditionService.getCommentsForPost(1);

        // Assertions
        assertEquals(2, actualDtos.size(), "Should return two comments");
        assertEquals("Name 1", actualDtos.get(0).getName(), "First comment's name should match expected");
        assertEquals("email1@example.com", actualDtos.get(0).getEmail(), "First comment's email should match expected");
        assertEquals("Comment 1", actualDtos.get(0).getBody(), "First comment's body should match expected");
        assertEquals("Name 2", actualDtos.get(1).getName(), "Second comment's name should match expected");
        assertEquals("email2@example.com", actualDtos.get(1).getEmail(),
            "Second comment's email should match expected");
        assertEquals("Comment 2", actualDtos.get(1).getBody(), "Second comment's body should match expected");

        // Verify the interactions
        verify(auditionIntegrationClient, times(1)).getCommentsForPost(1);
    }

}
