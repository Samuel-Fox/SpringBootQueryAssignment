package com.genspark.SpringBootQueryAssignment.Controller;

import com.genspark.SpringBootQueryAssignment.Entity.Book;
import com.genspark.SpringBootQueryAssignment.Service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController
{
	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getBooks() {
		return this.bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable String id){
		return this.bookService.getBookById(Long.parseLong(id));
	}

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return this.bookService.addBook(book);
	}

	@PutMapping
	public Book updateBook(@RequestBody Book book) {
		return this.bookService.updateBook(book);
	}

	@DeleteMapping("/{id}")
	public String deleteBook (@PathVariable String id) {
		return this.bookService.deleteBook(Long.parseLong(id));
	}

	@GetMapping("/author/{name}")
	public List<Book> getBookByAuthor(@PathVariable String name) {
		return this.bookService.findBooksByAuthor(name);
	}

	@GetMapping("/publication")
	public List<Book> getBookByPriceRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear)
	{
		return bookService.findBooksByPublicationYear(startYear, endYear);
	}
}
