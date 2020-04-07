package com.example.demo.service;

import com.example.demo.Book;
import com.example.demo.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book[] addBooks(Book[] books) {
        for (Book book : books) {
            bookRepository.save(book);
        }
        return books;
    }

    @Override
    public Book getBookById(Long id)
        throws Exception {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new Exception("Book " + id + " not found"));
            return book;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Override
    public List<Book> findByTitleAndAuthorAndYear(String title, String author, Long year) {
        return bookRepository.findByTitleAndAuthorAndYear(title, author, year);
    }

    @Override
    public List<Book> ultimateFind(String title, String author, Long year, String text) {
        Book example = Book.builder().title(title).author(author).year(year).text(text).build();
        return bookRepository.findAll(Example.of(example));
    }
}