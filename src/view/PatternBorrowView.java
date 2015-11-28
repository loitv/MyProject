package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;

public class PatternBorrowView extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JLabel lbPatternID, lbBookID, lbBorrowingDate, lbReaderID, lbTitle, lbReturnDate, lbQuantity;
	JTextField tfPatternID,tfReaderID, tfBookID,tfTittle, tfQuantity, tfBorrowingDate , tfReturnDate ;

	JScrollPane scrollPanel = new JScrollPane();
	JTable BorrowbookDetails;

	JPanel panel3 = new JPanel();
	JButton btnAdd, btnEdit, btnUpdate;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PatternBorrowView() {
		super("Borrow Book Management");
		setSize(970, 600);
		setLayout(new BorderLayout(5, 5));

		lbPatternID = new JLabel("PatternID");
		lbPatternID.setForeground(Color.blue);
		lbBookID = new JLabel("BookID");
		lbBookID.setForeground(Color.blue);
		lbBorrowingDate = new JLabel("BorrowingDate");
		lbBorrowingDate.setForeground(Color.blue);
		lbReaderID = new JLabel("ReaderID");
		lbReaderID.setForeground(Color.blue);
		lbTitle = new JLabel("Tittle");
		lbTitle.setForeground(Color.blue);
		lbReturnDate = new JLabel("ReturnDate");
		lbReturnDate.setForeground(Color.blue);
		lbQuantity = new JLabel("Quantity");
		lbQuantity.setForeground(Color.blue);

		tfPatternID = new JTextField(30);
		tfReaderID = new JTextField(30);
		tfBookID = new JTextField(30);
		tfTittle = new JTextField(30);
		tfQuantity = new JTextField(30);
		tfBorrowingDate = new JTextField(30);
		tfReturnDate = new JTextField(30);
		

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 6, 5, 10));
		panel1.add(lbPatternID);
		panel1.add(tfPatternID);
		panel1.add(lbBookID);
		panel1.add(tfBookID);
		panel1.add(lbBorrowingDate);
		panel1.add(tfBorrowingDate);
		panel1.add(lbReaderID);
		panel1.add(tfReaderID);
		panel1.add(lbTitle);
		panel1.add(tfTittle);
		panel1.add(lbReturnDate);
		panel1.add(tfReturnDate);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(lbQuantity);
		panel1.add(tfQuantity);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());

		add(panel1, BorderLayout.NORTH);
		
		Vector colsName = new Vector();
		colsName.addElement("PatternID");
		colsName.addElement("ReaderID");
		colsName.addElement("ReaderName");
		colsName.addElement("BookID");
		colsName.addElement("Title");
		colsName.addElement("Quantity");
		colsName.addElement("BorrowingDate");
		colsName.addElement("ReturnDate");
		colsName.addElement("Deposit");
		
		Vector data = new Vector();
		
		BorrowbookDetails = new JTable(data, colsName);
		BorrowbookDetails.setPreferredScrollableViewportSize(new Dimension(500, 100));
		BorrowbookDetails.setFillsViewportHeight(true);
		scrollPanel = new JScrollPane(BorrowbookDetails);
		add(scrollPanel, BorderLayout.CENTER);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 3, 5, 10));
		btnAdd = new JButton("Add");
		btnEdit = new JButton("Edit");
		btnUpdate = new JButton("Update");
		panel3.add(btnAdd);
		panel3.add(btnEdit);
		panel3.add(btnUpdate);
		add(panel3, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new PatternBorrowView();
	}

}
