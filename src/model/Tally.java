package model;

import java.util.Date;

public class Tally {

	private String tallyID;
	private String readerID;
	private String librarianID;
	private Date borrowDate;
	private Date sendBackDate;
	private double tienThue;
	private double tienCoc;

	// default constructor
	public Tally() {

	}

	// constructor
	public Tally(String id, String readerID, String libID, Date borrowDate, Date sendBackDate, double tienThue,
			double tienCoc) {
		this.tallyID = id;
		this.readerID = readerID;
		this.librarianID = libID;
		this.borrowDate = borrowDate;
		this.sendBackDate = sendBackDate;
		this.tienThue = tienThue;
		this.tienCoc = tienCoc;
	}

	public String getTallyID() {
		return tallyID;
	}

	public String getReaderID() {
		return readerID;
	}

	public String getLibrarianID() {
		return librarianID;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public Date getSendBackDate() {
		return sendBackDate;
	}

	public double getTienThue() {
		return tienThue;
	}

	public double getTienCoc() {
		return tienCoc;
	}

}
