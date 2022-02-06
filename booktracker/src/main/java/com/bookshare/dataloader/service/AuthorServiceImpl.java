package com.bookshare.dataloader.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookshare.dataloader.data.model.Author;
import com.bookshare.dataloader.data.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	private final AuthorRepository authorRepository;
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Optional<Author> getAuthorById(String authorId) {
		return authorRepository.findById(authorId);
	}

	
}
