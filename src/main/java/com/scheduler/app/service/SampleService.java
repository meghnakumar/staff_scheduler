package com.scheduler.app.service;

import com.scheduler.app.model.entity.BookDTO;
import java.util.List;

public interface SampleService {

    public List<BookDTO> getAllBooks();

    public BookDTO fetchBookByISBN(int isbn);
}
