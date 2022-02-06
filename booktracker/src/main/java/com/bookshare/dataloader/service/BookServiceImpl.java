package com.bookshare.dataloader.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookshare.dataloader.data.model.Book;
import com.bookshare.dataloader.data.model.BookRating;
import com.bookshare.dataloader.data.model.UserBook;
import com.bookshare.dataloader.data.repository.BookCategoryRepository;
import com.bookshare.dataloader.data.repository.BookRatingRepository;
import com.bookshare.dataloader.data.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	
	private final BookRatingRepository bookRatingRepository;
	
	private final BookCategoryRepository BookCategoryRepository;

	public BookServiceImpl(BookRepository bookRepository, BookRatingRepository bookRatingRepository, BookCategoryRepository BookCategoryRepository) {
		this.bookRepository = Objects.requireNonNull(bookRepository, "Bookrepository");
		this.bookRatingRepository = Objects.requireNonNull(bookRatingRepository,"bookRatingRepository");
		this.BookCategoryRepository = Objects.requireNonNull(BookCategoryRepository, "BookCategoryRepository");
	}

	@Override
	public Optional<Book> getBookById(String bookId) {
		Optional<Book> mightBeBook = bookRepository.findById(bookId);
		return mightBeBook;
	}

	@Override
	public Optional<BookRating> findById(String bookId, Long userId) {
		return bookRatingRepository.findById(bookId, userId);
	}

	@Override
	public List<BookRating> findAllBookRatingsById(String bookId) {
		return bookRatingRepository.findAllByBookId(bookId);
	}

	@Override
	public List<UserBook> findAllBooksByUserId(String userId) {
		return BookCategoryRepository.findAllById(userId);
	}	
	
	@Override
	public BookRating saveRating(BookRating bookRating) {
		return bookRatingRepository.insert(bookRating);
	}
	@Override
	public UserBook saveUserBook(UserBook userBook) {
		return BookCategoryRepository.insert(userBook);
	}

}
