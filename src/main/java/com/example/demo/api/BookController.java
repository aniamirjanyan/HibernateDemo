package com.example.demo.api;

import com.example.demo.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    // Post a book (one at a time)
    @PostMapping("/addbook")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Post an array of books
    @PostMapping("/addbooks")
    ResponseEntity addBooks(@RequestBody Book[] books) {
        return new ResponseEntity(bookService.addBooks(books), HttpStatus.OK);
    }

    // Get all the books
    @GetMapping("/getbooks")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    // Get a book by the ID
    @GetMapping("/getbooks/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }
    // Find a book by title
    @GetMapping("/findbytitle/")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        return new ResponseEntity<>(bookService.findByTitle(title), HttpStatus.OK);
    }

    // Find a book by author
    @GetMapping("/findbyauthor/")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
        return new ResponseEntity<>(bookService.findByAuthor(author), HttpStatus.OK);
    }

    // Find a book by the title and author
    @GetMapping("/findbytitleandauthor/")
    public ResponseEntity<List<Book>> findByTitleAndAuthor(
            @RequestParam(required = false) String title, @RequestParam String author) {
        return new ResponseEntity<>(bookService.findByTitleAndAuthor(title, author), HttpStatus.OK);
    }

    // Find a book by any parameter (Nullable with Query)
    @GetMapping("/findbyanything/")
    public ResponseEntity<List<Book>> findByTitleAndAuthorAndYear(@RequestParam(required = false) Long year,
            @RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return new ResponseEntity<>(bookService.findByTitleAndAuthorAndYear(title, author, year), HttpStatus.OK);
    }

    // Find a book by any parameter (Nullable with Example)
    @GetMapping("/ultimatefind/")
    public ResponseEntity<List<Book>> finalFind(
            @RequestParam(required = false) String title, @RequestParam(required = false) String author,
            @RequestParam(required = false) Long year, @RequestParam(required = false) String text) {
        return new ResponseEntity<>(bookService.ultimateFind(title, author, year, text), HttpStatus.OK);
    }
}