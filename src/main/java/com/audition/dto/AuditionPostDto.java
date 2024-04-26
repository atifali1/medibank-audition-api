package com.audition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Schema(
    name = "AuditionPost",
    description = "Schema to hold Audition Post information"
)
public class AuditionPostDto {

    @Schema(
        description = "User ID of the author of the post",
        example = "1",
        required = true
    )
    private int userId;

    @NotEmpty(message = "Title cannot be null or empty")
    @Positive(message = "ID must be a positive number")
    @Min(value = 1, message = "ID must be at least 1")
    @Schema(
        description = "ID of the post",
        example = "10",
        required = true
    )
    private int id;

    @NotEmpty(message = "Title cannot be null or empty")
    @Schema(
        description = "Title of the post",
        example = "Introduction to Spring Boot",
        required = true
    )
    private String title;


    @Schema(
        description = "Body of the post",
        example = "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.",
        required = true
    )
    private String body;
}
