package com.sam.bank;

import java.util.Scanner;

import com.bank.account.current.CurrentAccount;
import com.bank.account.loan.LoanAccount;
import com.bank.account.main.Menu;
import com.bank.account.salary.SalaryAccount;
import com.bank.account.saving.SavingAccount;

public class Bank {
	private static String user, pass;
	int noOfTransaction;
	private Transaction[] bankTransaction;
	int noOfAccounts;
	public Account acc[];
	OpenClose[] openClose;
	int opCount;
	
	public Bank() {
		// TODO Auto-generated constructor stub
	}

	

	public Bank(int accSize) {
		this.noOfTransaction = 0;
		this.bankTransaction = new Transaction[20];
		this.noOfAccounts = 0;
		this.acc = new Account[accSize];
		this.openClose = new OpenClose[20];
		opCount=0;
	}

	public void setOpenClose(OpenClose oc) {
		int index = this.opCount-1;
		this.openClose[++index] = oc;
		opCount++;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Bank.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		Bank.pass = pass;
	}

	public Transaction[] getBankTransaction() {
		return bankTransaction;
	}

	public void setBankTransaction(Transaction bankTransaction) {
		int index = this.noOfTransaction-1;
		this.bankTransaction[++index] = bankTransaction;
		this.noOfTransaction++;
	}
	
	
	
	public Account[] getAcc() {
		return acc;
	}



	public void setAcc(Account[] acc) {
		this.acc = acc;
	}

	public void openAccount() {
		Menu menu = new Menu();
		menu.openAccountInfo();
		Scanner sc = new Scanner(System.in);
		int index = this.noOfAccounts-1;
		int choice1 = sc.nextInt();
		System.out.println("Enter your name: ");
		sc.nextLine();
		String name = sc.nextLine();
		switch(choice1) 
		{
			case 1:
			{
				double balance = menu.openSaving();
				acc[++index] = new SavingAccount(name, balance);
				this.noOfAccounts++;
				this.setOpenClose(new OpenClose("Saving", true));
				System.out.println("Account created Successfully...");
				System.out.println("Account Details: ");
				acc[index].accountDetails();
				break;
			}
			case 2:
			{
				double balance = menu.openCurrent();
				acc[++index] = new CurrentAccount(name, balance);
				this.noOfAccounts++;
				this.setOpenClose(new OpenClose("Current", true));
				System.out.println("Account created Successfully...");
				System.out.println("Account Details: ");
				acc[index].accountDetails();
				break;
			}
			case 3:
			{
				acc[++index] = new SalaryAccount(name, 0.0);
				this.noOfAccounts++;
				this.setOpenClose(new OpenClose("Salary", true));
				System.out.println("Account created Successfully...");
				System.out.println("Account Details: ");
				acc[index].accountDetails();
				break;
			}
			case 4:
			{
				System.out.println("Enter Loan Amount: ");
				double loanAmount = sc.nextDouble();
				System.out.println("Enter loan tenure(in months): ");
				int months = sc.nextInt();
				acc[++index] = new LoanAccount(name, loanAmount, months);
				this.noOfAccounts++;
				this.setOpenClose(new OpenClose("Loan", true));
				System.out.println("Account created Successfully...");
				System.out.println("Account Details: ");
				acc[index].accountDetails();
				break;
			}
			
			default:
			{
				System.out.println("Invalid choice!");
				break;
			}
		}
	}

	public void displayAccLifeCycle() {
		int i= -1;
		System.out.println("Account Type   |    Open//Close    |   Date");
		while(this.openClose[++i]!=null) {
			this.openClose[i].display();
		}
	}
	
	public void displayCounterActivities() {
		int i= -1;
		System.out.println("Transaction       |      Amount        |      Date");
		while(this.bankTransaction[++i] != null) {
			this.bankTransaction[i].display();
		}
	}
	
	public void transactionReport() {
//		int currentCount, savingCount, salaryCount, loanCount;
//		currentCount=savingCount=salaryCount=loanCount=0;
		int withdrawCount=0, depositCount=0, emiCount=0;
		int i = -1;
		while(this.bankTransaction[++i] != null) {
			if(this.bankTransaction[i].getTransactionType().equals("Deposit"))
				depositCount++;
			else if(this.bankTransaction[i].getTransactionType().equals("Withdraw"))
				withdrawCount++;
			else
				emiCount++;
		}
		System.out.println();
		System.out.println("Transaction Type     |     No. of Transactions");
		System.out.println("Deposits           |         "+depositCount);
		System.out.println("Withdraws           |         "+withdrawCount);
		System.out.println("EMI's           |         "+emiCount);
		System.out.println();
	}
	
	public boolean closeAccount(long accNumber) {
		int i= -1;
		boolean flag=false;
		while(this.acc[++i] != null) {
			if(acc[i].getAccountNumber() == accNumber) {
				flag=true;
				break;
			}
		}
		if(flag) {
			int j=i;
			if(acc[i] instanceof SavingAccount) {
				this.openClose[this.opCount] = new OpenClose("Saving", false);				
			}
			else if(acc[i] instanceof CurrentAccount) {
				this.openClose[this.opCount] = new OpenClose("Current", false);				
			}
			else if(acc[i] instanceof SalaryAccount) {
				this.openClose[this.opCount] = new OpenClose("Salary", false);				
			}
			else if(acc[i] instanceof LoanAccount) {
				this.openClose[this.opCount] = new OpenClose("Loan", false);				
			}
			
			while(acc[i] != null) {
				acc[i] = acc[i+1];
				i++;
			}
			this.noOfAccounts--;
			System.out.println("Account Closed Successfully");
			return true;
		}
		else {
			System.out.println("Invalid Account Number!");
			return false;
		}
	}
	public static boolean checkCredintials(String user, String pass) {
		if(Bank.user.equals(user) && Bank.pass.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static {
		user = "Admin@8899";
		pass = "Pass@1432";
	}
}
