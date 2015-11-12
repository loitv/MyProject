package model;

public class Warehouse {

	private int totalBook;
	private int balanceBook;
	private int borrowBook;
	
	//default constructor
	public Warehouse() {
		
	}
	
	// constructor
	public Warehouse(int total, int balance, int borrow) {
		this.totalBook = total;
		this.balanceBook = balance;
		this.borrowBook = borrow;
	}

	public int getTotalBook() {
		return totalBook;
	}

	public int getBalanceBook() {
		return balanceBook;
	}

	public int getBorrowBook() {
		return borrowBook;
	}
	
	
}
