package com.bookapp.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookapp.exceptions.Book;
import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.service.IBookService;
import com.bookapp.service.OrderDetails;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
class OrderTest {

	@Mock
	IBookService bookservice;
	
	@InjectMocks
	OrderDetails details;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	Book book1,book2,book3,book4,book7,book5,book6;
	List<Book> booklList = null;
	
	@BeforeEach
	void setUp() throws Exception {
		
			book1 = new Book(1,"java", "kathy",900);
			book2 = new Book(2,"spring", "kathy",900);
			book3 = new Book(3,"the 5 am", "Robin",900);
			book4 = new Book(4,"JSp", "Kathy",900);
			book5 = new Book(5,"Leadership", "Robin",900);
			book6 = new Book(6,"Secret", "Rhonde",900);
			book7 = new Book(7,"Monk", "Robin",900);
			booklList = Arrays.asList(book1,book2,book3,book4,book5,book6,book7);	
	}
	@AfterEach
	void tearDown() throws Exception {
	}
    
	@Test@DisplayName("Testing add Book - ")
	void testAddBook() throws BookNotFoundException{
		doNothing().when(bookservice).addBook(book1);
		String actual = details.addBook(book1);
		assertEquals("Book added", actual);
	}
	@Test@DisplayName("Testing add Book - null")
	void testAddBookNull() throws BookNotFoundException{
		String actual = details.addBook(null);
		assertEquals("Book not added", actual);
	}
	
   private Mockito doNothig() {
		// TODO Auto-generated method stub
		return null;
	}

@Test @DisplayName("Testing Book - returns the one book instance")
    void TestBookId() throws BookNotFoundException{
	// call the method using mock object
	doReturn(book1).when(bookservice).getById(2);
	// method to be tested
	String actual =  details.orderBook(2);
	assertEquals("Book ordered", actual);
}
    
	@Test@DisplayName("Testing Book-returns null")
	void testBookByIdNull() throws BookNotFoundException{
		// call the method using mock object
		doReturn(null).when(bookservice).getById(10);
		String actual =  details.orderBook(10);
		assertEquals("out of stock", actual);
	}
	

	@Test@DisplayName("Testing Book-throws exception")
	void testBookByIdException() throws BookNotFoundException{
		// call the method using mock object
		doThrow(new BookNotFoundException()).when(bookservice).getById(20);
		// method to be tested 
		String actual = details.orderBook(20);
		assertEquals("Technical issues", actual);
	}
}
