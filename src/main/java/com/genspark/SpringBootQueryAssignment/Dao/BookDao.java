package com.genspark.SpringBootQueryAssignment.Dao;

import com.genspark.SpringBootQueryAssignment.Entity.Book;
import jakarta.persistence.Entity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, Long>
{
	List<Book> findByAuthor(String author);
}
