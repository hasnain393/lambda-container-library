package com.ood.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ood.library.entity.Book;
import com.ood.library.entity.Transaction;
import com.ood.library.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	   Transaction findByUserAndBook(User user, Book book);

}

