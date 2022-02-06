package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshare.dataloader.data.model.BookRating;
import com.bookshare.dataloader.data.model.BookUserComposite;
import com.bookshare.dataloader.data.model.UserBook;
import com.bookshare.dataloader.service.BookService;
import com.booktracker.model.UserBookRating;
import com.booktracker.model.UserDashboard;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private static final String READ_BOOK_CATEGORY = "R";
	private static final String CURRENTLY_READING_BOOK_CATEGORY = "C";
	private static final String WISH_LIST = "W";

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDashboard> getUserDashboard(@PathVariable String userId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<UserBook> completeList = bookService.findAllBooksByUserId(userId);
		List<UserBook> readBooks = completeList.stream()
				.filter(book -> book.getCategoryType().equalsIgnoreCase(READ_BOOK_CATEGORY))
				.collect(Collectors.toList());
		List<UserBook> currentlyReading = completeList.stream()
				.filter(book -> book.getCategoryType().equalsIgnoreCase(CURRENTLY_READING_BOOK_CATEGORY))
				.collect(Collectors.toList());
		List<UserBook> wishList = completeList.stream()
				.filter(book -> book.getCategoryType().equalsIgnoreCase(WISH_LIST))
				.collect(Collectors.toList());
		
		UserDashboard userDashboard = new UserDashboard(userName, readBooks, currentlyReading, wishList);

		return ResponseEntity.ok(userDashboard);
	}
	
	@PostMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
	public String saveBookRating(@RequestBody UserBookRating userBookRating, @PathVariable String userId) {
		
		BookUserComposite bookUserComposite = new BookUserComposite(userBookRating.getBookId(), userId);
		BookRating bookRating = new BookRating(bookUserComposite, userBookRating.getComment(), userBookRating.getRating());
		bookService.saveRating(bookRating);
		return "SUCCESS";
	}
	
	@PostMapping(value = "/{userId}/{bookId}/{typeCode}", produces = APPLICATION_JSON_VALUE)
	public String saveBookCategory(@PathVariable String userId, @PathVariable String bookId, @PathVariable String typeCode) {
		BookUserComposite bookUserComposite = new BookUserComposite(bookId, userId);
		UserBook userBook = new UserBook(bookUserComposite, typeCode);
		bookService.saveUserBook(userBook);
		return "SUCCESS";
	}

}
