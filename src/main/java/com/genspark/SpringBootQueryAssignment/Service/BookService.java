package com.genspark.SpringBootQueryAssignment.Service;

import com.genspark.SpringBootQueryAssignment.Entity.Book;
import java.util.List;

public interface BookService
{
	List<Book> getAllBooks();
	Book getBookById(long id);
	Book addBook(Book book);
	Book updateBook(Book book);
	String deleteBook(long id);

	List<Book> findBooksByAuthor(String author);

	List<Book> findBooksByPublicationYear(int startYear, int endYear);
}
