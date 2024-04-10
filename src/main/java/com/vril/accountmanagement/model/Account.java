package com.vril.accountmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
	@SequenceGenerator(name = "account_generator", sequenceName = "account_generator", initialValue = 1000, allocationSize = 0)
	private Long id;

	@Column(name = "name", nullable = false)
	private String accountHolderName;

	@Column(name = "balance")
	private double accountBalance;

	private String status;

	@Version
	private Long version;

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be greater than zero");
		}
		accountBalance += amount;
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
		}
		if (amount > accountBalance) {
			throw new IllegalArgumentException("Insufficient balance");
		}
		accountBalance -= amount;
	}

}
