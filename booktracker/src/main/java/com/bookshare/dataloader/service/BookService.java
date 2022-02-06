package com.bookshare.dataloader.service;

import java.util.List;
import java.util.Optional;

import com.bookshare.dataloader.data.model.Book;
import com.bookshare.dataloader.data.model.BookRating;
import com.bookshare.dataloader.data.model.UserBook;

public interface BookService {

	Optional<Book> getBookById(String bookId);
	
	Optional<BookRating> findById(String bookId, Long userId);
	
	List<BookRating> findAllBookRatingsById(String bookId);
	
	List<UserBook> findAllBooksByUserId(String userId);

	BookRating saveRating(BookRating bookRating);

	UserBook saveUserBook(UserBook userBook);
}
