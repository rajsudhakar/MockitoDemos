package com.bookapp.testcases;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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


class OrderDetailsTest {
    

	@Mock  // create a proxy
	IBookService bookservice; // mock IBookService.
    
     // create of an object of order details and inject bookservice
    // deatils = new OrderDetails
    // details.setBookService(bookservice); 
    
    @InjectMocks 
	OrderDetails details;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	Book book1,book2,book3,book4,book7,book5,book6;
	List<Book> bookList = null;

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@Test
	void testShowMessage() {
		// using the mock object call the bookservice
		Mockito.when(bookservice.greetMessage()).thenReturn("Great Day");
		// this is the method to be tested
		String actual = details.showMessage("Priya"); // called first
		assertEquals("Great Day Priya", actual);
		String nactual = details.showMessage("Prachi");
		assertEquals("Great Day Prachi", nactual);
	}
	
	@Test
	void testAnShowMessage() {
		// using the mock object call the bookservice
		Mockito.when(bookservice.greetMessage()).thenReturn("Great Day");
		// this is the method to be tested
		String actual = details.showMessage("Raj"); // called first
		assertEquals("Wrong username", actual);
		String nactual = details.showMessage("Helen");
		assertEquals("Wrong username", nactual);
	}
	
	@BeforeEach
	void setUp() throws Exception {
	book1 = new Book(1,"java", "kathy",900);
	book2 = new Book(2,"spring", "kathy",900);
	book3 = new Book(3,"the 5 am", "Robin",900);
	book4 = new Book(4,"JSp", "Kathy",900);
	book5 = new Book(5,"Leadership", "Robin",900);
	book6 = new Book(6,"Secret", "Rhonde",900);
	book7 = new Book(7,"Monk", "Robin",900);
	bookList = Arrays.asList(book1,book2,book3,book4,book5,book6,book7);
		}
		@Test
		void testGetByAuthor() throws BookNotFoundException{
			String author = "Robin";
//			
			when(bookservice.getAll()).thenReturn(bookList);
			List<Book> actualBooks = details.findByAuthor(author);
			List<Book> expectedBooks = Arrays.asList(book5,book7,book3);
			assertEquals(expectedBooks, actualBooks);
		}
		@Test@DisplayName("Testing by author - throw exception")
		void testGetByAuthorException() throws BookNotFoundException{
			String author = "Priya";
			
			// mock the method and assume it returns empty list
			when(bookservice.getAll()).thenThrow(new BookNotFoundException());
			// throw the exception
			assertThrows(BookNotFoundException.class,()->{
				details.findByAuthor(author);
			});
		}
		
		
		@Test@DisplayName("Testing by author - empty list")
		void testGetByAuthorEmpty() throws BookNotFoundException{
			String author = "Priya";
			List<Book> emptyList = new ArrayList<>();
			// mock the method and assume it returns empty list
			when(bookservice.getAll()).thenReturn(emptyList);
			// throw the exception
			assertThrows(BookNotFoundException.class,()->{
				details.findByAuthor(author);
			});
		}
		@Test@DisplayName("Testing by author - null")
		void testGetByAuthorNull() throws BookNotFoundException{
			String author = "Priya";
			// mock the method and assume it returns null
			when(bookservice.getAll()).thenReturn(null);
			List<Book> actualBooks = details.findByAuthor(author);
			assertNull(actualBooks);
			
		}
	@AfterEach
	void tearDown() throws Exception {
		details = null;
	}

	@Test
	String testSetBookService() {
		return null;
	
	}
}

