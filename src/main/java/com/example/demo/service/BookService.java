package com.example.demo.service;

import com.example.demo.Book;

import java.util.List;

public interface BookService {
    public Book createBook(Book book);
    public Book[] addBooks(Book[] book);
    public List<Book> findAll();
    public Book getBookById(Long id) throws Exception;
    public List<Book> findByTitle(String title);
    public List<Book> findByAuthor(String author);
    public List<Book> findByTitleAndAuthor(String title, String author);
    public List<Book> findByTitleAndAuthorAndYear(String title, String author, Long year);
    public List<Book> ultimateFind(String title, String author, Long year, String text);
}