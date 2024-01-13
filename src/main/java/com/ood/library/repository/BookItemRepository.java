package com.ood.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ood.library.entity.Book;
import com.ood.library.entity.BookItem;


public interface BookItemRepository extends JpaRepository<BookItem,Long> {
	 Optional<BookItem> findFirstByBookAndStatus(Book book, BookItem.BookItemStatus status);
	
}

