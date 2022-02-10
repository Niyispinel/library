package com.library.api.utils;


import com.library.api.dto.BookRequestDto;
import com.library.api.dto.CategoryRequestDto;
import com.library.api.service.repositories.BookRepository;
import com.library.api.service.repositories.CategoryRepository;
import com.library.api.utils.exception.BadRequestException;
import com.library.api.utils.exception.CustomResponseCode;
import com.library.api.utils.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@SuppressWarnings("All")
@Slf4j
@Service
public class Validations {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Validations() {
    }

    public String generateReferenceNumber(int numOfDigits) {
        if (numOfDigits < 1) {
            throw new IllegalArgumentException(numOfDigits + ": Number must be equal or greater than 1");
        }
        long random = (long) Math.floor(Math.random() * 9 * (long) Math.pow(10, numOfDigits - 1)) + (long) Math.pow(10, numOfDigits - 1);
        return Long.toString(random);
    }

    public String generateCode(String code) {
        String encodedString = Base64.getEncoder().encodeToString(code.getBytes());
        return encodedString;
    }

    public void validateCategory (CategoryRequestDto request){

        if (request.getStatus() == null || request.getStatus().isEmpty() )
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Delivery Status cannot be empty");

        if (request.getName() == null || request.getName().isEmpty() )
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Name cannot be empty");

    }

    public void validateBook (BookRequestDto request){

        if (!Utility.isNumeric(request.getCategoryId().toString()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid data type for categoryId ");

        if (request.getTitle() == null || request.getTitle().isEmpty() )
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Title cannot be empty");

        if (request.getAuthor() == null || request.getAuthor().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Author cannot be empty");

        if (request.getISBN() == null || request.getISBN().isEmpty() )
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "ISBN cannot be empty");

        if (request.getPublisher() == null || request.getPublisher().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Publisher cannot be empty");


        categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        " categoryId does not Exist!")
        );
    }


}


