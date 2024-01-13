package com.ood.library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ood.library.entity.BookItem.BookItemStatus;

@Entity
@Table(name = "Book_TBL")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;  // Renamed to 'id' for clarity

    private String ISBN;  // New field
    private String author;  // Authors, assuming one string could have multiple authors' names
    private String title;  // New field
    private String edition;  // New field
    private int stock;  // Keeping stock as it might be necessary for managing copies of books


    
    
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookItem> bookItems = new HashSet<>();

    public int getAvailableStock() {
        return (int) bookItems.stream().filter(item -> item.getStatus() == BookItemStatus.AVAILABLE).count();
    }
    
    // Default constructor
	public Book() {
		super();
	}

    // Full constructor
	public Book(Long id, String ISBN, String author, String title, String edition, int stock) {
		super();
		this.bookId = id;
		this.ISBN = ISBN;
		this.author = author;
		this.title = title;
		this.edition = edition;
		this.stock = stock;
	
	}

    // Getters and setters for all fields
	public Long getId() {
		return bookId;
	}

	public void setId(Long id) {
		this.bookId = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
}
