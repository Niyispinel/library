package com.library.api.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.library.api.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String referenceNo;
    private String status;
    private LocalDateTime createdDate;

    private List<Book> books;


}
