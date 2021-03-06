package com.example.hl.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("expense")
@Getter
@Setter
@NoArgsConstructor
public class Expense extends AccountTransaction {

	public Expense(Date date, String description, Double amount) {
		super(amount, date, description);
	}
}