package com.bank.account.saving;

import com.sam.bank.Account;

public class SavingAccount extends Account
{
	static double minimumBalance;
	static double transactionLimit;
	
	public SavingAccount() 
	{}

	public SavingAccount(String holderName, double balance)
	{
		super(holderName, balance);
	}

	public static double getMinimumBalance() 
	{
		return minimumBalance;
	}

	public double getTransactionLimit()
	{
		return transactionLimit;
	}
	
	public boolean Withdraw(double wBalance) {
		if(super.getBalance() - wBalance >= minimumBalance) {
			this.setBalance(super.getBalance() - wBalance); // this.balance = this.balance - wBalance;
			return true;
		}
		else {
			System.out.println("Insuffiecient Balance !!!");
			return false;
		}
	}

	@Override
	public void showTransactions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String showAccountType() {
		return "Saving Account";
	}
	
	// static methods...
	public static void setMinimumBalance(double minBalance) 
	{
		SavingAccount.minimumBalance = minBalance;
	}
	public static void setTransactionLimit(double tranLimit) {
		SavingAccount.transactionLimit = tranLimit;
	}
	
	static 
	{
		minimumBalance = 1000.00;
		transactionLimit = 50000.00;
	}
}
