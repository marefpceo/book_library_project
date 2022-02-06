package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshare.dataloader.data.model.Author;
import com.bookshare.dataloader.service.AuthorService;

@RestController
@RequestMapping("api/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping(value = "/{authorId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthor(@PathVariable String authorId) {
		Optional<Author> mayBeauthor = authorService.getAuthorById(authorId);
		if (mayBeauthor.isEmpty()) {
			mayBeauthor = Optional.of(new Author());
		}
		return ResponseEntity.ok(mayBeauthor.get());
	}

}
