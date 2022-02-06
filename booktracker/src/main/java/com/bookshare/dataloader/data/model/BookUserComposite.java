package com.bookshare.dataloader.data.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
@PrimaryKeyClass
public class BookUserComposite implements Serializable {

	private static final long serialVersionUID = -163718141364859183L;
	@PrimaryKeyColumn(name = "book_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	@CassandraType(type = Name.TEXT)
	private String bookId;
	
	@PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = Name.TEXT)
	private String userId;

	public BookUserComposite(String bookId, String userId) {
		this.userId = userId;
		this.bookId = bookId;
	}
	
	public BookUserComposite() {
		
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
