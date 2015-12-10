package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.PersonalInformationView;

public class PersonalInformationController {

	private PersonalInformationView personalInfo;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private Statement statement;

	// constructor
	@SuppressWarnings({})
	public PersonalInformationController(String personalID) {

		personalInfo = new PersonalInformationView();
		personalInfo.setIDName(personalID);

		// show table information
		updateTable(personalID);

		// handle events when user click in row table
		personalInfo.setJTableMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

				try { // get informations from table and set them to TextField
						// for editing and updating
					personalInfo.getTfName().setText(personalInfo.getTable().getValueAt(0, 0).toString());
					personalInfo.getCbGen().setSelectedItem(personalInfo.getTable().getValueAt(0, 1));
					personalInfo.getTfEmail().setText(personalInfo.getTable().getValueAt(0, 5).toString());
					personalInfo.getTfAddress().setText(personalInfo.getTable().getValueAt(0, 3).toString());
					personalInfo.getTfPhone().setText(personalInfo.getTable().getValueAt(0, 2).toString());

				} catch (NullPointerException ex) {
					System.out.println("Doesn't have values");
				}
				// }
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		// handle events for button Update
		personalInfo.setBtnUpdateAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (personalInfo.getName().equals("") || personalInfo.getAddress().equals("")
						|| personalInfo.getEmail().equals("") || personalInfo.getPhone() == 0) {
					JOptionPane.showMessageDialog(null, "CANNOT UPDATE!");

				} else {
					String query = "UPDATE personalInfo SET name=? ,gender=?,phone=?,address=?, dateofbirth=?, email=? WHERE personalID=?";
					try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query)) {

						addStmt.setString(1, personalInfo.getName());
						addStmt.setString(2, personalInfo.getCbGender());
						addStmt.setInt(3, personalInfo.getPhone());
						addStmt.setString(4, personalInfo.getAddress());
						addStmt.setString(5, personalInfo.getDateOfBirth());
						addStmt.setString(6, personalInfo.getEmail());
						addStmt.setString(7, personalID);
						addStmt.executeUpdate();

					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					String query1 = "UPDATE patternBorrow SET readerName=? WHERE readerID=?";
					try (PreparedStatement addStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query1)) {

						addStmt.setString(1, personalInfo.getName());
						addStmt.setString(2, personalID);
						addStmt.executeUpdate();

					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					personalInfo.getTfName().setText("");
					personalInfo.getTfPhone().setText("");
					personalInfo.getTfAddress().setText("");
					personalInfo.getTfEmail().setText("");
				}
				// Update
				updateTable(personalID);

			}
		});
	}

	/**
	 * method update data when have changed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateTable(String personalID) {
		data = new Vector();
		String query = "select * from personalInfo;";
		try {
			statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				do {
					String id = rs.getString("personalID");
					if (id.equals(personalID)) {
						String name = rs.getString("name");
						String gender = rs.getString("gender");
						int phone = rs.getInt("phone");
						String address = rs.getString("address");
						String dateBirth = rs.getString("dateOfBirth");
						String email = rs.getString("email");
						Vector reader = new Vector();
						// reader.addElement(id);
						reader.addElement(name);
						reader.addElement(gender);
						reader.addElement(phone);
						reader.addElement(address);
						reader.addElement(dateBirth);
						reader.addElement(email);
						data.add(reader);
						break;
					}
				} while (rs.next());
			}
			((PersonalInformationView) personalInfo).refreshTable(data);
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}
	}
}
