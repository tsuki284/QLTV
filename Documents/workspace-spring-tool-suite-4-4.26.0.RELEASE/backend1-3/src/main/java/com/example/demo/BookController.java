package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

	private final BookRepository repo;

	public BookController(BookRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public List<Book> getBooks() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam String keyword) {
		return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
	}

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return repo.save(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		Book oldBook = repo.findById(id).orElse(null);
		if (oldBook == null) {
			return null;
		}

		oldBook.setTitle(book.getTitle());
		oldBook.setAuthor(book.getAuthor());
		oldBook.setImage(book.getImage());
		return repo.save(oldBook);
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Long id) {
		repo.deleteById(id);
		return "Deleted successfully";
	}
}