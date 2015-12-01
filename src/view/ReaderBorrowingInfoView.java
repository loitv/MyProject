package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//import javafx.scene.paint.Color;

public class ReaderBorrowingInfoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbReaderID1, lbReaderID2, lbPatternID, lbBorrowDate, lbReturndate, lbDeposit, lbBook, lbReaderName, lbReaderName1;
	private JTextField tfBorrowDate, tfReturnDate, tfDeposit;
	private JComboBox<Integer> cbPatternID;
	private JTable patternInfo;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReaderBorrowingInfoView() {
		super("Reader Borrowing Information");
		setSize(750, 550);
		setLayout(new BorderLayout(5, 10));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 4, 5, 10));
		lbReaderID1 = new JLabel("Reader ID:");
		lbReaderID1.setForeground(Color.BLUE);
		lbReaderID2 = new JLabel();
		lbPatternID = new JLabel("Pattern ID");
		lbPatternID.setForeground(Color.BLUE);
		lbReaderName = new JLabel("Reader Name:");
		lbReaderName.setForeground(Color.BLUE);
		lbReaderName1 = new JLabel();
		lbReaderName1.setForeground(Color.BLUE);
		cbPatternID = new JComboBox<Integer>();
		lbBorrowDate = new JLabel("Borrowing Date");
		lbBorrowDate.setForeground(Color.BLUE);
		lbReturndate = new JLabel("Returning Date");
		lbReturndate.setForeground(Color.BLUE);
		lbDeposit = new JLabel("Deposit");
		lbDeposit.setForeground(Color.BLUE);
		lbBook = new JLabel("BOOKS");
		lbBook.setForeground(Color.BLUE);
		// lbBook.setFont(calibri);;
		tfBorrowDate = new JTextField();
		tfReturnDate = new JTextField();
		tfDeposit = new JTextField();
//		tfReaderName = new JTextField();
		panel1.add(lbReaderID1);
		panel1.add(lbReaderID2);
		panel1.add(lbReaderName);
		panel1.add(lbReaderName1);
		panel1.add(lbPatternID);
		panel1.add(cbPatternID);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(lbBorrowDate);
		panel1.add(lbReturndate);
		panel1.add(lbDeposit);
		panel1.add(new JLabel());
		panel1.add(tfBorrowDate);
		panel1.add(tfReturnDate);
		panel1.add(tfDeposit);
		panel1.add(new JLabel());
		panel1.add(lbBook);
		add(panel1, BorderLayout.NORTH);

		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Title");
//		cols.addElement("Price");
		Vector data = new Vector();
		// patternInfo.add(comp, index)
		patternInfo = new JTable(data, cols);
		patternInfo.setPreferredScrollableViewportSize(new Dimension(500, 100));
		patternInfo.setFillsViewportHeight(true);
		JScrollPane scrollPanel = new JScrollPane(patternInfo);
		add(scrollPanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	// GETTER METHODS
	public JLabel getLbReaderID2() {
		return lbReaderID2;
	}
	public JLabel getLbReaderName1() {
		return lbReaderName1;
	}

	public JTextField getTfBorrowDate() {
		return tfBorrowDate;
	}

	public JTextField getTfReturnDate() {
		return tfReturnDate;
	}

	public JTextField getTfDeposit() {
		return tfDeposit;
	}

	public JComboBox<Integer> getCbPatternID() {
		return cbPatternID;
	}

	public JTable getPatternInfo() {
		return patternInfo;
	}
	
	//SET ACTION LISTENER
	public void setIDChangeListener(ItemListener il) {
		this.cbPatternID.addItemListener(il);
	}
	
	public void addItemToComboBox(int patternID) {
		DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) cbPatternID.getModel();
		model.addElement(patternID);
	}
	
	//REFRESH TABLE
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = patternInfo.getModel();
		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Title");
		model = new DefaultTableModel(list, cols);
		patternInfo.setModel(model);
		patternInfo.repaint();
	}

	public static void main(String[] args) {
		new ReaderBorrowingInfoView();
	}
}
