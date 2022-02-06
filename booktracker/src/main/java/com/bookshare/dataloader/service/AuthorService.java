package com.bookshare.dataloader.service;

import java.util.Optional;

import com.bookshare.dataloader.data.model.Author;

public interface AuthorService {
	
	Optional<Author> getAuthorById(String authorId);

}
