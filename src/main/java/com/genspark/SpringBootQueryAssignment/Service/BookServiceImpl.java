package com.genspark.SpringBootQueryAssignment.Service;


import com.genspark.SpringBootQueryAssignment.Dao.BookDao;
import com.genspark.SpringBootQueryAssignment.Entity.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService
{
	@Autowired
	private BookDao bookDao;


	@Override
	public List<Book> getAllBooks()
	{
		return this.bookDao.findAll();
	}

	@Override
	public Book getBookById(long id)
	{
		Optional <Book> b = this.bookDao.findById(id);
		Book book = null;
		if(b.isPresent()) {
			book = b.get();
		} else {
			throw new RuntimeException("Book not found with id : " + id);
		}
		return book;
	}

	@Override
	public Book addBook(Book book)
	{
		return this.bookDao.save(book);
	}

	@Override
	public Book updateBook(Book book)
	{
		return this.bookDao.save(book);
	}

	@Override
	public String deleteBook(long id)
	{
		this.bookDao.deleteById(id);
		return "Deleted book with id : " + id;
	}

	@Override
	public List<Book> findBooksByAuthor(String author)
	{
		return bookDao.findByAuthor(author);
	}

	@Override
	public List<Book> findBooksByPublicationYear(int startYear, int endYear)
	{
		TypedQuery<Book> query = entityManager.createQuery(
			"SELECT b FROM Book b WHERE b.publicationYear BETWEEN :startYear AND :endYear",
			Book.class);

		query.setParameter("startYear", startYear);
		query.setParameter("endYear", endYear);

		return query.getResultList();
	}

	@PersistenceContext
	private EntityManager entityManager;
}
