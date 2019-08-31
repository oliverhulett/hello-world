package com.example.hl.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	private AccountTransaction toAccount;
	@ManyToOne
	private AccountTransaction fromAccount;
	private Double amount;
	private Date date;
	private String description;

	public Payment(AccountTransaction toAccount, AccountTransaction fromAccount,
			Double amount, Date date, String description) {
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}
}