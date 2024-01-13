package com.ood.library.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ood.library.entity.Book;
import com.ood.library.entity.BookItem;
import com.ood.library.entity.User;
import com.ood.library.exception.BookAlreadyAssignedException;
import com.ood.library.exception.BookNotAvailableException;
import com.ood.library.exception.BookNotFoundException;
import com.ood.library.exception.UserNotFoundException;
import com.ood.library.repository.BookItemRepository;
import com.ood.library.repository.BookRepository;
import com.ood.library.repository.UserRepository;

@Service
public class BookItemService {


	@Autowired
	private BookItemRepository bookItemRepository;
	


	public void createUser(BookItem bookItem) {
		bookItemRepository.save(bookItem);
	}

	public List<BookItem> getAllUsers() {
		return bookItemRepository.findAll();
	}

	public BookItem getUserById(Long id) {
		return bookItemRepository.findById(id).orElse(null);
	}

	public void updateUser(BookItem bookItem) {
		bookItemRepository.save(bookItem);
	}

	public void deleteUser(Long id) {
		bookItemRepository.deleteById(id);
	}

	
}
