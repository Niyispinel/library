package com.library.api.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.library.api.dto.BookRequestDto;
import com.library.api.dto.BookResponseDto;
import com.library.api.model.Book;
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

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
@Slf4j
@Service
public class BookService {


    private final ModelMapper mapper;
    private final ObjectMapper objectMapper;
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Validations validations;

    public BookService(ModelMapper mapper, ObjectMapper objectMapper, BookRepository bookRepository) {
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
    }

    /** <summary>
     * book creation
     * </summary>
     * <remarks>this method is responsible for creation of new book</remarks>
     */

    public BookResponseDto addBook(BookRequestDto request) {
        validations.validateBook(request);
        Book book = mapper.map(request, Book.class);
        Book bookExist = bookRepository.findBookByTitle(request.getTitle());
        if(bookExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " book already exist");
        }
        book.setIsActive(true);
        book = bookRepository.save(book);
        log.debug("Create new Book - {}"+ new Gson().toJson(book));
        return mapper.map(book, BookResponseDto.class);
    }

    public  List<Book> addBooks(List<BookRequestDto> requests) {
        List<Book> responseDtos = new ArrayList<>();
        requests.forEach(request->{
            validations.validateBook(request);
            Book book = mapper.map(request, Book.class);
            Book bookExist = bookRepository.findBookByTitle(request.getTitle());
            if(bookExist !=null){
                throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " book already exist");
            }
            book.setIsActive(true);
            book = bookRepository.save(book);
            log.debug("Create new Book - {}"+ new Gson().toJson(book));
            responseDtos.add(mapper.map(book, Book.class));
        });
        return responseDtos;
    }


    /** <summary>
     * book update
     * </summary>
     * <remarks>this method is responsible for updating already existing book</remarks>
     */

    public BookResponseDto editBook(BookRequestDto request) {
        validations.validateBook(request);
        Book book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested book Id does not exist!"));
        mapper.map(request, book);
        bookRepository.save(book);
        log.debug("State record updated - {}"+ new Gson().toJson(book));
        return mapper.map(book, BookResponseDto.class);
    }

    /** <summary>
     * book delete
     * </summary>
     * <remarks>this method is responsible for deleting already existing book</remarks>
     */

    public BookResponseDto deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested book Id does not exist!"));
        bookRepository.deleteById(book.getId());
        log.debug("Book Deleted - {}"+ new Gson().toJson(book));
        return mapper.map(book, BookResponseDto.class);
    }


    /** <summary>
     * Find book
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public BookResponseDto findBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested book Id does not exist!"));
        return mapper.map(book, BookResponseDto.class);

    }



    /** <summary>
     * List of Books
     * </summary>
     * <remarks>this method is responsible for getting List of Books</remarks>
     */
    public List<Book> getAll(Boolean isActive){
        List<Book> books = bookRepository.findByIsActive(isActive);
        return books;
    }

    public List<Book> getFavBooks(Boolean isFavorite){
        List<Book> books = bookRepository.findByIsFavorite(isFavorite);
        return books;
    }
}
