package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.jdbc.PreparedStatement;

import view.LibrarianManagerView;

public class LibrarianManagerController {

	private LibrarianManagerView libmv;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private Statement statement;

	// constructor
	public LibrarianManagerController() {
		libmv = new LibrarianManagerView();
		updateTable();
		textFielsEnable(false);
		buttonEnable1(false);

		// handle event when TextField LibrarianID changing
		libmv.setTfLibIDDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> libIDList = createListOfLibrarianID();
				int a = 0;
				String libID = libmv.getLibID();
				for (int i = 0; i < libIDList.size(); i++) {
					if (!libID.equalsIgnoreCase(libIDList.get(i))) {
						a++;
					}
					if (libID.equalsIgnoreCase(libIDList.get(i))) {
						break;
					}
				}
				if (a == libIDList.size()) { // new Librarian
					buttonEnable2(true);
					textFielsEnable(true);
				} else {
					buttonEnable2(false);
					textFielsEnable(false);
				}
				if (libID.equals("")) {
					textFielsEnable(false);
					buttonEnable1(false);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> libIDList = createListOfLibrarianID();
				int a = 0;
				String libID = libmv.getLibID();
				for (int i = 0; i < libIDList.size(); i++) {
					if (!libID.equalsIgnoreCase(libIDList.get(i))) {
						a++;
					}
					if (libID.equalsIgnoreCase(libIDList.get(i))) {
						break;
					}
				}
				// System.out.println(libIDList.toString()+"\n"+a);
				if (a == libIDList.size()) { // new Librarian
					buttonEnable2(true);
					textFielsEnable(true);
				} else {
					buttonEnable2(false);
					textFielsEnable(true);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		// handle event for button add
		libmv.setBtnAddAL(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String query1 = "insert into account values (?, ?, ?);";
				String query2 = "insert into personalInfo values (?, ?, ?, ?, ?, ?, ?, ?);";
				if (libmv.getName().equals("") || libmv.getPhone() == 0 || libmv.getAddress().equals("")
						|| libmv.getEmail().equals("")) {
					JOptionPane.showMessageDialog(null, "YOU MUST FILL ALL FIELDS");
				} else {
					try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query1)) {
						insertStmt.setString(1, libmv.getLibID());
						insertStmt.setString(2, "000");
						insertStmt.setInt(3, 2);
						insertStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
					try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query2)) {
						insertStmt.setString(1, libmv.getLibID());
						insertStmt.setString(2, libmv.getName());
						insertStmt.setString(3, libmv.getCbGender().getSelectedItem().toString());
						insertStmt.setInt(4, libmv.getPhone());
						insertStmt.setString(5, libmv.getAddress());
						insertStmt.setString(6, libmv.getDateOfBirth());
						insertStmt.setString(7, libmv.getEmail());
						insertStmt.setInt(8, 2);
						insertStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}

					updateTable();
				}
			}
		});

		// handle events for button Edit
		libmv.setBtnEditAL(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (libmv.getName().equals("") || libmv.getPhone() == 0 || libmv.getAddress().equals("")
						|| libmv.getEmail().equals("")) {
					JOptionPane.showMessageDialog(null, "YOU MUST FILL ALL FIELDS");
				} else {
					String query = "UPDATE personalInfo SET name=? ,gender=?,phone=?,address=?, dateofbirth=?, email=? WHERE personalID=?";
					try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query)) {

						addStmt.setString(1, libmv.getName());
						addStmt.setString(2, libmv.getGender());
						addStmt.setInt(3, libmv.getPhone());
						addStmt.setString(4, libmv.getAddress());
						addStmt.setString(5, libmv.getDateOfBirth());
						addStmt.setString(6, libmv.getEmail());
						addStmt.setString(7, libmv.getLibID());
						addStmt.executeUpdate();
						updateTable();

					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		// handle events for button delete
		libmv.setBtnDeleteAL(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (libmv.getLibID().equals("")) {
					JOptionPane.showMessageDialog(null, "PLEASE ENTER LIBRARIAN'S ID");
				} else {
					int k = JOptionPane.showConfirmDialog(null, "Are you sure to delete this Librarian?",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (k == 0) {
						String query1 = "DELETE from account WHERE userName=?";
						String query2 = "DELETE from personalInfo WHERE personalID=?";
						try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query1)) {
							addStmt.setString(1, libmv.getLibID());
							addStmt.executeUpdate();

						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query2)) {
							addStmt.setString(1, libmv.getLibID());
							addStmt.executeUpdate();

						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						updateTable();
					}
				}
			}
		});
		
		// handle events when click on a row in table
		libmv.setJTableMouseListener(new MouseListener() {
			
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
				int n = libmv.getLibrarianManegerInfoTable().getSelectedRow();

				if (n != -1) {
					try {
						libmv.getTfID().setText(libmv.getLibrarianManegerInfoTable().getValueAt(n, 0).toString());
						libmv.getTfName().setText(libmv.getLibrarianManegerInfoTable().getValueAt(n, 1).toString());
						libmv.getCbGender().setSelectedItem(libmv.getLibrarianManegerInfoTable().getValueAt(n, 2).toString());
						libmv.getTfPhone().setText(libmv.getLibrarianManegerInfoTable().getValueAt(n, 3).toString());
						libmv.getTfAddress().setText(libmv.getLibrarianManegerInfoTable().getValueAt(n, 4).toString());
						libmv.getTfEmail().setText(libmv.getLibrarianManegerInfoTable().getValueAt(n, 6).toString());
						libmv.getTfID().setEditable(false);
					} catch (Exception ex) {
						System.out.println("Doesn't have values");
					}
				}
			}
		});
	}
	
	

	public void textFielsEnable(boolean a) {
		libmv.getTfAddress().setEditable(a);
		libmv.getTfEmail().setEditable(a);
		libmv.getTfName().setEditable(a);
		libmv.getTfPhone().setEditable(a);
		libmv.getDatePicker().setEnabled(a);
		libmv.getCbGender().setEnabled(a);
	}

	public void buttonEnable1(boolean a) {
		libmv.getBtnAdd().setEnabled(a);
		libmv.getBtnDelete().setEnabled(a);
		libmv.getBtnEdit().setEnabled(a);
	}

	public void buttonEnable2(boolean a) {
		libmv.getBtnAdd().setEnabled(a);
		libmv.getBtnDelete().setEnabled(!a);
		libmv.getBtnEdit().setEnabled(!a);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateTable() {
		data = new Vector(); // NOTE THIS STATEMENT
		String query = "select * from personalInfo where type = 2";
		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD1!");
			} else {
				do {
					String id = rs.getString("personalID");
					String name = rs.getString("name");
					String gender = rs.getString("gender");
					int phone = rs.getInt("phone");
					String address = rs.getString("address");
					String dateBirth = rs.getString("dateOfBirth");
					String email = rs.getString("email");
					Vector librarian = new Vector();
					librarian.addElement(id);
					librarian.addElement(name);
					librarian.addElement(gender);
					librarian.addElement(phone);
					librarian.addElement(address);
					librarian.addElement(dateBirth);
					librarian.addElement(email);
					data.add(librarian);
				} while (rs.next());
			}
			((LibrarianManagerView) libmv).refreshTable(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String> createListOfLibrarianID() {
		ArrayList<String> libIDList = new ArrayList<String>();
		// int a = 0;
		try {
			String query = "select * from account where type = 2;";
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD4");
			} else {
				do {
					libIDList.add(rs.getString("userName"));
				} while (rs.next());
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return libIDList;
	}

	public static void main(String[] args) {
		new LibrarianManagerController();
	}
}
