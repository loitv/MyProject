package model;

public class Category {

	private String cateID;
	private String cateName;
	
	//default constructor
	public Category() {
		
	}
	
	// constructor
	public Category(String id, String name) {
		this.cateID = id;
		this.cateName = name;
	}

	public String getCateID() {
		return cateID;
	}

	public String getCateName() {
		return cateName;
	}
}
