package com.bookshare.dataloader.data.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("book_by_id")
public class Book {

	@Column("book_title")
	@CassandraType(type = Name.TEXT)
	private String title;

	@PrimaryKeyColumn(name = "book_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String id;

	@Column("book_description")
	@CassandraType(type = Name.TEXT)
	private String description;

	@Column("date_publish")
	@CassandraType(type = Name.DATE)
	private LocalDate publishedDate;

	@Column("cover_art_ids")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> coverArtIds;

	@Column("author_ids")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> authorId;

	@Column("author_names")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> authorName;

	@Column("book_edition")
	@CassandraType(type = Name.TEXT)
	private String edition;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<String> getCoverArtIds() {
		return coverArtIds;
	}

	public void setCoverArtIds(List<String> coverArtIds) {
		this.coverArtIds = coverArtIds;
	}

	public List<String> getAuthorId() {
		return authorId;
	}

	public void setAuthorId(List<String> authorId) {
		this.authorId = authorId;
	}

	public List<String> getAuthorName() {
		return authorName;
	}

	public void setAuthorName(List<String> authorName) {
		this.authorName = authorName;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return this.title + "<-->" + this.id;
	}

}
