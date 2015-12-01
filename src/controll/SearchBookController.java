package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.SearchBookView;

public class SearchBookController {
	private SearchBookView searchBookView;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private String id;

	public SearchBookController() {
		searchBookView = new SearchBookView();
		// DISPLAY ALL BOOK//
		displayBook("select * from book;");

		/////////////////////////////
		/// Add Items into ComboBox///
		/////////////////////////////
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
					searchBookView.addItemToComboBox(category);
				} while (rs.next());
			}
		} catch (SQLException sqlEX) {
			sqlEX.printStackTrace();
		}
		//////////////////////////////////
		/// handle events for btnSearch;///
		//////////////////////////////////
		searchBookView.setBtnSearchAL(new ActionListener() {

			@SuppressWarnings({ "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (searchBookView.getInputBookName().equals("")) {
					JOptionPane.showMessageDialog(null, "BOOK NAME IS EMPTY");
				} else {
					data = new Vector();

					String input = "%".concat(searchBookView.getInputBookName()).concat("%");
					String query = String.format("select * from book where bookName like '%s'", input);
					// display book detail
					displayBook(query);
				}

			}

		});

		// handle event for text field serachBook
		searchBookView.setTfBookAL(new ActionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (searchBookView.getInputBookName().equals("")) {
					JOptionPane.showMessageDialog(null, "BOOK NAME IS EMPTY");
					displayBook("select * from `book`;");
				} else {
					data = new Vector();
					String input = "%".concat(searchBookView.getInputBookName()).concat("%");
					String query = String.format("select * from book where bookName like '%s'", input);
					// display book detail
					displayBook(query);
				}
			}
		});

		// handle events when user choose data into comboBox
		searchBookView.setItemChangeListener(new ItemListener() {

			@SuppressWarnings({ "rawtypes" })
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					id = searchBookView.getSelectedID();
					data = new Vector();
					String query = String.format("select * from book where category = '%s'", id);
					displayBook(query);
				}
			}

		});

		///////////////////
		searchBookView.setTfBookDL(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if (searchBookView.getTfBook().getText().equals("")) {
					displayBook("select * from `book`;");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

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
				displayBook("select * from `book`;");
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
			searchBookView.refreshTable(data);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SearchBookController();
	}
}
