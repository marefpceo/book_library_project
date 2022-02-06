package com.bookshare.dataloader.data.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("book_cat_by_id")
public class UserBook implements Serializable{

	private static final long serialVersionUID = 4923777390012025751L;
	
	@PrimaryKey
	private BookUserComposite bookUserComposite;
	
	@Column("category_type")
	@CassandraType(type = Name.TEXT)
	private String categoryType;
	
	public UserBook() {
		
	}
	
	public UserBook(BookUserComposite bookUserComposite, String categoryType) {
		this.bookUserComposite = bookUserComposite;
		this.categoryType = categoryType;
	}

	public BookUserComposite getBookUserComposite() {
		return bookUserComposite;
	}

	public void setBookUserComposite(BookUserComposite bookUserComposite) {
		this.bookUserComposite = bookUserComposite;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	
}
