package com.bookshare.dataloader.data.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshare.dataloader.data.model.Author;

@Repository
public interface AuthorRepository extends CassandraRepository<Author, String>{
	
	@Query("select * from author_by_id where author_id = ?0")
	Optional<Author> findById(String id);
}
