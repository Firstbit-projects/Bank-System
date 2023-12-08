package com.bank.account.loan;

import com.sam.bank.Account;

public class LoanAccount extends Account{

//	String loanType;
	double principalAmount;
	static double intrestRate;
	double emi;
	int tenure; // in months
	int remainingEmi;
	double intrestAmount;
	double penalty;
//	LocalDate repaymentDate;

	public LoanAccount(String holderName, double loanAmount, int months)
	{
		super(holderName, -(calculateEMI(loanAmount, months)*months));
		principalAmount = loanAmount;
		this.penalty = 0;
		this.emi = calculateEMI(loanAmount, months);
		this.tenure = months;
		this.remainingEmi = this.tenure;
		this.intrestAmount = emi*months - loanAmount;
	}
	
	public double getPrincipalAmount() {
		return this.principalAmount;
	}
	
	public double getEmi() {
		return this.emi;
	}
	
	public int  getTenure() {
		return tenure;
	}

	public int getRemainingEmi() {
		return this.remainingEmi;
	}
	
	public double getIntrestAmount() {
		return this.intrestAmount;
	}
	
	public double getPenalty() {
		return this.penalty;
	}
	
	
	
	//business 
	@Override
	public void showTransactions() {
		// TODO Auto-generated method stub
		
	}
	public String showAccountType() {
		return "Loan Account";
	}
	
	public boolean payEmi() {
		if(this.remainingEmi>0) {
			this.deposit(this.emi);
			this.remainingEmi--;
			return true;
		}
		else {
			System.out.println("there are no pending EMI");
			return false;
		}
	}
	public boolean deposit(double amount) {
		if(amount>0) {
			this.setBalance(this.getBalance() + amount);
			return true;
		}
		else {
			System.out.println("EMI amount should be greater than 0");
			return false;
		}
	}
	
	public double TotalLoan() {
		
		return emi*tenure;
	}
	
	
	
	public static double calculateEMI(double principalAmount, int months) {
		double rate = LoanAccount.getInterestRate()/1200;
		double temp = Math.pow(1+rate, months);
		double emi = (principalAmount * rate * temp)/ (temp - 1);
		return emi;
	}
	
	public static double getInterestRate() {
		return intrestRate;
	}
	
	public static void setInterestRate(double interestRate) {
		LoanAccount.intrestRate = interestRate;
	}
	
	static
	{
		intrestRate=10.5;
	}
}
