package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.booktracker.model.OpenLibrarySearchResult;

@RestController
@RequestMapping("api/search")
public class SearchController {

	private WebClient webClient;

	@GetMapping(value = "/query", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<OpenLibrarySearchResult> searchBooks(@RequestParam(name = "q") String query) {
		OpenLibrarySearchResult searchResults = this.webClient
				.get().uri("?q={query}" , query)
				.retrieve()
				.bodyToMono(OpenLibrarySearchResult.class)
				.block();
		searchResults.setSearchedBooks(searchResults.getSearchedBooks().stream().limit(10).collect(Collectors.toList()));
		
		searchResults.setSearchedBooks(searchResults.getSearchedBooks().stream().map(book -> {
			book.setCoverArtUrl(String.format("http://covers.openlibrary.org/b/id/%s-M.jpg", book.getCoverId()));
			return book;
		}).collect(Collectors.toList())

		);
		return ResponseEntity.ok(searchResults);
	}

	@PostConstruct
	private void buildWebClient() {
		this.webClient = WebClient.builder().baseUrl("http://openlibrary.org/search.json")
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs()
								.maxInMemorySize(16 * 1024 * 1024)).build())
				.build();
	}

}
