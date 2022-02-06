package com.bookshare.dataloader.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshare.dataloader.data.model.BookRating;
import com.bookshare.dataloader.data.model.BookUserComposite;

@Repository
public interface BookRatingRepository extends CassandraRepository<BookRating, BookUserComposite>{

	@Query("select * from book_rating_by_id where book_id = ?0 AND user_id = ?1")
	Optional<BookRating> findById(String bookId, Long userId);
	
	@Query("select * from book_rating_by_id where book_id = ?0")
	List<BookRating> findAllByBookId(String bookId);
	
}
