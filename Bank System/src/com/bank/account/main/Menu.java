package com.bank.account.main;

import java.util.Scanner;

import com.bank.account.current.CurrentAccount;
import com.bank.account.loan.LoanAccount;
import com.bank.account.salary.SalaryAccount;
import com.bank.account.saving.SavingAccount;
import com.sam.bank.Account;
import com.sam.bank.Bank;
import com.sam.bank.Transaction;

public class Menu {

	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	////////////////////////////////////
	public void adminMenu(Bank bk) {
		
		Scanner sc = new Scanner(System.in);
		int choice2;
		do {
			System.out.println("\t1.Account LifeCycle");
			System.out.println("\t2.Counter Activities");
			System.out.println("\t3.Daily Report of Transactions");
			System.out.println("\t10.Back To previus Menu");
			choice2 = sc.nextInt();
			
			switch(choice2) {
				
				case 1:
				{
					bk.displayAccLifeCycle();
					break;
				}
				case 2:
				{
					bk.displayCounterActivities();
					break;
				}
				case 3:
				{
					bk.transactionReport();
					break;
				}
				case 10:
				{
					choice2 = 0;
					break;
				}
				default:{
					System.out.println("Invalid Entry!");
				}
			}
		}while(choice2!=0);
		
	}

	public void openAccountInfo(){
		System.out.println("Choose type of Account you want to open:-");
		System.out.println("\t\t1.Saving");
		System.out.println("\t\t2.Current");
		System.out.println("\t\t3.Salary");
		System.out.println("\t\t4.Loan");	
	}
	
	public double openSaving() {
		Scanner sc = new Scanner(System.in);
		boolean flag =true;
		double balance=500;
		while(flag) {								
			System.out.println("Enter initial amount to store\n(amount must be greater than "+SavingAccount.getMinimumBalance()+"): ");
			balance = sc.nextDouble();
			if(balance < SavingAccount.getMinimumBalance()) {
				System.out.println("Please Enter amount more than "+SavingAccount.getMinimumBalance());
			}
			else {
				flag=false;
			}
		}
		sc.close();
		return balance;
	}
	
