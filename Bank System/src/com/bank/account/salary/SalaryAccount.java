package com.bank.account.salary;

import java.time.LocalDate;
import java.time.Period;

import com.bank.account.saving.SavingAccount;

public class SalaryAccount extends SavingAccount
{
	LocalDate lastTransactionDate;
	String status;
	
	public SalaryAccount(String holderName, double balance) 
	{
		super(holderName, balance);
		lastTransactionDate = LocalDate.now();
		status = "Active";
	}

	public LocalDate getLastTransactionDate() 
	{
		return lastTransactionDate;
	}

//	public void setLastTransactionDate(LocalDate lastTransactionDate)
//	{
//		this.lastTransactionDate = lastTransactionDate;
//	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public boolean deposit(double newBalance) {
		if(newBalance>0) {
			LocalDate currentDate = LocalDate.now();
			Period p = Period.between(currentDate, lastTransactionDate);
			if(p.getDays() > 60) {
				this.setStatus("Freeze");
			}
			this.lastTransactionDate = LocalDate.now();
			this.setBalance(this.getBalance() + newBalance);
			return true;				
		}
		else {
			System.out.println("Deposit amount should be less than 0");
			return false;
		}
	}
	
	public boolean withdraw(double wBalance) {
		if(this.getStatus()=="Active") {
			if(this.getBalance() - wBalance > 0) {
				this.setBalance(this.getBalance() - wBalance); // this.balance = this.balance - wBalance;
				return true;
			}
			else {
				System.out.println("Insuffiecient Balance !!!");
				return false;
			}
		}
		else {
			System.out.println("Sorry can't withdraw amount! \nYour Account is Freezed !!!");
			return false;
		}
	}
	
	public String showAccountType() {
		return "Salary Account";
	}
	
	public void accountDetails() {
		super.accountDetails();
		System.out.println("Last Transaction Date: "+this.lastTransactionDate);
		System.out.println("Account Status: "+this.status);
	}
}

