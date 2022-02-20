package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByIsbnIsNotNull();

    List<Book> findByIsbn(int id);
}