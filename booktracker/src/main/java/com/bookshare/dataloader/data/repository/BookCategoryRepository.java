package com.bookshare.dataloader.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.bookshare.dataloader.data.model.BookUserComposite;
import com.bookshare.dataloader.data.model.UserBook;

public interface BookCategoryRepository extends CassandraRepository<UserBook, BookUserComposite>{

	@Query("select * from book_cat_by_id where user_id = ?0 ALLOW FILTERING")
	List<UserBook> findAllById(String userId);
	
	
}
