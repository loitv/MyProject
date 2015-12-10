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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdesktop.xswingx.PromptSupport;

import model.DateLabelFormatter;
//import view.BookView;
import view.PatternBorrowView;
import view.TemporaryBookView;

public class PatternBorrowController {

	private PatternBorrowView patternbv;
	private TemporaryBookView tempBook;
	@SuppressWarnings("rawtypes")
	private Vector data, data1;
	private Statement statement;

	////////////// CONSTRUCTOR////////////////
	public PatternBorrowController() {
		patternbv = new PatternBorrowView();
		updateTable();

		////////////////////////////////
		/// When TfPatternID change/////
		patternbv.setTfPatternIDChangeDL(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				patternbv.getBtnAdd().setEnabled(true);
				patternbv.getBtnDelete().setEnabled(true);
				patternbv.getBtnCreatePattern().setEnabled(true);
				patternbv.getBtnRemove().setEnabled(true);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Integer> patternIDList = createListOfPatternID();
				int a = 0;
				int patternID = patternbv.getPatternID();
				for (int i = 0; i < patternIDList.size(); i++) {
					if (patternID != patternIDList.get(i)) {
						a++;
					}
					if (patternID == patternIDList.get(i)) {
						break;
					}
				}
				if (a == patternIDList.size()) { // new pattern borrow
					patternbv.getBtnDelete().setEnabled(false);
				} else {
					patternbv.getBtnAdd().setEnabled(false);
					patternbv.getBtnCreatePattern().setEnabled(false);
					patternbv.getBtnRemove().setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		/////////////////////////////////////////////
		///// handle events for TextField ReaderID///
		patternbv.setTfReaderIDContentChange(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				PromptSupport.uninstall(patternbv.getTfReaderName());
				patternbv.getTfReaderName().setText("");
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<String> readerIDList = createListOfReaderID();
				int a = 0;
				String readerID = patternbv.getReaderID();
				for (int i = 0; i < readerIDList.size(); i++) {
					if (!readerID.equalsIgnoreCase(readerIDList.get(i))) {
						a++;
					}
					if (readerID.equalsIgnoreCase(readerIDList.get(i))) {
						break;
					}
				}
				if (a == readerIDList.size()) {
					PromptSupport.uninstall(patternbv.getTfReaderName());
					patternbv.getTfReaderName().setText("");
				} else {
					try {
						statement = controll.ConnectDatabase.getConnection().createStatement();
						String query = "select * from personalInfo;";
						ResultSet rs = statement.executeQuery(query);
						if (!rs.first()) {
							JOptionPane.showMessageDialog(null, "HAVE NO RECORD1!");
						} else {
							do {
								String userId = rs.getString("personalID");
								String name = rs.getString("name");
								if (userId.equalsIgnoreCase(readerID)) {
									if (name == null) {
										PromptSupport.setPrompt("Hasn't been set", patternbv.getTfReaderName());
									} else {
										patternbv.getTfReaderName().setText(name);
									}
									break;
								}
							} while (rs.next());
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		////////////////////////////////////////////
		//// handle events for TextFiel ISBN/////////
		patternbv.setTFISBNChangeAL(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				patternbv.getTfTittle().setText("");
				patternbv.getTfPrice().setText("");
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<Integer> isbnList = createListOfISBN();
				int a = 0;
				int isbn = patternbv.getISBN();
				for (int i = 0; i < isbnList.size(); i++) {
					if (isbn != isbnList.get(i)) {
						a++;
					}
					if (isbn == isbnList.get(i)) {
						break;
					}
				}
				if (a == isbnList.size()) {
					patternbv.getTfTittle().setText("");
					patternbv.getTfPrice().setText("");
				} else {
					try {
						statement = controll.ConnectDatabase.getConnection().createStatement();
						String query = "select * from book;";
						ResultSet rs = statement.executeQuery(query);
						if (!rs.first()) {
							JOptionPane.showMessageDialog(null, "HAVE NO RECORD2!");
						} else {
							do {
								int bookISBN = rs.getInt("isbn");
								String title = rs.getString("bookName");
								double price = rs.getDouble("price");
								if (isbn == bookISBN) {
									patternbv.getTfTittle().setText(title);
									patternbv.getTfPrice().setText(Double.toString(price));
									break;
								}
							} while (rs.next());
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		//////////////////////////////////////
		// handle events for borrowDatePicker//
		patternbv.setBorrowDateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Date a = patternbv.getModel1().getValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(a);
				cal.add(Calendar.MONTH, 5);
				Date b = cal.getTime();
				patternbv.getModel2().setValue(b);
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				JDatePanelImpl datePanel = new JDatePanelImpl(patternbv.getModel2(), p);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
				patternbv.setReturnDatePicker(datePicker);
			}
		});

		/////////////////////////////////////////////////
		////////// handle events for button CREATE//////////
		/////////////////////////////////////////////////
		patternbv.setBtnCreatePatternAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<String> readerIDList = createListOfReaderID();
				int b = 0;
				double temp = 0, deposit = 0;
				ArrayList<String> tempTitleList = new ArrayList<String>();
				ArrayList<Integer> tempIsbnList = new ArrayList<Integer>();
				ArrayList<Double> tempPriceList = new ArrayList<Double>();

				try {
					String queryB = "select * from `tempbook`;";
					Statement st = controll.ConnectDatabase.getConnection().createStatement();
					ResultSet rs = st.executeQuery(queryB);
					if (!rs.first()) {
						JOptionPane.showMessageDialog(null, "NO BOOK WAS ADDED!");
					} else {
						do {
							tempIsbnList.add(rs.getInt("isbn"));
							tempTitleList.add(rs.getString("title"));
							tempPriceList.add(rs.getDouble("price"));
						} while (rs.next());
						for (int i = 0; i < tempPriceList.size(); i++) {
							temp += tempPriceList.get(i);
							deposit = 0.25 * temp;
						}
						////////////////////////////////
						try {
							if (patternbv.getPatternID() == 0 || patternbv.getReaderID().equals("")
									|| patternbv.getReaderName().equals("") || patternbv.getBorrowDate().equals("")
									|| patternbv.getReturnDate().equals("")) {
								JOptionPane.showMessageDialog(null, "SOME FIELDS MUST BE FILLED!");
							} else {
								int patternID = patternbv.getPatternID();
								String readerID = patternbv.getReaderID();
								String readerName = patternbv.getReaderName();
								String query = "insert into patternborrow values (?, ?, ?, ?, ?, ?);";
								try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
										.getConnection().prepareStatement(query)) {
									insertStmt.setInt(1, patternbv.getPatternID());
									insertStmt.setString(2, patternbv.getReaderID());
									insertStmt.setString(3, patternbv.getReaderName());
									insertStmt.setString(4, patternbv.getBorrowDate());
									insertStmt.setString(5, patternbv.getReturnDate());
									insertStmt.setDouble(6, deposit);
									insertStmt.executeUpdate();

									patternbv.getTfPatternID().setText("");
									patternbv.getTfReaderID().setText("");
									patternbv.getTfReaderName().setText("");
									// patternbv.gettf

								} catch (Exception ex) {
									ex.printStackTrace();
								}

								String queryA = "insert into pattern_book values(?,?,?);";
								for (int i = 0; i < tempTitleList.size(); i++) {
									try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
											.getConnection().prepareStatement(queryA)) {
										insertStmt.setInt(1, patternID);
										insertStmt.setInt(2, tempIsbnList.get(i));
										insertStmt.setString(3, tempTitleList.get(i));
										insertStmt.executeUpdate();
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
								// Update pattern List
								updateTable();

								/// Delete TemoraryBook's data///
								String queryC = "delete from tempbook";
								try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
										.getConnection().prepareStatement(queryC)) {
									insertStmt.executeUpdate();
								} catch (Exception ex) {
									ex.printStackTrace();
								}

								// UPDATE tables in database `Library`: account,
								// personalInfo
								for (int i = 0; i < readerIDList.size(); i++) {
									if (!readerID.equalsIgnoreCase(readerIDList.get(i))) {
										b++;
									}
									if (readerID.equalsIgnoreCase(readerIDList.get(i))) {
										break;
									}
								}

								if (b == readerIDList.size()) { // New Account
									String query1 = "insert into account values (?,?,?);";
									String query2 = "insert into `personalInfo` values (?,?,?,?,?,?,?,?);";
									try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
											.getConnection().prepareStatement(query1)) {
										insertStmt.setString(1, readerID);
										insertStmt.setString(2, "000");
										insertStmt.setInt(3, 3);
										insertStmt.executeUpdate();
									} catch (Exception ex) {
										ex.printStackTrace();
									}
									try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase
											.getConnection().prepareStatement(query2)) {
										insertStmt.setString(1, readerID);
										insertStmt.setString(2, readerName);
										insertStmt.setString(3, null);
										insertStmt.setInt(4, 0);
										insertStmt.setString(5, null);
										insertStmt.setString(6, null);
										insertStmt.setString(7, null);
										insertStmt.setInt(8, 3);
										insertStmt.executeUpdate();
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								} else {
									String query3 = "UPDATE personalInfo SET name=? WHERE personalID=?";
									try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase
											.getConnection().prepareStatement(query3)) {

										addStmt.setString(1, readerName);
										addStmt.setString(2, readerID);
										addStmt.executeUpdate();

									} catch (SQLException ex) {
										ex.printStackTrace();
									}
								}

							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}
						///////////////////////////////////
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		/////////////////////////////////////////////////
		////////// handle events for button ADD//////////
		/////////////////////////////////////////////////
		patternbv.setBtnAddAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// tempBook = new TemporaryBookView();
				String query = "insert into tempbook values (?,?,?)";
				try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
						.prepareStatement(query)) {
					if (patternbv.getISBN() == 0) {
						JOptionPane.showMessageDialog(null, "CANNOT ADD!");
					} else {
						insertStmt.setInt(1, patternbv.getISBN());
						insertStmt.setString(2, patternbv.getTitleBook());
						insertStmt.setDouble(3, patternbv.getPrice());
						insertStmt.executeUpdate();
						updateTempBookTable();
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});

		/////////////////////////////////////////////////
		///////// handle events for button REMOVE////////
		/////////////////////////////////////////////////
		patternbv.setBtnRemoveBookAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String query1 = "select * from tempbook";
				try {
					Statement st = controll.ConnectDatabase.getConnection().createStatement();
					ResultSet rs = st.executeQuery(query1);
					if (!rs.first()) {
						JOptionPane.showMessageDialog(null, "EMPTY LIST!");
					} else {
						String query = "Delete from `tempbook` where isbn = ?";
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query)) {
							insertStmt.setInt(1, patternbv.getISBN());
							insertStmt.executeUpdate();
							updateTempBookTable();
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "NO AVAILBLE BOOK!");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		///////////////////////////////////////////////////
		//// Handle events for button DELETE///////////////
		//////////////////////////////////////////////////
		patternbv.setBtnDeleteAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int patternID = patternbv.getPatternID();
				if (patternID == 0) {
					JOptionPane.showMessageDialog(null, "PATTERN'S ID FIELD IS EMPTY!");
				} else {
					int k = JOptionPane.showConfirmDialog(null, "Are you sure to delete this pattern?",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (k == 0) {
						String query1 = "delete from patternborrow where patternID = ?";
						String query2 = "delete from pattern_book where patternID = ?";
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query1)) {
							insertStmt.setInt(1, patternID);
							insertStmt.executeUpdate();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query2)) {
							insertStmt.setInt(1, patternID);
							insertStmt.executeUpdate();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						updateTable();
					}
				}
			}
		});

		//////////////////////////////////////////////////
		//////////// handle events for Table//////////////
		//////////////////////////////////////////////////
		patternbv.setPaternBorrowDetailsML(new MouseListener() {

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

			}
		});

	}

	// update table of pattern borrow's information
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateTable() {
		data = new Vector(); // NOTE THIS STATEMENT
		String query = "select patternBorrow.patternID, readerID, readerName, group_concat( isbn SEPARATOR ', ') as isbn, group_concat(title SEPARATOR ', ') as title, borrowDate, returnDate, deposit from patternborrow left join pattern_book on patternborrow.patternID = pattern_book.patternID group by patternborrow.patternID;";

		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "NONE PATTERN HAS BEEN CREATED!");
			} else {
				do {
					int patternID = rs.getInt("patternID");
					String readerID = rs.getString("readerID");
					String readerName = rs.getString("readerName");
					String isbn = rs.getString("isbn");
					String bookName = rs.getString("title");
					String borrowDate = rs.getString("borrowDate");
					String returnDate = rs.getString("returnDate");
					double deposit = rs.getDouble("deposit");

					Vector tempData = new Vector();
					tempData.addElement(patternID);
					tempData.addElement(readerID);
					tempData.addElement(readerName);
					tempData.addElement(isbn);
					tempData.addElement(bookName);
					tempData.addElement(borrowDate);
					tempData.addElement(returnDate);
					tempData.addElement(deposit);
					data.add(tempData);

				} while (rs.next());
			}
			((PatternBorrowView) patternbv).refreshTable(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Integer> createListOfPatternID() {
		ArrayList<Integer> patternIDList = new ArrayList<Integer>();
		// int a = 0;
		try {
			String query = "select * from patternborrow;";
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				// JOptionPane.showMessageDialog(null, "HAVE NO RECORD3");
			} else {
				do {
					patternIDList.add(rs.getInt("patternID"));
				} while (rs.next());
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return patternIDList;
	}

	public ArrayList<String> createListOfReaderID() {
		ArrayList<String> readerIDList = new ArrayList<String>();
		// int a = 0;
		try {
			String query = "select * from account where type = 3;";
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD4");
			} else {
				do {
					readerIDList.add(rs.getString("userName"));
				} while (rs.next());
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return readerIDList;
	}

	public ArrayList<Integer> createListOfISBN() {
		ArrayList<Integer> isbnList = new ArrayList<Integer>();
		// int a = 0;
		try {
			String query = "select * from book;";
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD5");
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateTempBookTable() {
		data1 = new Vector();
		String query = "select * from tempbook;";
		tempBook = new TemporaryBookView();
		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD6!");
			} else {
				do {
					int isbn = rs.getInt("isbn");
					String title = rs.getString("title");
					double price = rs.getDouble("price");
					Vector tempData = new Vector();
					tempData.addElement(isbn);
					tempData.addElement(title);
					tempData.addElement(price);
					data1.add(tempData);
				} while (rs.next());
				((TemporaryBookView) tempBook).refreshTable(data1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new PatternBorrowController();
	}
}
