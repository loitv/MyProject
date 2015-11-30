package controll;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.*;

public class AllBookController {

	private AllBookView allBook;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private String id;

	public AllBookController() {
		allBook = new AllBookView();
		// DISPLAY ALL BOOK ON TABLE
		displayBook("select * from book;");
		
		// Add items into ComboBox
		String query = "select DISTINCT CATEGORY from BOOK;";
		try {
			Statement statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				// display result if not empty
				do {
					String category = rs.getString("category");
					allBook.addItemsToComboBox(category);
				} while (rs.next());
			}
		} catch (SQLException sqlEX) {
			sqlEX.printStackTrace();
		}

		// handle events when user choose data into comboBox
		allBook.setItemChangeListener(new ItemListener() {

			@SuppressWarnings({ "rawtypes" })
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					id = allBook.getSelectedID();
					String query;
					if (id.equals("All")) {
						query = "select * from book";
					} else {
						query = String.format("select * from book where category = '%s'", id);
					}
					data = new Vector();
					displayBook(query); // Update Book information on table
				}
			}

		});

	}

	// method for displaying book detail on table
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayBook(String query) {
		data = new Vector();
		try {
			Statement statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				// display if not empty
				do {
					int isbn = rs.getInt("isbn");
					String bookName = rs.getString("bookName");
					String author = rs.getString("author");
					String category = rs.getString("category");
					double price = rs.getDouble("Price");

					Vector bookDetail = new Vector();
					bookDetail.addElement(isbn);
					bookDetail.addElement(bookName);
					bookDetail.addElement(author);
					bookDetail.addElement(category);
					bookDetail.addElement(price);
					data.add(bookDetail);
				} while (rs.next());
			}
			allBook.refreshTable(data);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AllBookController();
	}
}
