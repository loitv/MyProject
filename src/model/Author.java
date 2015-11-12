package model;

public class Author {

	private String authorID;
	private String authorName;

	// default constructor
	public Author() {

	}

	// costructor
	public Author(String authorID, String authorName) {
		this.authorID = authorID;
		this.authorName = authorName;
	}

	public void setAuthorName(String name) {
		this.authorName = name;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorID(String id) {
		this.authorID = id;
	}

	public String getAuthorID() {
		return authorID;
	}
}
