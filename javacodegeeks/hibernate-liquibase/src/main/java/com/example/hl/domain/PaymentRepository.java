package com.example.hl.domain;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
	@Transactional
	void deleteByDate(Date date);

	List<Payment> findAllByDate(Date date);
}