package model;

import java.util.Date;

public class Reader {

	private String readerID;
	private String readerName;
	private String readerGender;
	private String readerPhone;
	private Date readerDateOfBirth;
	private String readerAddress;
	
	//Default constructor
	public Reader() {
		
	}
	//Constructor
	public Reader(String id, String name, String gender, String phone, Date birthDate, String address) {
		this.readerID = id;
		this.readerName = name;
		this.readerGender = gender;
		this.readerPhone = phone;
		this.readerDateOfBirth = birthDate;
		this.readerAddress = address;
	}
	public String getReaderID() {
		return readerID;
	}
	public String getReaderName() {
		return readerName;
	}
	public String getReaderGender() {
		return readerGender;
	}
	public String getReaderPhone() {
		return readerPhone;
	}
	public Date getReaderDateOfBirth() {
		return readerDateOfBirth;
	}
	public String getReaderAddress() {
		return readerAddress;
	}
}
