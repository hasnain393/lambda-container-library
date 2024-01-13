package com.ood.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ood.library.entity.BookItem;
import com.ood.library.service.BookItemService;

@RestController
@RequestMapping("/book-items")
public class BookItemController {

    @Autowired
    private BookItemService bookItemService;

    @PostMapping
    public ResponseEntity<BookItem> createBookItem(@RequestBody BookItem bookItem) {
        bookItemService.createUser(bookItem);
        return ResponseEntity.ok(bookItem);
    }

    @GetMapping
    public ResponseEntity<List<BookItem>> getAllBookItems() {
        return ResponseEntity.ok(bookItemService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookItem> getBookItemById(@PathVariable Long id) {
        BookItem bookItem = bookItemService.getUserById(id);
        if (bookItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookItem> updateBookItem(@PathVariable Long id, @RequestBody BookItem updatedBookItem) {
        bookItemService.updateUser(updatedBookItem);
        return ResponseEntity.ok(updatedBookItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookItem(@PathVariable Long id) {
        bookItemService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

