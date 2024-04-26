package com.audition.web;

import com.audition.dto.AuditionPostDto;
import com.audition.dto.CommentsDto;
import com.audition.service.IAuditionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
@Tag(name = "Read-Only REST APIs for Audition Posts",
    description = "REST APIs in Audition Application to FETCH AuditionPost details and their related comments")
public class AuditionController {

    IAuditionService auditionService;

    // Add a query param that allows data filtering. The intent of the filter is at developers discretion.

    @Operation(
        summary = "Fetch Audition Posts Details REST API",
        description = "REST API to fetch Audition Posts based on a Title"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
        )
    }
    )
    @GetMapping("/posts")
    public ResponseEntity<List<AuditionPostDto>> getPosts(
        @RequestParam @NotBlank(message = "Title cannot be empty") String title) {
        // Add logic that filters response data based on the query param
        List<AuditionPostDto> filteredPosts = auditionService.getPosts().stream()
            .filter(post -> post.getTitle().contains(title))
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(filteredPosts);
    }

    @Operation(
        summary = "Fetch Audition Posts Details By ID REST API",
        description = "REST API to fetch Audition Posts based on a ID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
        )
    })
    @GetMapping("/posts/{id}")
    public ResponseEntity<AuditionPostDto> getPostById(
        @PathVariable("id") @Pattern(regexp = "\\d+", message = "Post ID must be a numeric value") String postId) {
        AuditionPostDto auditionPostDto = auditionService.getPostById(postId);
        //Add input validation
        return ResponseEntity.ok(auditionPostDto);
    }

    //  Add additional methods to return comments for each post. Hint: Check https://jsonplaceholder.typicode.com/
    @Operation(
        summary = "Fetch CommentsByPostId  REST API",
        description = "REST API to fetch Comments based on a PostID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
        )
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentsDto>> getCommentsByPostId(
        @PathVariable("postId") @Pattern(regexp = "\\d+", message = "Post ID must be a numeric value") int postId) {
        List<CommentsDto> commentsByPostId = auditionService.getCommentsByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentsByPostId);
    }
    @Operation(
        summary = "Fetch CommentsForPost  REST API",
        description = "REST API to fetch CommentsForPost based on PostID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
        )
    })
    @GetMapping("/comments")
    public ResponseEntity<List<CommentsDto>> getCommentsForPost(
        @RequestParam @Pattern(regexp = "\\d+", message = "Post ID must be a numeric value") int postId) {
        List<CommentsDto> commentsForPost = auditionService.getCommentsForPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentsForPost);
    }

}
