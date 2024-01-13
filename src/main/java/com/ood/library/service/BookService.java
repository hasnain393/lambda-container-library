package com.ood.library.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ood.library.entity.Book;
import com.ood.library.entity.BookItem;
import com.ood.library.entity.Transaction;
import com.ood.library.entity.User;
import com.ood.library.repository.BookItemRepository;
import com.ood.library.repository.BookRepository;
import com.ood.library.repository.TransactionRepository;
import com.ood.library.repository.UserRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    
    @Autowired
    private BookItemRepository bookItemRepository; // This is a new repository for handling individual book items.

   
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveBooks(List<Book> book) {
        return bookRepository.saveAll(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book getBookByTitle(String name) {
        return bookRepository.findByTitle(name);
    }

    public String deleteBook(Long id) {
    	bookRepository.deleteById(id);
        return "book removed !! " + id;
    }

    public Book updateBook(Book book) {
        // Find the book by id; if it's not available, throw an exception.
    	Book existingBook = bookRepository.findById(book.getId()).orElse(null);

        // Update all the details of the retrieved book with the details from the book object provided.
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setISBN(book.getISBN());
        existingBook.setEdition(book.getEdition());
        existingBook.setStock(book.getStock());
        // If there are any other fields, update them here as well.

        // Save the updated book object to the database and return it.
        return bookRepository.save(existingBook);
    }
    
 

//    public String buyBookForUser(Long userId, Long bookId) {
//    	System.out.println(userId);
//    	System.out.println(bookId);
//        User user = userRepository.findById(userId).orElse(null);
//        if (user == null) {
//            return "Failure: User not found";
//        }
//
//        Book book = bookRepository.findById(bookId).orElse(null);
//        if (book == null || book.getStock() <= 0) {
//            return "Failure: Book not available";
//        }
//
//        float buyPrice = Math.max(book.getPrice(), 15.0f); // If price is below 15, buy it at 15
//        book.setStock(book.getStock() - 1); // one book bought
//
//        user.getBooks().add(book);  // associate the book with the user
//        userRepository.save(user);   // save the updated user entity
//        bookRepository.save(book);  // save the updated book entity
//
//        return "Success: User " + user.getUsername() + " bought book " + book.getTitle() + " at " + buyPrice;
//    }
    
    
    
    public String buyBookForUser(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null) {
            return "Failure: User not found";
        }
        if (book == null) {
            return "Failure: Book not found";
        }

     // Check if the user has already rented this book
        Set<BookItem> userBooks = user.getBooksrented();
        for (BookItem item : userBooks) {
            if (item.getBook().equals(book)) {
                return "Failure: User has already bought this book";
            }
        }
        
        // Find an available BookItem for this book
        Optional<BookItem> availableItem = bookItemRepository.findFirstByBookAndStatus(book, BookItem.BookItemStatus.AVAILABLE);
        if (!availableItem.isPresent()) {
            return "Failure: Book not available";
        }

        BookItem itemToAssign = availableItem.get();
        
        System.out.println(itemToAssign);
        itemToAssign.setStatus(BookItem.BookItemStatus.ASSIGNED);
        user.getBooksrented().add(itemToAssign);
        
        

        // Update book stock
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setPurchasePrice(Math.max(itemToAssign.getPrice(), 15.0f));
        transaction.setPurchaseDate(LocalDateTime.now());
        transaction.setTransactionType("BUY");

        userRepository.save(user);
        bookItemRepository.save(itemToAssign);
        transactionRepository.save(transaction);

        return "Success: User " + user.getUsername() + " bought book " + book.getTitle() + " at " + transaction.getPurchasePrice();
    }

    public String sellBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null) {
            return "Failure: User not found";
        }
        if (book == null) {
            return "Failure: Book not found";
        }

        // Find the BookItem that this user has rented for this book
        Optional<BookItem> rentedItem = user.getBooksrented().stream()
                                            .filter(item -> item.getBook().equals(book))
                                            .findFirst();

        if (!rentedItem.isPresent()) {
            return "Failure: User hasn't bought this book";
        }

        BookItem itemToReturn = rentedItem.get();
        itemToReturn.setStatus(BookItem.BookItemStatus.AVAILABLE);
        user.getBooksrented().remove(itemToReturn);
        
        // Update book stock
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);
        
        

        float newPrice = calculateDepreciatedPrice(itemToReturn.getPrice());
        itemToReturn.setPrice(newPrice);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
  
        transaction.setPurchasePrice(newPrice);
        transaction.setPurchaseDate(LocalDateTime.now());
        transaction.setTransactionType("SELL");

        userRepository.save(user);
        bookItemRepository.save(itemToReturn);
        transactionRepository.save(transaction);

        return "Success: User " + user.getUsername() + " sold back book " + book.getTitle() + " at " + newPrice;
    }
    
    public String sellBookByISBN(Long userId, String isbn) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findByISBN(isbn); // Assuming you have this method in the bookRepository
        
        System.out.println(book.getTitle());
        

        if (user == null) {
            return "Failure: User not found";
        }
        if (book == null) {
            return "Failure: Book not found by ISBN";
        }

        // Find the BookItem that this user has rented for this book
        Optional<BookItem> rentedItem = user.getBooksrented().stream()
                                            .filter(item -> item.getBook().equals(book))
                                            .findFirst();

        if (!rentedItem.isPresent()) {
            return "Failure: User hasn't bought this book";
        }

        BookItem itemToReturn = rentedItem.get();
        itemToReturn.setStatus(BookItem.BookItemStatus.AVAILABLE);
        user.getBooksrented().remove(itemToReturn);

        // Update book stock
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        float newPrice = calculateDepreciatedPrice(itemToReturn.getPrice());
        itemToReturn.setPrice(newPrice);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setPurchasePrice(newPrice);
        transaction.setPurchaseDate(LocalDateTime.now());
        transaction.setTransactionType("SELL");

        userRepository.save(user);
        bookItemRepository.save(itemToReturn);
        transactionRepository.save(transaction);

        return "Success: User " + user.getUsername() + " sold back book with ISBN " + isbn + " at " + newPrice;
    }



    private float calculateDepreciatedPrice(float price) {
        float newPrice = price * 0.9f; // reduces the price by 10%
        return Math.max(newPrice, 15.0f); // ensure the price never goes below 15
    }

}
