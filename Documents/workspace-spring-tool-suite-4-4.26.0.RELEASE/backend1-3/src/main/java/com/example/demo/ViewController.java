package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	private final BookRepository repo;

	public ViewController(BookRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Book> books = repo.findAll();
		model.addAttribute("books", books);
		model.addAttribute("totalBooks", repo.count());
		return "dashboard";
	}
}