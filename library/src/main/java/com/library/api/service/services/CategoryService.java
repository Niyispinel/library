package com.library.api.service.services;

import com.google.gson.Gson;
import com.library.api.dto.CategoryRequestDto;
import com.library.api.dto.CategoryResponseDto;
import com.library.api.model.Book;
import com.library.api.model.Category;
import com.library.api.service.repositories.BookRepository;
import com.library.api.service.repositories.CategoryRepository;
import com.library.api.utils.Validations;
import com.library.api.utils.exception.ConflictException;
import com.library.api.utils.exception.CustomResponseCode;
import com.library.api.utils.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    private Validations validations;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public CategoryResponseDto addCategory(CategoryRequestDto request, HttpServletRequest request1) {
        List<Book> responseDtos = new ArrayList<>();
        validations.validateCategory(request);
        Category category = mapper.map(request, Category.class);

        category.setReferenceNo(validations.generateReferenceNumber(10));
        Category categoryExists = categoryRepository.findByReferenceNo(category.getReferenceNo());
        if(category.getReferenceNo() == null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Category does not have Reference Number");
        }

        if(categoryExists != null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Category already exist");
        }

        category.setIsActive(true);
        category = categoryRepository.save(category);
        log.debug("Create new category - {}"+ new Gson().toJson(category));


        CategoryResponseDto categoryResponseDto = mapper.map(category, CategoryResponseDto.class);

        if(request.getBooks() != null) {
            request.getBooks().forEach(book -> {
                book.setCategoryId(categoryResponseDto.getId());
            });

            responseDtos = bookService.addBooks(request.getBooks());
            List<Book> finalResponseDtos = responseDtos;
            responseDtos.forEach(book -> {
                categoryResponseDto.setBooks(finalResponseDtos);
            });
        }

        return categoryResponseDto;
    }

    public CategoryResponseDto editCategory(CategoryRequestDto request) {
        validations.validateCategory(request);
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested category Id does not exist!"));
        mapper.map(request, category);

        categoryRepository.save(category);
        log.debug("category record updated - {}"+ new Gson().toJson(category));

        CategoryResponseDto categoryResponseDto = mapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    /** <summary>
     * book delete
     * </summary>
     * <remarks>this method is responsible for deleting already existing category</remarks>
     */

    public CategoryResponseDto deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested category Id does not exist!"));
        categoryRepository.deleteById(category.getId());
        log.debug("Book Deleted - {}"+ new Gson().toJson(category));
        return mapper.map(category, CategoryResponseDto.class);
    }


    public CategoryResponseDto findCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested category Id does not exist!"));
        CategoryResponseDto categoryResponseDto = mapper.map(category, CategoryResponseDto.class);
        categoryResponseDto.setBooks(getBooks(id));

        return categoryResponseDto;

    }


    public List<Category> getAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories;

    }

    public List<Book> getBooks(Long categoryId){
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        return books;

    }
}
