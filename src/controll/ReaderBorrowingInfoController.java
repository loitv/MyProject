package controll;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import view.ReaderBorrowingInfoView;

public class ReaderBorrowingInfoController {

	private ReaderBorrowingInfoView readerbiv;
	@SuppressWarnings("rawtypes")
	private Vector data;
	private Statement stmt;

	// CONSTRUCTOR
	public ReaderBorrowingInfoController(String readerID) {
		readerbiv = new ReaderBorrowingInfoView();
		readerbiv.getLbReaderID2().setText(readerID);
		readerbiv.getLbReaderID2().setForeground(Color.red);
		String query = String.format("select * from patternborrow where readerID = '%s';", readerID);
		try {
			stmt = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD3");
			} else {
				do {
					int patternID = rs.getInt("patternID");
					readerbiv.getCbPatternID().addItem(patternID);
				} while (rs.next());
				int patternID = Integer.parseInt(readerbiv.getCbPatternID().getSelectedItem().toString());
				updateInfo(patternID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// int patternID =
		// Integer.parseInt(readerbiv.getCbPatternID().getSelectedItem().toString());
		// updateInfo(patternID);

		// handle events when changing selected item from combobox
		readerbiv.setIDChangeListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int patternID = Integer.parseInt(readerbiv.getCbPatternID().getSelectedItem().toString());
				updateInfo(patternID);
			}
		});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateInfo(int patternID) {
		data = new Vector();
		String query1 = String.format("select * from pattern_book where patternID = '%s';", patternID);
		String query2 = String.format("select * from patternborrow where patternID = '%s';", patternID);
		try {
			stmt = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD1");
			} else {
				do {
					int isbn = rs.getInt("isbn");
					String title = rs.getString("title");
					Vector bookDetail = new Vector();
					bookDetail.addElement(isbn);
					bookDetail.addElement(title);
					data.add(bookDetail);
				} while (rs.next());
			}
			readerbiv.refreshTable(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			stmt = controll.ConnectDatabase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query2);
			if (!rs.first()) {
				JOptionPane.showMessageDialog(null, "HAVE NO RECORD2");
			} else {
				do {
					String readerName = rs.getString("readerName");
					String borrowDate = rs.getString("borrowDate");
					String returnDate = rs.getString("returnDate");
					double deposit = rs.getDouble("deposit");

					readerbiv.getLbReaderName1().setText(readerName);
					readerbiv.getLbReaderName1().setForeground(Color.RED);
					readerbiv.getTfBorrowDate().setText(borrowDate);
					readerbiv.getTfReturnDate().setText(returnDate);
					readerbiv.getTfDeposit().setText(Double.toString(deposit));
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ReaderBorrowingInfoController("cr7");
	}
}
