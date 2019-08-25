package com.example.hl.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	@Transactional
	void deleteByDate(Date date);

	List<Expense> findAllByDate(Date date);
}