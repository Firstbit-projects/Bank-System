package com.bank.account.current;

import com.sam.bank.Account;

public class CurrentAccount extends Account{
	
	static double overDraftLimit;
	
	public CurrentAccount(String holderName, double balance) {
		super(holderName, balance);
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	//business methods

	boolean withdraw(double wBalance) {
		if(this.getBalance() - wBalance > overDraftLimit) {
			this.setBalance(this.getBalance() - wBalance); 
			return true;
		}
	else {
		return false;
		}
	}

	@Override
	public void showTransactions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String showAccountType() {
		// TODO Auto-generated method stub
		return "Current Account";
	}
	
	
	
	public static void setOverDraftLimit(double overDraftLimit) {
		CurrentAccount.overDraftLimit = overDraftLimit;
	}
	
	static {
		overDraftLimit = -10000;
	}
}

