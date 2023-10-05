package com.genspark.SpringBootQueryAssignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genspark.SpringBootQueryAssignment.Controller.BookController;
import com.genspark.SpringBootQueryAssignment.Entity.Book;
import com.genspark.SpringBootQueryAssignment.Service.BookService;
import java.util.List;
import javax.net.ssl.SSLEngineResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTests
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BookService bookService;

	@Test
	public void testGetAll() throws Exception {
		Book book1 = new Book(1L, "Book1", "Author1", 12.99D, 1999);
		Book book2 = new Book(2L, "Book2", "Author2", 14.99D, 2012);

		List<Book> bookList = List.of(book1, book2);

		Mockito.when(bookService.getAllBooks()).thenReturn(bookList);

		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$[0].title").value("Book1"))
			.andExpect(jsonPath("$[1].title").value("Book2"))
			.andDo(print());
	}

	@Test
	public void testGetByID() throws Exception {
		Book book1 = new Book(1L, "Book1", "Author1", 12.99D, 1999);

		Mockito.when(bookService.getBookById(1L)).thenReturn(book1);

		mockMvc.perform(get("/books/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.title").value("Book1"))
			.andDo(print());
	}
	@Test
	public void testPostBook() throws Exception {
		Book book1 = new Book(1L, "Book1", "Author1", 12.99D, 1999);

		Mockito.when(bookService.addBook(book1)).thenReturn(book1);

		String requestBody = objectMapper.writeValueAsString(book1);
		mockMvc.perform(post("/books").contentType("application/json").content(requestBody))
			.andExpect(status().isOk())
			.andDo(print());
	}
	@Test
	public void testGetByAuthor() throws Exception {
		Book book1 = new Book(1L, "Book1", "Author1", 12.99D, 1999);
		Book book2 = new Book(2L, "Book2", "Author2", 14.99D, 2012);
		Book book3 = new Book(3L, "Book3", "Author1", 19.99D, 2012);

		List<Book> bookList = List.of(book1,book3);

		Mockito.when(bookService.findBooksByAuthor("Author1")).thenReturn(bookList);

		mockMvc.perform(get("/books/author/Author1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$[0].author").value("Author1"))
			.andExpect(jsonPath("$[1].author").value("Author1"))
			.andDo(print());

	}


}
