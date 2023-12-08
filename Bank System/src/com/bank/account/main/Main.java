package com.bank.account.main;

import java.util.Scanner;

import com.sam.bank.Bank;

public class Main{
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bank bk = new Bank(10);
		Menu menu = new Menu();
		
		int choice1;
		do {
			System.out.println("----------------------------------");
			System.out.println("\t\t1.Open Account");
			System.out.println("\t\t2.Login");
			System.out.println("\t\t3.Admin");
			System.out.println("\t\t4.Close Account");
			System.out.println("\t\t5.Exit");
			
			choice1 = sc.nextInt();
			
			switch(choice1) 
			{
				case 1:
				{
					bk.openAccount();
					break;
				}
				case 2:
				{
					System.out.println("Enter account number");
					long accNumber = sc.nextLong();
					
					if(menu.accountLogin(accNumber, bk.getAcc())) {
						menu.accountActivity(accNumber, bk);
					}
					else {
						System.out.println("Account not found!");
					}
					break;
				}
				case 3:
				{
					String user, pass;

					System.out.println("Enter USER ID and PASS: ");
					sc.nextLine();
					user = sc.nextLine();
					pass = sc.nextLine();
					if(Bank.checkCredintials(user, pass)) {						
						menu.adminMenu(bk);
					}
					else {
						System.out.println("Wrong Credintials!!!");
					}
					break;
				}
				case 4:
				{
					System.out.println("Enter Account Number: ");
					long accNumber = sc.nextLong();
					bk.closeAccount(accNumber);
					break;
				}
				case 5:
				{
					choice1 = 0;
					break;
				}
				default:
				{
					System.out.println("Invalid Entry!");
				}
			}
			
		}while(choice1 != 0);
		
		System.out.println("-----------Closing our Exclusive Swiss Bank :)-----------");
		
	}
}