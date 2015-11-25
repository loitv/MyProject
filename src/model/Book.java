package model;

public class Book {

	private String isbn;
	private String title;
	private String category;
	private String author;
	private double price;
	private int quantity;

	// default constructor
	public Book() {

	}

	// constructor
	public Book(String isbn, String title, String category, String author, double price, int quantity) {
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setIdAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

}
