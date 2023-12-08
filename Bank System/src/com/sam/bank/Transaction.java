package com.sam.bank;

import java.time.LocalDate;

public class Transaction {
	String transactionType;
	double amount;
	LocalDate transDate;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(String transactionType, double amount) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
		transDate = LocalDate.now();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDate getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}
	
	public void display() {
		System.out.println(this.transactionType + "   " + this.amount + "   " + this.transDate);
	}
}
