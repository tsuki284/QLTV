package com.example.demo.User;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest req) {
		Optional<User> user = userRepository.findByUsername(req.getUsername());

		if (user.isPresent() && user.get().getPassword().equals(req.getPassword())) {
			return ResponseEntity.ok(Map.of("token", "logged_in"));
		}

		return ResponseEntity.status(401).body("Sai tài khoản hoặc mật khẩu");
	}
}