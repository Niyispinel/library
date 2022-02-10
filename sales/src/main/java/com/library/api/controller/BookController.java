package com.library.api.controller;


import com.library.api.dto.BookRequestDto;
import com.library.api.dto.BookResponseDto;
import com.library.api.dto.Response;
import com.library.api.model.Book;
import com.library.api.service.services.BookService;
import com.library.api.utils.exception.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    /** <summary>
     * book creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new book</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> addBook(@Validated @RequestBody BookRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        BookResponseDto response = service.addBook(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * book edit endpoint
     * </summary>
     * <remarks>this endpoint is responsible for editing book</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> editBook(@Validated @RequestBody BookRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        BookResponseDto response = service.editBook(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * book delete endpoint
     * </summary>
     * <remarks>this endpoint is responsible for deleting book</remarks>
     */

    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteBookById(@PathVariable long id){

        HttpStatus httpCode ;
        Response resp = new Response();
        BookResponseDto response = service.deleteBook(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record Deleted !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getBook(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        BookResponseDto response = service.findBook(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * book list endpoint
     * </summary>
     * <remarks>this endpoint is responsible for list of books</remarks>
     */

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Book> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/fav")
    public ResponseEntity<Response> getFavBooks(@RequestParam(value = "isFavorite",required = false)Boolean isFavorite){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Book> response = service.getFavBooks(isFavorite);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
