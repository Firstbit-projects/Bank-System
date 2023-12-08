package com.sam.bank;

import java.time.LocalDate;

public class OpenClose {
	String accType;
	boolean oc;
	LocalDate date;
	
	public OpenClose() {
		// TODO Auto-generated constructor stub
	}
	
	public OpenClose(String accType, boolean oc) 
	{
		this.accType = accType;
		this.oc = oc;
		this.date = LocalDate.now();
	}

	public String getAccType() 
	{
		return accType;
	}

	public void setAccType(String accType) 
	{
		this.accType = accType;
	}

	public LocalDate getDate() 
	{
		return date;
	}

	public void setDate(LocalDate date) 
	{
		this.date = date;
	}
	
	public void display() {
		if(oc) {			
			System.out.println(this.accType + " Opened "+ this.date);
		}
		else {
			System.out.println(this.accType + " Closed "+ this.date);
			
		}
	}
}
