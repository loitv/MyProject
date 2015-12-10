package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.ReadInfoView;

public class ReaderInfoController {

	private ReadInfoView readeriv;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private String id;
	private Statement statement;

	public ReaderInfoController() {
		readeriv = new ReadInfoView();
		readeriv.getBtnBorInfo().setEnabled(false);
		readeriv.getBtnDelete().setEnabled(false);
		updateTable("select * from personalInfo where type = 3;");

		// Add items into ComboBox
		try {
			String query = "select DISTINCT personalID from personalInfo where type = 3;";
			Statement statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				// display result if not empty
				do {
					String id = rs.getString("personalID");
					readeriv.addItemToComboBox(id);
				} while (rs.next());

			}
		} catch (SQLException sqlEX) {
			sqlEX.printStackTrace();
		}

		readeriv.setIDChangeListener(new ItemListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					id = readeriv.getSelectedID();
					String query;
					if (id.equals("All")) {
						query = "select * from personalInfo where type = 3;";
					} else {
						query = String.format("select * from personalInfo where personalID = '%s'", id);
						readeriv.getBtnBorInfo().setEnabled(true);
						readeriv.getBtnDelete().setEnabled(true);
					}
					data = new Vector();
					updateTable(query); // Update Book information on table
				}
			}
		});

		// handle event when click on the table
		readeriv.setReaderInfoML(new MouseListener() {

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
				int n = readeriv.getReaderInfoTable().getSelectedRow();
				if (n != -1) {
					id = readeriv.getReaderInfoTable().getValueAt(n, 0).toString();
					readeriv.getBtnBorInfo().setEnabled(true);
					readeriv.getBtnDelete().setEnabled(true);
				}
			}
		});

		// handle events when press button Borrowing Info
		readeriv.setBtnBorInfoAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ReaderBorrowingInfoController(id);
			}
		});

		// handle events when press button Delete Account
		readeriv.setBtnDeleteAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int k = JOptionPane.showConfirmDialog(null, "Are you sure to delete this account?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					readeriv.getCbReaderID().removeItem(id);
					ArrayList<Integer> patternIDList = new ArrayList<Integer>();

					String query = String.format("select patternID from patternborrow where readerID = '%s';", id);
					String query1 = String.format("delete from account where userName = '%s';", id);
					String query2 = String.format("delete from personalInfo where personalID = '%s';", id);
					String query3 = String.format("delete from patternborrow where readerID = '%s';", id);
					try {
						statement = controll.ConnectDatabase.getConnection().createStatement();
						ResultSet rs = statement.executeQuery(query);
						if (!rs.first()) {
//							JOptionPane.showMessageDialog(null, "Have no record!");
						} else {
							do {
								patternIDList.add(rs.getInt("patternID"));
							} while (rs.next());
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// delete data from table: pattern_book
					for (int i = 0; i < patternIDList.size(); i++) {
						int patid = patternIDList.get(i);
						String query4 = String.format("delete from pattern_book where patternID = '%s';", patid);
						try (PreparedStatement preStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query4)) {
							preStmt.executeUpdate();
						} catch (SQLException sqlEx) {
							sqlEx.printStackTrace();
						}
					}

					// delete from table: account
					try (PreparedStatement preStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query1)) {
						preStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
					// delete from table: personalInfo
					try (PreparedStatement preStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query2)) {
						preStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
					// delete from table: patternborrow
					try (PreparedStatement preStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query3)) {
						preStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
					updateTable("select * from personalInfo where type = 3;");
				}
			}
		});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateTable(String query) {
		data = new Vector();
		// String query = "select * from personalInfo;";
		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
				updateTable("select * from personalInfo where type = 3;");
			} else {
				do {
					String id = rs.getString("personalID");
					String name = rs.getString("name");
					String gender = rs.getString("gender");
					int phone = rs.getInt("phone");
					String address = rs.getString("address");
					String dateBirth = rs.getString("dateOfBirth");
					String email = rs.getString("email");
					Vector reader = new Vector();
					reader.addElement(id);
					reader.addElement(name);
					reader.addElement(gender);
					reader.addElement(phone);
					reader.addElement(address);
					reader.addElement(dateBirth);
					reader.addElement(email);
					data.add(reader);
				} while (rs.next());
			}
			((ReadInfoView) readeriv).refreshTable(data);
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ReaderInfoController();
	}
}
