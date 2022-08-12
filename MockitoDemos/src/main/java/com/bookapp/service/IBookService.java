package com.bookapp.service;

import java.util.List;

import com.bookapp.exceptions.Book;
import com.bookapp.exceptions.BookNotFoundException;



public interface IBookService {

	List<Book> getAll() throws BookNotFoundException;
	List<Book> getBylessPrice (double price);
	Book getById(int bookId)throws BookNotFoundException;
	
	void addBook(Book book);
	
	String greetMessage();
	
	
}