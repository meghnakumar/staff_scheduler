package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "temp_book")
public class Book implements Serializable {

    @Getter
    @Setter
    @Id()
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "isbn")
    private int isbn;

    @Getter
    @Setter
    @Column(name = "book_name")
    private String bookName;

    @Getter
    @Setter
    @Column(name = "author")
    private String author;

    public Book() {
    }

    public Book(int isbn, String bookName, String author) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
    }
}
