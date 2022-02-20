package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class BookDTO {

    @Getter
    @Setter
    private int isbn;

    @Getter
    @Setter
    private String bookName;

    @Getter
    @Setter
    private String author;

    public BookDTO(int isbn, String bookName, String author) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "isbn=" + isbn +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
