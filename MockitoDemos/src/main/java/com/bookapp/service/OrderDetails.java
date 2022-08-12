package com.bookapp.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bookapp.exceptions.Book;
import com.bookapp.exceptions.BookNotFoundException;

public class OrderDetails {

	IBookService bookservice;
	public void setBookService(IBookService bookservice) {
		this.bookservice = bookservice;
		
	}
	public String showMessage(String name) {
		
		String result = bookservice.greetMessage();
		if(name.startsWith("P")) {
			return result.concat(" ").concat(name);
		}
		return "Wrong username";
	}
	
	public List<Book> findByAuthor(String author) throws BookNotFoundException{
		// System.out.println("in order details");
		List<Book> books = bookservice.getAll();
		if(books == null) {
			return null;
		}
		if(books.isEmpty()) {
			throw new BookNotFoundException();
		}
		return books.stream()
				.filter(book ->book.getAuthor().equals(author))
				.sorted((p1,p2)->p1.getTitle().compareTo(p2.getTitle()))
				.collect(Collectors.toList());
				
				
	}
	public String orderBook(int bookId) {
		try {
			Book book= bookservice.getById(bookId);
			if(book == null)
				return "out of stock";
				return "Book ordered";
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		return "Technical issues";
		
	}
	public String addBook(Book book) {
		
		if(book==null)
		return  "Book not added";
		bookservice.addBook(book);
		return "Book added";
	}
	
}
