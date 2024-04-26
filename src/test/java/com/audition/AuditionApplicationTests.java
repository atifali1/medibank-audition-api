package com.audition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.audition.model.Comment;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuditionApplicationTests {

    //  implement unit test. Note that an applicant should create additional unit tests as required.
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testApplicationBehavior() {
        // Simulate a request to a controller and validate the response
        String baseUrl = "http://localhost:" + port;
        List<Comment> response = List.of(
            Objects.requireNonNull(restTemplate.getForObject(baseUrl + "/api/posts/7/comments", Comment[].class)));
        assertNotNull(response.get(0).getEmail(), "Should return some content");
    }
}
