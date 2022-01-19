package com.bookshare.dataloader.data.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.bookshare.dataloader.data.model.Author;

@Repository
public interface AuthorRepository extends CassandraRepository<Author, String>{

}
