package com.bookshare.dataloader.data.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshare.dataloader.data.model.Book;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

	@Query("select * from book_by_id where book_id = ?0")
	Optional<Book> findById(String id);
}
