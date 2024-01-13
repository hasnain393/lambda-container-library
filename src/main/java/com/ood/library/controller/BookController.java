package com.ood.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ood.library.entity.Book;
import com.ood.library.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;
    

    @GetMapping("/books")
    public List<Book> findAllProducts() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public Book addProduct(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    

    @PostMapping("/books/add-multiple")
    public List<Book> addProducts(@RequestBody List<Book> books) {
        return bookService.saveBooks(books);
    }


    @GetMapping("/books/id/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/books/name/{name}")
    public Book findBookByName(@PathVariable String name) {
        return bookService.getBookByTitle(name);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
    
    @PostMapping("/users/{userId}/buyBook/{bookId}")
    public String buyBook(@PathVariable Long userId,@PathVariable Long bookId) {
        return bookService.buyBookForUser(userId,bookId);
    }

    @PostMapping("/users/{userId}/sellBook/{bookId}")
    public String sellBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return bookService.sellBook(userId, bookId);
    }

    @PostMapping("/users/{userId}/sellBookByISBN/{isbn}")
    public String sellBookByISBN(@PathVariable Long userId, @PathVariable String isbn) {
        return bookService.sellBookByISBN(userId, isbn);
    }

   
    

}
