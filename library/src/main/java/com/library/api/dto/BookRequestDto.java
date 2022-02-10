package com.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 *
 * This class collects the request and map it to the entity class
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookRequestDto {

    private Long id;
    private String title;
    private String ISBN;
    private String language;
    private String publisher;
    private String author;
    private Boolean favorite;

    @NotNull(message = "categoryId can not be blank")
    @Min(message = "categoryId can not be less than 1", value = 1)
    private Long categoryId;

    private LocalDateTime publisherDate;

}
