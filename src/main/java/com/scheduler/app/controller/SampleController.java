package com.scheduler.app.controller;

import com.scheduler.app.model.entity.Book;
import com.scheduler.app.model.entity.BookDTO;
import com.scheduler.app.model.repo.BookRepository;
import com.scheduler.app.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Operation(summary = "Get a list of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @RequestMapping(method = RequestMethod.GET, value = "/test-api")
    public List<BookDTO> fetchAllBooks() {
        return sampleService.getAllBooks();
    }

    @Operation(summary = "Get our book by its isbn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid isbn supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @RequestMapping(method = RequestMethod.GET, value = "/test-api-2/{id}")
    public BookDTO findById(@Parameter(description = "isbn of book to be searched")
                         @PathVariable int id) {
        return sampleService.fetchBookByISBN(id);
    }
}
