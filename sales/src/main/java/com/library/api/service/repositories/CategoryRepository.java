package com.library.api.service.repositories;


import com.library.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByReferenceNo(String referenceNo);

    Category findCategoryById(Long id);

    List<Category> findByIsActive(Boolean isActive);


}