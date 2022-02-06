package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookshare.dataloader.data.model.Book;
import com.bookshare.dataloader.data.model.BookRating;
import com.bookshare.dataloader.service.BookService;
import com.booktracker.model.BookDetails;
import com.booktracker.model.BookWithReviews;

@RestController
@RequestMapping("api/book")
public class BookController {

	private BookService bookService;

	private WebClient webClient;

	private static final Function<Book, BookDetails> BOOK_TO_BOOKDETAILS_CONVERTER = (book) -> {
		BookDetails bookDetails = new BookDetails();
		bookDetails.setAuthorID(book.getAuthorId());
		bookDetails.setAuthorName(book.getAuthorName());
		bookDetails.setBookDescription(book.getDescription());
		bookDetails.setBookId(book.getId());
		bookDetails.setCoverId(book.getCoverArtIds().get(0));
		return bookDetails;
	};

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = Objects.requireNonNull(bookService, "authorService");
	}

	@GetMapping(value = "/{bookId}/search", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<BookWithReviews> getBook(@PathVariable String bookId) {
		BookWithReviews bookWithReviews = new BookWithReviews(new Book(), new ArrayList<BookRating>());
		Optional<Book> mayBeBook = bookService.getBookById(bookId);

		if (mayBeBook.isPresent()) {
			bookWithReviews.setBookDetails(mayBeBook.get());
			List<BookRating> ratings = bookService.findAllBookRatingsById(bookId);
			if (Optional.of(ratings).isPresent() && ratings.size() > 0) {
				bookWithReviews.setRatings(ratings);
			}
			if (Optional.of(mayBeBook.get().getCoverArtIds()).isPresent()
					&& mayBeBook.get().getCoverArtIds().size() > 0) {
				mayBeBook.get()
						.setCoverArtIds(mayBeBook.get().getCoverArtIds().stream()
								.map(coverId -> String.format("http://covers.openlibrary.org/b/id/%s-M.jpg", coverId))
								.collect(Collectors.toList()));
			}
		}

		/*
		 * BookDetails searchResult = this.webClient .get().uri("/{bookId}.json" ,
		 * bookId) .retrieve() .bodyToMono(BookDetails.class) .block();
		 */

		return ResponseEntity.ok(bookWithReviews);
	}

	@PostConstruct
	private void buildWebClient() {
		this.webClient = WebClient.builder().baseUrl("https://openlibrary.org/works/")
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
				.build();
	}

}
