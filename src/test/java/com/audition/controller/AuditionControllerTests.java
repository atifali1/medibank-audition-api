package com.audition.controller;

import com.audition.AuditionApplication;
import com.audition.common.logging.AuditionLogger;
import com.audition.dto.AuditionPostDto;
import com.audition.dto.CommentsDto;
import com.audition.service.IAuditionService;
import com.audition.web.AuditionController;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuditionController.class)
@SpringJUnitConfig(classes = AuditionApplication.class) // Specify your main application class
public class AuditionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuditionService auditionService;

    @MockBean
    private AuditionLogger auditionLogger;


    @Test
    void shouldReturnFilteredPostsWhenValidTitleIsGiven() throws Exception {
        List<AuditionPostDto> mockPosts =
            Arrays.asList(new AuditionPostDto(1, 1, "Filtered Title", "Content"));
        when(auditionService.getPosts()).thenReturn(mockPosts);

        mockMvc.perform(get("/api/posts?title=Filtered")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Filtered Title"));
    }

    @Test
    void shouldReturnPostWhenValidIdIsGiven() throws Exception {
        AuditionPostDto mockPost = new AuditionPostDto(1, 1, "Valid Title", "Content");
        when(auditionService.getPostById("1")).thenReturn(mockPost);

        mockMvc.perform(get("/api/posts/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Valid Title"));
    }




    @Test
    void shouldReturnCommentsForGivenPostId() throws Exception {
        List<CommentsDto> mockComments = Arrays.asList(
            new CommentsDto(1, 1, "Name 1", "email1@example.com", "Comment 1"),
            new CommentsDto(2, 2, "Name 2", "email2@example.com", "Comment 2")
        );
        when(auditionService.getCommentsForPost(1)).thenReturn(mockComments);

        mockMvc.perform(get("/api/comments?postId=1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Name 1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Name 2"));
    }


}



