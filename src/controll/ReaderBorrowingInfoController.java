package controll;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

		// handle events when changing selected item from combobox
		readerbiv.setIDChangeListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int patternID = Integer.parseInt(readerbiv.getCbPatternID().getSelectedItem().toString());
				updateInfo(patternID);
			}
		});

		// handle events for button Export Pattern
		readerbiv.setexportPatternActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Tran\\Desktop\\page1.pdf"));
					document.open();
					document.add(new Paragraph(
							"                                                                                                                                              To 1/2  "));

					document.add(new Paragraph("Ta Quang Buu Library",
							FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD, BaseColor.RED)));
					document.add(new Paragraph("Address: No. 1, Dai Co Viet Str., Hai Ba Trung district, Ha Noi City"));
					document.add(new Paragraph("Phone: 04(3) 345 678"));
					document.add(new Paragraph(
							"-------------------------------------------------------------------------------------------------"));
					document.add(new Paragraph("BORROWING PATTERN OF BOOK ",
							FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD, BaseColor.BLUE)));
					document.add(new Paragraph(new java.util.Date().toString()));
					document.add(new Paragraph(
							"                                                                                     "));
					String patternID = "Pattern ID:  " + readerbiv.getCbPatternID().getSelectedItem().toString();
					Paragraph patternID1 = new Paragraph(patternID,
							FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD));
					document.add(new Paragraph(patternID1));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph("Reader ID: " + readerbiv.getLbReaderID2().getText() + "            " + "Reader Name: "
							+ readerbiv.getLbReaderName1().getText()));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph("Borrowing Date: "  +readerbiv.getTfBorrowDate().getText() + "            Returning Date: "+
							readerbiv.getTfReturnDate().getText() + "           Deposit: "+readerbiv.getTfDeposit().getText()));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph("List of Books:"));
					document.add(new Paragraph(
							"                                                                                     "));
					int n = readerbiv.getPatternInfo().getRowCount();
					PdfPTable table = new PdfPTable(3);// column number
					PdfPCell cell = new PdfPCell();
					cell.setColspan(3);
					cell.setBorderColor(BaseColor.WHITE);
					table.addCell(cell);
					table.addCell("No.");
					table.addCell("ISBN");
					table.addCell("Title");
					for (int i = 0; i < n; i ++) {
						table.addCell(Integer.toString(i+1));
						table.addCell(readerbiv.getPatternInfo().getValueAt(i, 0).toString());
						table.addCell(readerbiv.getPatternInfo().getValueAt(i, 1).toString());
						
					}
					document.add(table);
					com.itextpdf.text.List list = new com.itextpdf.text.List();

					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph("Thank You!"));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph("              "));
					document.add(new Paragraph("                                                   "));
					document.add(new Paragraph(
							"                                                                                     "));
					document.add(new Paragraph(
							"                                                                             "));

					document.add(new Paragraph(
							"                      Librarian                                                                            Reader          "));
					document.add(new Paragraph(
							"                      (Signature)                                                                       (Signature)        "));

					document.add(list);

					document.close();
					JOptionPane.showMessageDialog(null, "Borrowing Pattern is exported to C:\\Users\\Tran\\Desktop\\page1");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
		new ReaderBorrowingInfoController("abc");
	}
}
