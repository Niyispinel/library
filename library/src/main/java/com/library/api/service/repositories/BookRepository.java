package com.library.api.service.repositories;


import com.library.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByIsActive(Boolean isActive);

    List<Book> findByIsFavorite(Boolean isFavorite);

}
