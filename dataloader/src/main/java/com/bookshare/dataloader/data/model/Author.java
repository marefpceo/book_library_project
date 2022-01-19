package com.bookshare.dataloader.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("author_by_id")
public class Author {
	@Column("author_name")
	@CassandraType(type = Name.TEXT)
	private String name;
	
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@Id
	private String author_id;
	
	@Column("personal_name")
	@CassandraType(type = Name.TEXT)
	private String personalName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return author_id;
	}

	public void setId(String id) {
		this.author_id = id;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.author_id;
	}

}
