package model;

import java.util.Date;

public class Librarian {

	private String libID;
	private String libName;
	private String libGender;
	private String libPhoneNumber;
	private Date libDateOfBirth;

	// Default constructor
	public Librarian() {

	}

	// Constructor
	public Librarian(String id, String name, String gender, String phone, Date birthDate) {
		this.libID = id;
		this.libName = name;
		this.libGender = gender;
		this.libPhoneNumber = phone;
		this.libDateOfBirth = birthDate;
	}

	public String getLibID() {
		return libID;
	}

	public String getLibName() {
		return libName;
	}

	public String getLibGender() {
		return libGender;
	}

	public String getLibPhoneNumber() {
		return libPhoneNumber;
	}

	public Date getLibDateOfBirth() {
		return libDateOfBirth;
	}

}
