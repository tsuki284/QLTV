package com.example.demo.Borrow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrows")
@CrossOrigin
public class BorrowController {

	@Autowired
	private BorrowRepository repo;

	@PostMapping
	public ResponseEntity<?> borrow(@RequestBody Borrow b) {
		Borrow existing = repo.findByBookId(b.getBookId());

		if (existing != null) {
			return ResponseEntity.badRequest().body("Sách đã được mượn");
		}

		return ResponseEntity.ok(repo.save(b));
	}

	@DeleteMapping("/{bookId}")
	public void returnBook(@PathVariable Long bookId) {
		Borrow b = repo.findByBookId(bookId);
		if (b != null)
			repo.delete(b);
	}

	@GetMapping
	public List<Borrow> getAll() {
		return repo.findAll();
	}
}