package com.audition.integration;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS_ENDPOINT = "/posts";
    private static final String COMMENTS_ENDPOINT = "/comments";

    @Autowired
    private RestTemplate restTemplate;


    public List<AuditionPost> getPosts() {
        //  make RestTemplate call to get Posts from https://jsonplaceholder.typicode.com/posts
        try {
            AuditionPost[] postsArray = restTemplate.getForObject(BASE_URL + POSTS_ENDPOINT, AuditionPost[].class);
            return Arrays.asList(postsArray);
        } catch (HttpClientErrorException e) {
            throw handleHttpClientException(e);
        }

    }

    public AuditionPost getPostById(final String id) {
        //  get post by post ID call from https://jsonplaceholder.typicode.com/posts/
        try {
            return restTemplate.getForObject(BASE_URL + POSTS_ENDPOINT + "/" + id, AuditionPost.class);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                throw handleHttpClientException(e);
            }
        }
    }

    //  Write a method GET comments for a post from https://jsonplaceholder.typicode.com/posts/{postId}/comments - the comments must be returned as part of the post.
    public List<Comment> getCommentsByPostId(final int postId) {
        try {
            return Arrays.asList(restTemplate.getForObject(BASE_URL + POSTS_ENDPOINT + "/" + postId + COMMENTS_ENDPOINT,
                Comment[].class));
        } catch (HttpClientErrorException e) {
            throw handleHttpClientException(e);
        }
    }

    //  write a method. GET comments for a particular Post from https://jsonplaceholder.typicode.com/comments?postId={postId}.
    // The comments are a separate list that needs to be returned to the API consumers. Hint: this is not part of the AuditionPost pojo.

    public List<Comment> getCommentsForPost(final int postId) {
        try {
            return Arrays.asList(
                restTemplate.getForObject(BASE_URL + COMMENTS_ENDPOINT + "?postId=" + postId, Comment[].class));
        } catch (HttpClientErrorException e) {
            throw handleHttpClientException(e);
        }
    }


    private SystemException handleHttpClientException(HttpClientErrorException e) {
        String errorDetail = String.format("Error fetching data from external API: %s. Status: %s", e.getMessage(),
            e.getStatusCode().toString());
        return new SystemException(errorDetail, e.getStatusCode().toString(), e.getRawStatusCode());
    }
}


