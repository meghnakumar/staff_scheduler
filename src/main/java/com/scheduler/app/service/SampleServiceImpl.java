package com.scheduler.app.service;

import com.scheduler.app.model.entity.BookDTO;
import com.scheduler.app.model.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SampleServiceImpl implements  SampleService{

    @Autowired
    BookRepository bookRepo;

    public List<BookDTO> getAllBooks(){
        return bookRepo.findByIsbnIsNotNull().stream().map(temp -> {
            return new BookDTO(temp.getIsbn(), temp.getBookName(), temp.getAuthor());
        }).collect(Collectors.toList());
    }

    public BookDTO fetchBookByISBN(int id){
        Optional<BookDTO> initial = bookRepo.findByIsbn(id).stream().map(temp -> {
            return new BookDTO(temp.getIsbn(), temp.getBookName(), temp.getAuthor());
        }).findFirst();
        if(initial.isPresent()){
            BookDTO result = initial.get();
            return result;
        }
        else {
            return new BookDTO(0, "", "");
        }
    }

}
