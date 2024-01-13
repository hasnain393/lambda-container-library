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
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;
	
	
	  @Autowired
	    private BookItemRepository bookItemRepository;

	public void createUser(User user) {
		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	
}
