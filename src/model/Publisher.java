package model;

public class Publisher {

	private String pubID;
	private String pubName;
	
	//default constructor
	public Publisher() {
		
	}
	// constructor
	public Publisher(String id, String name) {
		this.pubID = id;
		this.pubName = name;
	}
	public String getPubID() {
		return pubID;
	}
	public String getPubName() {
		return pubName;
	}
	
	
}
