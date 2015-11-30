package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.BookView;

public class BookController {

	private BookView bookView;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private Statement statement;

	// the constructor and events are being processed in here
	@SuppressWarnings({ "rawtypes" })
	public BookController() {
		data = new Vector();
		bookView = new BookView();
		// String query = "select * from book;";
		updateBook();
		//////////////////////////////////
		/// handle event for button Add///
		//////////////////////////////////
		bookView.setBtnAddAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				ArrayList<Integer> isbnList = createListOfISBN();
				int a = 0;

				// Add new book or add a quantity of availble book in lirary
				try {
					if (bookView.getISBN() == 0 || bookView.getTitle().equals("") || bookView.getAuthor().equals("")
							|| bookView.getCategory().equals("") || bookView.getPrice() == 0
							|| bookView.getQuantity() == 0) {
						JOptionPane.showMessageDialog(null, "SOME FIELDS MUST BE FILLED!");
					} else {
						int isbn = bookView.getISBN();
						for (int i = 0; i < isbnList.size(); i++) {
							if (isbn != isbnList.get(i)) {
								a++;
							}
							if (isbn == isbnList.get(i)) {
								break;
							}
						}
						if (a == isbnList.size()) { // Not concident with any
													// available book
							String query = "insert into `book` values (?,?,?,?,?,?,?);";
							try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
									.getConnection().prepareStatement(query)) {
								insertStmt.setInt(1, bookView.getISBN());
								insertStmt.setString(2, bookView.getTitle());
								insertStmt.setString(3, bookView.getAuthor());
								insertStmt.setString(4, bookView.getCategory());
								insertStmt.setDouble(5, bookView.getPrice());
								insertStmt.setInt(6, bookView.getQuantity());
								insertStmt.setInt(7, bookView.getQuantity());
								insertStmt.executeUpdate();

								updateBook();

								// set all fields empty
								bookView.getTfISBN().setText("");
								bookView.getTfTitle().setText("");
								bookView.getTfAuthor().setText("");
								bookView.getTfCategory().setText("");
								bookView.getTfPrice().setText("");
								bookView.getTfQuantity().setText("");
								bookView.getTfISBN().setEditable(true);

							} catch (SQLException sqlEx) {
								sqlEx.printStackTrace();
							}
						} else {
							String query = "UPDATE book SET Total=Total + ? ,Remain=Remain+? WHERE ISBN=?";
							try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
									.getConnection().prepareStatement(query)) {
								insertStmt.setInt(1, bookView.getQuantity());
								insertStmt.setInt(2, bookView.getQuantity());
								insertStmt.setInt(3, bookView.getISBN());
								insertStmt.executeUpdate();

								updateBook();

								// set all fields empty
								bookView.getTfISBN().setText("");
								bookView.getTfTitle().setText("");
								bookView.getTfAuthor().setText("");
								bookView.getTfCategory().setText("");
								bookView.getTfPrice().setText("");
								bookView.getTfQuantity().setText("");
								bookView.getTfISBN().setEditable(true);
							} catch (SQLException sqlEx) {
								sqlEx.printStackTrace();
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERRORS SOMEWHERE!");
				}
			}
		});
		//////////////////////////////////
		// handle events for button Edit//
		//////////////////////////////////
		bookView.setBtnEditAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (bookView.getTitle().equals("") || bookView.getAuthor().equals("")
							|| bookView.getCategory().equals("") || bookView.getPrice() == 0) {
						JOptionPane.showMessageDialog(null, "SOME FIELDS MUST BE FILLED!");
					} else {
						String query1 = "UPDATE book SET bookName=? ,author=?,category=?,price=? WHERE ISBN=?";
						try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query1)) {

							addStmt.setString(1, bookView.getTitle());
							addStmt.setString(2, bookView.getAuthor());
							addStmt.setString(3, bookView.getCategory());
							addStmt.setDouble(4, bookView.getPrice());
							addStmt.setInt(5, bookView.getISBN());
							addStmt.executeUpdate();

						} catch (Exception ex) {
							// ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "ERRORS SOMEWHERE!");
						}
						updateBook();

						// set all fields empty
						bookView.getTfISBN().setText("");
						bookView.getTfTitle().setText("");
						bookView.getTfAuthor().setText("");
						bookView.getTfCategory().setText("");
						bookView.getTfPrice().setText("");
						bookView.getTfQuantity().setText("");
						bookView.getTfISBN().setEditable(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERRORS SOMEWHERE!");
				}

			}
		});

		////////////////////////////////////
		// handle events for button Delete//
		////////////////////////////////////
		bookView.setBtnDeleteAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// JOptionPane.showConfirmDialog(null, "Are you sure to delete
				// this book?", "Warning",
				// JOptionPane.YES_NO_OPTION);

				ArrayList<Integer> isbnList = createListOfISBN();
				int a = 0;
				// Delete book or subtract a quantity of availble book in lirary
				try {
					if (bookView.getISBN() == 0) {
						JOptionPane.showMessageDialog(null, "Cannot delete caused ISBN field is empty!");
					} else {
						int isbn = bookView.getISBN();
						for (int i = 0; i < isbnList.size(); i++) {
							if (isbn != isbnList.get(i)) {
								a++;
							}
							if (isbn == isbnList.get(i)) {
								break;
							}
						}
						if (a == isbnList.size()) { // Not concident with any
													// available book
							JOptionPane.showMessageDialog(null, "Cannot delete caused ISBN ISN'T CORRECT!");

						} else {
							if (bookView.getQuantity() == 0) {
								int k = JOptionPane.showConfirmDialog(null, "Are you sure to delete this book?",
										"Warning", JOptionPane.YES_NO_OPTION);
								if (k == 0) {
									String query = "Delete from `book` where isbn = ?";
									try (PreparedStatement preStmt = (PreparedStatement) controll.ConnectDatabase
											.getConnection().prepareStatement(query)) {
										preStmt.setInt(1, isbn);
										preStmt.executeUpdate();
										updateBook();
									} catch (SQLException sqlEx) {
										sqlEx.printStackTrace();
									}
								}
							} else {
								String query1 = "select * from book";
								try {
									Statement statement = controll.ConnectDatabase.getConnection().createStatement();
									ResultSet rs = statement.executeQuery(query1);
									if (!rs.first()) {
										JOptionPane.showMessageDialog(null, "HAVE NO RECORD!");
									} else {
										int quantity;
										do {
											quantity = rs.getInt("Remain");
											int selectedISBN = rs.getInt("ISBN");
											if (isbn == selectedISBN) {
												break;
											}
										} while (rs.next());
										if (bookView.getQuantity() >= quantity || bookView.getQuantity() <= 0) {
											JOptionPane.showMessageDialog(null, "INVALID QUANTITY!");
										} else { // case: input quantity is
													// valid
											String query = "UPDATE book SET Total=Total - ? ,Remain=Remain-? WHERE ISBN=?";
											try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
													.getConnection().prepareStatement(query)) {
//												insertStmt.setArray(parameterIndex, x);
												insertStmt.setInt(1, bookView.getQuantity());
												insertStmt.setInt(2, bookView.getQuantity());
												insertStmt.setInt(3, bookView.getISBN());
												insertStmt.executeUpdate();

												updateBook();

												// set all fields empty
												bookView.getTfISBN().setText("");
												bookView.getTfTitle().setText("");
												bookView.getTfAuthor().setText("");
												bookView.getTfCategory().setText("");
												bookView.getTfPrice().setText("");
												bookView.getTfQuantity().setText("");
												bookView.getTfISBN().setEditable(true);
											} catch (SQLException sqlEx) {
												sqlEx.printStackTrace();
											}
										}
									}
								} catch (SQLException sqlEx) {
									// JOptionPane.showMessageDialog(null,
									// "ERRORS SOMEWHERE!");
									sqlEx.printStackTrace();
								}
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERRORS SOMEWHERE!");
				}
				// String query = "Delete from book where isbn = ?";

			}
		});

		// handle events when user click in row table
		bookView.setBookDetailsML(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int n = bookView.getBookDetails().getSelectedRow();

				if (n != -1) {
					try {
						bookView.getTfISBN().setText(bookView.getBookDetails().getValueAt(n, 0).toString());
						bookView.getTfTitle().setText(bookView.getBookDetails().getValueAt(n, 1).toString());
						bookView.getTfAuthor().setText(bookView.getBookDetails().getValueAt(n, 2).toString());
						bookView.getTfCategory().setText(bookView.getBookDetails().getValueAt(n, 3).toString());
						bookView.getTfPrice().setText(bookView.getBookDetails().getValueAt(n, 4).toString());
						bookView.getTfISBN().setEditable(false);
					} catch (Exception ex) {
						System.out.println("Doesn't have values");
					}
				}
			}
		});

	}

	// method for update book
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateBook() {
		data = new Vector(); // NOTE THIS STATEMENT
		String query = "select * from book;";
		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				System.out.println("HAVE NO BOOK!");
			} else {
				do {
					int isbn = rs.getInt("ISBN");
					String name = rs.getString("bookName");
					String author = rs.getString("author");
					String category = rs.getString("category");
					double price = rs.getDouble("Price");
					int total = rs.getInt("total");
					int remain = rs.getInt("remain");
					Vector tempData = new Vector();
					tempData.addElement(isbn);
					tempData.addElement(name);
					tempData.addElement(author);
					tempData.addElement(category);
					tempData.addElement(price);
					tempData.addElement(total);
					tempData.addElement(remain);
					data.add(tempData);
				} while (rs.next());
			}
			((BookView) bookView).refreshTable(data);
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public ArrayList<Integer> createListOfISBN() {
		ArrayList<Integer> isbnList = new ArrayList<Integer>();
		// int a = 0;
		try {
			String query = "select * from book;";
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				do {
					isbnList.add(rs.getInt("ISBN"));
				} while (rs.next());
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return isbnList;
	}

	public static void main(String[] args) {
		new BookController();
	}
}
