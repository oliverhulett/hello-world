package com.example.hl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.hl.domain.AccountTransactionRepository;
import com.example.hl.domain.Expense;
import com.example.hl.domain.ExpenseRepository;
import com.example.hl.domain.Income;
import com.example.hl.domain.IncomeRepository;
import com.example.hl.domain.Payment;
import com.example.hl.domain.PaymentRepository;

@SpringBootApplication
public class HibernateLiquibaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HibernateLiquibaseApplication.class, args);
	}

	@Autowired
	ConfigurableApplicationContext cac;

	@Autowired
	AccountTransactionRepository atr;
	@Autowired
	ExpenseRepository er;
	@Autowired
	IncomeRepository ir;
	@Autowired
	PaymentRepository pr;

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {

		ir.save(new Income(formatter.parse("01/01/2018"), "first income",
				1000.0));
		ir.save(new Income(formatter.parse("02/01/2018"), "second income",
				2000.0));
		ir.save(new Income(formatter.parse("03/01/2018"), "third income",
				2000.0));

		er.save(new Expense(formatter.parse("01/01/2018"), "first expense",
				500.0));
		er.save(new Expense(formatter.parse("02/01/2018"), "second expense",
				750.0));
		er.save(new Expense(formatter.parse("03/01/2018"), "third expense",
				750.0));

		// Delete incomes and expenses of 2nd January
		ir.deleteByDate(formatter.parse("02/01/2018"));
		er.deleteByDate(formatter.parse("02/01/2018"));

		// update 3rd January income(s) amount to 500
		Iterable<Income> incomes = ir
				.findAllByDate(formatter.parse("03/01/2018"));
		incomes.forEach(income -> {
			income.setAmount(500.0);
			ir.save(income);
		});

		// update 3rd January expense(s) amount to 250
		Iterable<Expense> expenses = er
				.findAllByDate(formatter.parse("03/01/2018"));
		expenses.forEach(expense -> {
			expense.setAmount(250.0);
			er.save(expense);
		});

		pr.save(new Payment(expenses.iterator().next(),
				incomes.iterator().next(), 20.0, Date.from(Instant.now()),
				"Services Rendered", "payment"));

		// calculate & print overall balance: incomes total minus expenses total
		Double balance = atr.findTotalByAccountType("income")
				- atr.findTotalByAccountType("expense");
		System.out.println(balance);

		cac.close();
	}
}
