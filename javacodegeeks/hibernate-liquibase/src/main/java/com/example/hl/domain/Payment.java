package com.example.hl.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne
	private Expense fromAccount;
	@NotNull
	@ManyToOne
	private Income toAccount;
	@NotNull
	private Double amount;
	@NotNull
	private Date date;
	@NotNull
	private String item;
	@Nullable
	private String description;

	public Payment(Expense fromAccount, Income toAccount, Double amount,
			Date date, String item, String description) {
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.date = date;
		this.item = item;
		this.description = description;
	}
}