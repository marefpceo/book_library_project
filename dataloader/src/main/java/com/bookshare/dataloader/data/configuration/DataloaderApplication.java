package com.bookshare.dataloader.data.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.bookshare.dataloader.data.model.Author;
import com.bookshare.dataloader.data.model.Book;
import com.bookshare.dataloader.data.repository.AuthorRepository;
import com.bookshare.dataloader.data.repository.BookRepository;

@SpringBootApplication
@EnableCassandraRepositories("com.bookshare.dataloader.data.repository")
@EnableConfigurationProperties({ DataAstraPropertiesConfiguration.class, SpringDataPropertiesConfiguration.class })
@PropertySource("classpath:application.yml")
public class DataloaderApplication {

	@Value("${openlibrary.author.data}")
	private String authorFilePath;

	@Value("${openlibrary.book.data}")
	private String booksDataFilePath;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(DataloaderApplication.class, args);
	}

	private void loadAuthorsToCassandra() {
		Path authorTestDataPath = new File(authorFilePath).toPath();
		try (Stream<String> lines = Files.lines(authorTestDataPath)) {
			lines.forEach(string -> {
				String jsonAuthorData = string.substring(string.indexOf('{'));
				// System.out.println(jsonAuthorData);

				try {
					JSONObject jsonObject = new JSONObject(jsonAuthorData);
					Author author = new Author();
					author.setName(jsonObject.getString("name"));
					author.setId(
							jsonObject.getString("key").substring(jsonObject.getString("key").lastIndexOf('/') + 1));
					authorRepository.save(author);
				} catch (JSONException exp) {
					exp.printStackTrace();
				}
			});
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private void loadBooksToCassandra() {
		Path bookTestDataPath = new File(booksDataFilePath).toPath();

		try (Stream<String> lines = Files.lines(bookTestDataPath)) {
			lines.forEach(string -> {
				String jsonAuthorData = string.substring(string.indexOf('{'));

				try {
					JSONObject jsonObject = new JSONObject(jsonAuthorData);
					// create and prepare book object
					Book book = new Book();
					book.setTitle(jsonObject.optString("title").toString());
					JSONObject descriptionObject = jsonObject.optJSONObject("description");
					book.setDescription(descriptionObject != null ? descriptionObject.optString("description") : null);
					book.setId(jsonObject.getString("key").replace("/works/", ""));
					int authorArraySize = jsonObject.getJSONArray("authors").length();
					if (authorArraySize > 0) {
						List<String> authorIds = new ArrayList<>();
						for (int i = 0; i < authorArraySize; i++) {
							JSONObject authorObject = (JSONObject) jsonObject.getJSONArray("authors").get(i);
							JSONObject author = authorObject.optJSONObject("author");
							authorIds.add(author.getString("key").replace("/authors/", ""));
						}
						book.setAuthorId(authorIds);
						List<String> authorNames = new ArrayList<>();
						for (String authorId : authorIds) {
							Optional<Author> mayBeAuthor = authorRepository.findById(authorId);
							authorNames.add(mayBeAuthor.isPresent() ? mayBeAuthor.get().getName() : "");
						}
						book.setAuthorName(authorNames);
					}
					book.setEdition("revision");
					JSONArray coverIds = jsonObject.optJSONArray("covers");
					if (coverIds != null) {
						List<String> coverArtIds = new ArrayList<>();
						for (int i = 0; i < coverIds.length(); i++) {
							coverArtIds.add(String.valueOf(coverIds.get(i)));
						}
						book.setCoverArtIds(coverArtIds);
					}
					JSONObject publication = jsonObject.optJSONObject("created");
					if(publication!=null) {
						book.setPublishedDate(LocalDate.parse(publication.getString("value").substring(0, publication.getString("value").indexOf('T'))));
					}
					bookRepository.save(book);
				} catch (JSONException exp) {
					exp.printStackTrace();
				}

			});
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	@PostConstruct
	public void start() {
		System.out.println("Author test data file path is: " + authorFilePath);
		System.out.println("Loading Authors in the database..");
		// loadAuthorsToCassandra();

		System.out.println("Books test data file path is: " + booksDataFilePath);
		System.out.println("Loading books in the database..");
		loadBooksToCassandra();

	}

}
