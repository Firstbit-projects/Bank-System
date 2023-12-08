package com.sam.bank;

import java.time.LocalDate;

public abstract class Account {
	
	final long accountNumber; 
	String holderName;
	final LocalDate accountOpenDate; 
	private double balance;
	private Transaction AccTransaction[];
	private int noOfTransaction;
	static int accNoChange;
	
	public Account() {
		this.accountNumber = 0;
		this.accountOpenDate = null;
		// TODO Auto-generated constructor stub
	}

	public Account(String holderName, double balance) {
		this.accountNumber = Account.generateAccountNumber();
		this.holderName = holderName;
		this.accountOpenDate = LocalDate.now();
		this.balance = balance;
		this.AccTransaction = new Transaction[20];
		this.noOfTransaction=0;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public LocalDate getAccountOpenDate() {
		return accountOpenDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Transaction[] getAccTransaction() {
		return AccTransaction;
	}

	public void setAccTransaction(Transaction accTransaction) {
		int index = this.noOfTransaction-1;
		this.AccTransaction[++index] = accTransaction;
		this.noOfTransaction++;
	}

	public int getNoOfTransaction() {
		return noOfTransaction;
	}

	public void setNoOfTransaction(int noOfTransaction) {
		this.noOfTransaction = noOfTransaction;
	}

	// Business methods...
	public boolean deposit(double newBalance) {
		if(newBalance>0) {			
			this.balance = this.balance + newBalance;
			return true;
		}
		else {
			System.out.println("Deposit amount should be less than 0");
			return false;
		}
	}
	public boolean Withdraw(double wBalance) {
		if(this.balance - wBalance >= 0) {
			this.balance -= wBalance; // this.balance = this.balance - wBalance;
			return true;
		}
		else {
			System.out.println("Insuffiecient Balance !!!");
			return false;
		}
	}
	
	public double checkBalance() {
		return this.balance;
	}
	
	public void accountDetails() {
		System.out.println("Your Account Details: ");
		System.out.println("Holder Name: "+ holderName);
		System.out.println("Acc No: "+ accountNumber);
		System.out.println("Acc Open date: "+ accountOpenDate);
		System.out.println("Balance: "+ this.balance);
	}
	public abstract void showTransactions();
	public abstract String showAccountType();
	
	
	
	
	
	
	
	//static methods
	
	// this method is used to generate Account number
	private static long generateAccountNumber() {
		long accNo = 60244654542L;
		accNo = accNo +  accNoChange++;
		return accNo;
	}
	
	static
	{
		accNoChange=1;
	}
	
}
