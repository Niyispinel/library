package com.library.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDto {

    private Long id;
    private Long categoryId;
    private String title;
    private String ISBN;
    private String language;
    private String publisher;
    private String author;
    private LocalDateTime publisherDate;
    private Boolean favorite;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
