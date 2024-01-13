package com.ood.library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BookItem_TBL")
public class BookItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookItemId;
    
    
    private float price;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;  // Reference to the Book this item is an instance of
    
    
    @JsonIgnore
    @ManyToMany(mappedBy = "booksrented")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
		return users;
	}



	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Enumerated(EnumType.STRING)
    private BookItemStatus status = BookItemStatus.AVAILABLE;  // Default is AVAILABLE
    
    // ... other attributes, getters, setters, etc.

    public enum BookItemStatus {
        AVAILABLE,
        ASSIGNED
//        DAMAGED
 
    }

	public BookItem() {
		super();
	}



	public BookItem(Long bookItemId, float price, Book book, BookItemStatus status) {
		super();
		this.bookItemId = bookItemId;
		this.price = price;
		this.book = book;
		this.status = status;
	}



	public Long getBookItemId() {
		return bookItemId;
	}

	public void setBookItemId(Long bookItemId) {
		this.bookItemId = bookItemId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookItemStatus getStatus() {
		return status;
	}

	public void setStatus(BookItemStatus status) {
		this.status = status;
	}
    

    public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

    
}
