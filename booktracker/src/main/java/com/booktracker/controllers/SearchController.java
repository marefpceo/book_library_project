package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
