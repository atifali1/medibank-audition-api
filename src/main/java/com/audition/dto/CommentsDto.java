package com.audition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Data Transfer Object (DTO) for comments on Audition Posts")
public class CommentsDto {

    @Schema(description = "ID of the post to which the comment is associated", example = "1", required = true)
    private int postId;

    @Schema(description = "Unique identifier for the comment", example = "101", required = true)
    private int id;

    @NotEmpty(message = "Name cannot be null or empty")
    @Schema(description = "Name of the person making the comment", example = "Jane Doe", required = true)
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the person making the comment", example = "janedoe@example.com", required = true)
    private String email;

    @NotEmpty(message = "Body cannot be null or empty")
    @Schema(description = "Content of the comment", example = "This is an insightful post!", required = true)
    private String body;
}
