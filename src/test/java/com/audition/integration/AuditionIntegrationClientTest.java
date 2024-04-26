package com.audition.integration;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditionIntegrationClientTest {

    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";


    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuditionIntegrationClient auditionIntegrationClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPosts_Success() {
        AuditionPost[] postsArray = {new AuditionPost(1, 1, "Title 1", "Body 1"),
            new AuditionPost(2, 2, "Title 2", "Body 2")};
        when(restTemplate.getForObject(POSTS_URL, AuditionPost[].class)).thenReturn(postsArray);
        List<AuditionPost> result = auditionIntegrationClient.getPosts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Title 1", result.get(0).getTitle());
        assertEquals(2, result.get(1).getId());
        assertEquals("Title 2", result.get(1).getTitle());
    }

    @Test
    public void testGetPosts_HttpClientErrorException() {
        when(restTemplate.getForObject(POSTS_URL, AuditionPost[].class))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(SystemException.class, () -> auditionIntegrationClient.getPosts());
    }

    @Test
    public void testGetPostById_Success() {
        String url = POSTS_URL+"/1";
        AuditionPost post = new AuditionPost(1, 1, "Title 1", "Body 1");
        when(restTemplate.getForObject(url, AuditionPost.class)).thenReturn(post);

        AuditionPost result = auditionIntegrationClient.getPostById("1");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Title 1", result.getTitle());
    }

    @Test
    public void testGetPostById_HttpClientErrorException_NotFound() {
        String url = POSTS_URL+"/1";
        when(restTemplate.getForObject(url, AuditionPost.class))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(SystemException.class, () -> auditionIntegrationClient.getPostById("1"));
    }

    @Test
    public void testGetCommentsByPostId_Success() {
        String url = POSTS_URL+"/1/comments";
        Comment[] commentsArray = {new Comment(1, 1, "Name 1", "email1@example.com", "Comment body 1"),
            new Comment(1, 2, "Name 2", "email2@example.com", "Comment body 2")};
        when(restTemplate.getForObject(url, Comment[].class)).thenReturn(commentsArray);

        List<Comment> result = auditionIntegrationClient.getCommentsByPostId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Name 1", result.get(0).getName());
    }
    @Test
    public void testGetCommentsByPostId_HttpClientErrorException() {
        String url = POSTS_URL+"/1/comments";
        when(restTemplate.getForObject(url, Comment[].class))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));

        SystemException thrown = assertThrows(SystemException.class, () -> auditionIntegrationClient.getCommentsByPostId(1));
        assertEquals(404, thrown.getStatusCode());
    }

    @Test
    public void testGetCommentsForPost_Success() {
        String url = "https://jsonplaceholder.typicode.com/comments?postId=2";
        Comment[] commentsArray = {new Comment(2, 3, "Name 3", "email3@example.com", "Comment body 3"),
            new Comment(2, 4, "Name 4", "email4@example.com", "Comment body 4")};
        when(restTemplate.getForObject(url, Comment[].class)).thenReturn(commentsArray);

        List<Comment> result = auditionIntegrationClient.getCommentsForPost(2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(3, result.get(0).getId());
        assertEquals("Name 3", result.get(0).getName());
    }

    @Test
    public void testGetCommentsForPost_HttpClientErrorException() {
        String url = "https://jsonplaceholder.typicode.com/comments?postId=2";
        when(restTemplate.getForObject(url, Comment[].class))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));

        SystemException thrown = assertThrows(SystemException.class, () -> auditionIntegrationClient.getCommentsForPost(2));
        assertEquals(404, thrown.getStatusCode());
    }

}