	public double openCurrent() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		double balance=0;
		while(flag) {	
			System.out.println("Enter initial amount to store: ");
			balance = sc.nextDouble();
			if(balance < 0) {
				System.out.println("Initial balance cannot be less than zero");
			}
			else {				
				flag = false;
			}
		}
//		sc.close();
		return balance;
	}
	public void manageAccount() {
		System.out.println("\t\t1.Show Account Details");
		System.out.println("\t\t2.Deposit money");
		System.out.println("\t\t3.Withdraw money");
		System.out.println("\t\t4.Check Balance");	
		System.out.println("\t\t10.Back to Main menu");
		
	}
	
	public void manageLoanAccount() {
		System.out.println("\t\t1.Show Account Details");
		System.out.println("\t\t2.Pay EMI");
		System.out.println("\t\t3.Show Loan");
		System.out.println("\t\t4.Show Interest rate");
		System.out.println("\t\t5.Reamaining Loan");
		System.out.println("\t\t6.Reamining EMI's");
		System.out.println("\t\t7.Total Interest amount");
		System.out.println("\t\t8.Total Amount(to be paid)");
		System.out.println("\t\t10.Back to Main menu");
		
	}
	
	public boolean accountLogin(long accNumber, Account[] acc) {
		int i=-1;
		while(acc[++i]!=null) {
			if(accNumber==acc[i].getAccountNumber()) {
				return true;
			}
		}
		return false;
	}
	
	public void accountActivity(long accNumber, Bank bk) {
		Account acc[] = bk.getAcc();
		int i=-1;
		boolean flag = false;
		while(acc[++i]!=null) {
			if(accNumber==acc[i].getAccountNumber()) {
				flag=true;
				break;
			}
		}
		if(flag==false) {
			System.out.println("Account not found(something wrong)!!");
			return;
		}
		
		if(acc[i] instanceof SavingAccount || acc[i] instanceof CurrentAccount || acc[i] instanceof SalaryAccount) {
			otherAccountsActivity(bk, acc[i]);
		}
		else {
			loanAccountActivity(bk, acc[i]);
		}
	}
	
	public void loanAccountActivity(Bank bk, Account a) 
	{
		//loan account
		if(!(a instanceof LoanAccount)) {
			System.out.println("Something went wrong in loan account!!");
			return;
		}
		LoanAccount la = (LoanAccount)a;
		Scanner sc = new Scanner(System.in);
		int choice3=0;
		do {
			manageLoanAccount();
			choice3 = sc.nextInt();
			
			switch(choice3) 
			{
				case 1:
				{
					la.accountDetails();
					break;
				}
				case 2:
				{
					System.out.println("1.Pay Regular EMI (of "+la.getEmi()+" Rs.)");
					System.out.println("2.Pay custom amount");
					int ch = sc.nextInt();
					if(ch==1) {
						if(la.payEmi()) {
							la.setAccTransaction(new Transaction("EMI", la.getEmi()));
							bk.setBankTransaction(new Transaction("EMI", la.getEmi()));
							System.out.println("EMI paid...");
						}
					}
					else {
						System.out.println("Enter amount: ");
						double amount = sc.nextDouble();
						if(la.deposit(amount)) {
							la.setAccTransaction(new Transaction("Custom EMI", amount));
							bk.setBankTransaction(new Transaction("Custom EMI", amount));
							System.out.println("Custom EMI paid...");
						}
					}
					break;
				}
				case 3:
				{
					System.out.println("Your Loan Amount is: "+la.getPrincipalAmount());
					break;
				}
				case 4:
				{
					System.out.println("Interest Rate is: "+LoanAccount.getInterestRate()+"%");
					break;
				}
				case 5:
				{
					System.out.println("Remaining Loan is: "+la.getBalance());
					
					break;
				}
				case 6:
				{
					System.out.println("Remaining EMI's are: "+la.getRemainingEmi());
					break;
				}
				case 7:
				{
					System.out.println("Total Intrest Amount is: "+la.getIntrestAmount());
					break;
				}
				case 8:
				{
					System.out.println("Total Amount: "+la.TotalLoan());
					break;
				}
				case 10:
				{
					choice3=0;
					break;
				}
				default:
				{
					System.out.println("Invalid entry!");
				}
			}
		}while(choice3!=0);
	}
	
	public void otherAccountsActivity(Bank bk, Account a) {
		Scanner sc = new Scanner(System.in);
		int choice2;
		do {								
			manageAccount();
			
			choice2 = sc.nextInt();
			switch(choice2) 
			{
				case 1:
				{
					a.accountDetails();
					break;
				}
				case 2:
				{
					System.out.println("Enter amount to deposit: ");
					boolean flag1 = true;
					while(flag1) 
					{
						double amount = sc.nextDouble();
						if(amount < 0) {
							System.out.println("Amount must be greater than zero !\nRe enter the amount: ");
						}
						else {
							a.deposit(amount);
							a.setAccTransaction(new Transaction("Deposit", amount));
							bk.setBankTransaction(new Transaction("Deposit", amount));
							System.out.println("Amount deposited successfuly...");
							flag1=false;
						}
					}
					break;
				}
				case 3:
				{
					if(a instanceof SalaryAccount) {
						SalaryAccount sa = (SalaryAccount)a;
						if(sa.getStatus().equals("Freeze")) {
							System.out.println("You Can't withdraw money!");
							System.out.println("Your account is freezed!");
							break;
						}
							
					}
					System.out.println("Enter amount to withdraw: ");
					boolean flag1 = true;
					while(flag1) {
						double amount = sc.nextDouble();
						if(amount < 0) {
							System.out.println("Amount must be greater than zero !\nRe enter the amount: ");
						}
						else {
							if(a.Withdraw(amount)) {
								System.out.println("Amount withdrawn successfuly...");								
							}
							a.setAccTransaction(new Transaction("Withdraw", amount));
							bk.setBankTransaction(new Transaction("Withdraw", amount));
							flag1=false;
						}
					}
					break;
				}
				case 4:
				{
					System.out.println("Remaining Balance: "+a.checkBalance());	
					break;
				}
				case 10:
				{
					choice2 = 0;
					break;
				}
				default:
				{
					System.out.println("Invalid entry!");
					break; 
				}
			}
		}while(choice2!=0);
		return;
	}
}













