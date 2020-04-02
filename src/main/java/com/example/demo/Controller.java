package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private BookRepository bookRepository;

    // Get all the books
    @GetMapping("/getbooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by the ID
    @GetMapping("/getbooks/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId)
            throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book " + bookId + " not found"));
        return ResponseEntity.ok().body(book);
    }

    // Post a book (one at a time)
    @PostMapping("/addbook")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Post an array of books
    @PostMapping("/addbooks")
    ResponseEntity addBooks(@RequestBody Book[] books) {
        for (Book book:books) {
            bookRepository.save(book);
        }
        return new ResponseEntity(books, HttpStatus.OK);
    }

    // Find a book by title
    @GetMapping("/findbytitle/")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title){
        List<Book> books = bookRepository.findByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Find a book by author
    @GetMapping("/findbyauthor/")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Find a book by the title and author
    @GetMapping("/findbytitleandauthor/")
    public ResponseEntity<List<Book>> findByTitleAndAuthor(@RequestParam (required = false) String title, @RequestParam String author) {
        List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Find a book by any parameter
    @GetMapping("/findbyanything/")
    public ResponseEntity<List<Book>> findByTitleAndAuthorAndYear(
            @RequestParam (required = false) String title, @RequestParam (required = false) String author,
            @RequestParam (required = false) Long year) {
        List<Book> books = bookRepository.findByTitleAndAuthorAndYear(title, author, year);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/ultimatefind/")
    public ResponseEntity<List<Book>> ultimateFind(
            @RequestParam (required = false) String title, @RequestParam (required = false) String author,
            @RequestParam (required = false) Long year) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withIgnoreNullValues()
                .withMatcher(title, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Book> exampleQuery = Example.of(new Book(title, author, year), matcher);
        List<Book> books = bookRepository.findAll(exampleQuery);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}

