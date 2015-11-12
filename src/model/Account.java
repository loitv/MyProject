package model;

public class Account {

	private String accName;
	private String accPassword;
	
	public Account() {
		
	}
	
	public Account(String name, String password) {
		this.accName = name;
		this.accPassword = password;
	}

	public String getAccName() {
		return accName;
	}

	public String getAccPassword() {
		return accPassword;
	}
	
}
