package com.ood.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ood.library.entity.Book;


public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
    Book findByISBN(String isbn);
}

