package com.genspark.SpringBootQueryAssignment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_books")
@NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
public class Book
{
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private String author;
	private double price;
	private int publicationYear;

	public Book(long id, String title, String author, double price, int publicationYear)
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publicationYear = publicationYear;
	}

	public Book() {}


	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getPublicationYear()
	{
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear)
	{
		this.publicationYear = publicationYear;
	}
}
