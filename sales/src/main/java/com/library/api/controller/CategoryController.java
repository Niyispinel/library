package com.library.api.controller;


import com.library.api.dto.CategoryRequestDto;
import com.library.api.dto.CategoryResponseDto;
import com.library.api.dto.Response;
import com.library.api.model.Category;
import com.library.api.service.services.CategoryService;
import com.library.api.utils.exception.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    /** <summary>
     * Category creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Categories</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> addCategory(@Validated @RequestBody CategoryRequestDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        CategoryResponseDto response = service.addCategory(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Category edit endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating categories</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> editCategory(@Validated @RequestBody CategoryRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CategoryResponseDto response = service.editCategory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * book delete endpoint
     * </summary>
     * <remarks>this endpoint is responsible for deleting category</remarks>
     */

    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable long id){

        HttpStatus httpCode ;
        Response resp = new Response();
        CategoryResponseDto response = service.deleteCategory(id);
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
    public ResponseEntity<Response> getCategory(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        CategoryResponseDto response = service.findCategory(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Category> response = service.getAll();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
