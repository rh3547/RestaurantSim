package com.engine.data;

/**
 * A bank is what holds and handles
 * all financial transactions within a game.
 * A bank can also be used to store any other
 * numerical data for other uses, such as
 * experience points, skill points, etc.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Bank
{
	// Transaction Types
	public static final int ADD = 0;
	public static final int SUBTRACT = 1;
	
	private double balance; // The amount of money in this bank
	
	/**
	 * Create a new bank with the given
	 * initial balance.
	 * @param initialBalance
	 */
	public Bank(double initialBalance)
	{
		this.balance = initialBalance;
	}
	
	/**
	 * Alter the balance of this bank.
	 * @param amount - the amount to change by
	 * @param type - the type of transaction (add, subtract, etc.)
	 */
	public boolean changeBalance(double amount, int type)
	{
		if (amount <= 0)
			throw new NumberFormatException("The amount must be greater than 0.");
		
		if (checkTransaction(amount, type))
		{
			switch(type)
			{
				case Bank.ADD:
					this.balance += amount;
					break;
					
				case Bank.SUBTRACT:
					this.balance -= amount;
					break;
			}
			
			return true;
		}
		else
		{
			switch(type)
			{
				case Bank.ADD:
					System.out.println("Adding " + amount + " to your funds would exceed the max value of 999,999.");
					break;
					
				case Bank.SUBTRACT:
					System.out.println("Insufficient funds.");
					break;
			}
			
			return false;
		}
	}
	
	/**
	 * Check if the given transaction can be completed
	 * with the bank in its current state.
	 * @param amount - the amount to change by
	 * @param type - the type of transaction (add, subtract, etc.)
	 * @return boolean - if the transaction is possible
	 */
	public boolean checkTransaction(double amount, int type)
	{
		if (amount <= 0)
			throw new NumberFormatException("The amount must be greater than 0.");
		
		switch(type)
		{
			case Bank.ADD:
				if ((this.balance + amount) <= 999999)
					return true;
				break;
				
			case Bank.SUBTRACT:
				if ((this.balance - amount) >= 0)
					return true;
				break;
		}
		
		return false;
	}

	/**
	 * @return the balance
	 */
	public double getBalance()
	{
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
}