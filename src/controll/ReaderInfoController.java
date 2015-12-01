package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		updateTable("select * from personalInfo where type = 3;");

		// Add items into ComboBox

		try {
			String query = "select DISTINCT personalID from personalInfo;";
			Statement statement = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD");
			} else {
				// display result if not empty
				do {
					String id = rs.getString("personalID");
					if (id.equalsIgnoreCase("Librarian") || id.equalsIgnoreCase("Admin")) {
						// System.out.println("Bo qua");
					} else {
						readeriv.addItemToComboBox(id);
					}
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
					}
					data = new Vector();
					updateTable(query); // Update Book information on table
				}
			}
		});
		
		//handle event when click on the table
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
				if (n!=-1) {
					id = readeriv.getReaderInfoTable().getValueAt(n, 0).toString();
					readeriv.getBtnBorInfo().setEnabled(true);
				}
			}
		});
		
		readeriv.setBtnBorInfoAL(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				System.out.println(id);
				new ReaderBorrowingInfoController(id);
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
