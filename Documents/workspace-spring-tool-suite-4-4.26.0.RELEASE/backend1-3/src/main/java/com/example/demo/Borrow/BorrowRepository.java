package com.example.demo.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
	Borrow findByBookId(Long bookId);
}