package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.DateLabelFormatter;

public class PatternBorrowView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lbPatternID, lbISBN, lbBorrowingDate, lbReaderID, lbReaderName, lbTitle,lbPrice, lbReturnDate;
	private JTextField tfPatternID, tfReaderID, tfReaderName, tfISBN, tfTittle, tfPrice;

	private JTable paternBorrowDetails;

	private JButton btnAdd, btnDelete, btnCreatePattern, btnRemoveBook;

	private JDatePickerImpl datePickerBorrow, datePickerReturn;
	private UtilDateModel model1, model2;
	private Properties property;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PatternBorrowView() {
		super("Borrowing Book Management");
		setSize(1270, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/createpattern1.png")));
		setLayout(new BorderLayout(5, 5));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 6, 5, 10));
		lbPatternID = new JLabel("Pattern ID");
		lbPatternID.setForeground(Color.blue);
		lbISBN = new JLabel("ISBN");
		lbISBN.setForeground(Color.blue);
		lbBorrowingDate = new JLabel("Borrowing Date");
		lbBorrowingDate.setForeground(Color.blue);
		lbReaderID = new JLabel("Reader ID");
		lbReaderID.setForeground(Color.blue);
		lbReaderName = new JLabel("Reader Name");
		lbReaderName.setForeground(Color.blue);
		lbTitle = new JLabel("Tittle");
		lbTitle.setForeground(Color.blue);
		lbReturnDate = new JLabel("Return Date");
		lbReturnDate.setForeground(Color.blue);
		lbPrice = new JLabel("Price");
		lbPrice.setForeground(Color.BLUE);

		tfPatternID = new JTextField(30);
		tfReaderID = new JTextField(30);
		tfISBN = new JTextField(30);
		tfTittle = new JTextField(30);
		tfReaderName = new JTextField(30);
		tfPrice = new JTextField(30);

		property = new Properties();
		property.put("text.today", "Today");
		model1 = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model1, property);
		datePickerBorrow = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, property);
		datePickerReturn = new JDatePickerImpl(datePanel2, new DateLabelFormatter());

		panel1.add(lbPatternID);
		panel1.add(tfPatternID);
		panel1.add(lbISBN);
		panel1.add(tfISBN);
		panel1.add(lbBorrowingDate);
		panel1.add(datePickerBorrow);
		panel1.add(lbReaderID);
		panel1.add(tfReaderID);
		panel1.add(lbTitle);
		panel1.add(tfTittle);
		panel1.add(lbReturnDate);
		panel1.add(datePickerReturn);
		panel1.add(lbReaderName);
		panel1.add(tfReaderName);
		panel1.add(lbPrice);
		panel1.add(tfPrice);
//		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		add(panel1, BorderLayout.NORTH);

		Vector colsName = new Vector();
		colsName.addElement("Pattern ID");
		colsName.addElement("Reader ID");
		colsName.addElement("Reader Name");
		colsName.addElement("ISBN");
		colsName.addElement("Title");
		colsName.addElement("Borrowing Date");
		colsName.addElement("Return Date");
		colsName.addElement("Deposit");

		Vector data = new Vector();

		paternBorrowDetails = new JTable(data, colsName);
		paternBorrowDetails.setPreferredScrollableViewportSize(new Dimension(500, 100));
		paternBorrowDetails.setFillsViewportHeight(true);
		JScrollPane scrollPanel = new JScrollPane(paternBorrowDetails);
		add(scrollPanel, BorderLayout.CENTER);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 3, 5, 10));
		btnAdd = new JButton("Add book");
		btnDelete = new JButton("Delete Pattern");
		btnCreatePattern = new JButton("Create New Pattern");
		btnRemoveBook = new JButton("Remove book");
		btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnAdd1.png")));
		btnRemoveBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnRemove.png")));
		btnCreatePattern.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnAdd.png")));
		btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnDelete.png")));
		panel3.add(btnAdd);
		panel3.add(btnRemoveBook);
		panel3.add(btnCreatePattern);
		panel3.add(btnDelete);
		add(panel3, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setTfPatternIDChangeDL(DocumentListener dl) {
		this.tfPatternID.getDocument().addDocumentListener(dl);
	}
	
	public void setTfReaderIDContentChange(DocumentListener dl) {
		this.tfReaderID.getDocument().addDocumentListener(dl);
	}

	public void setTFISBNChangeAL(DocumentListener dl) {
		this.tfISBN.getDocument().addDocumentListener(dl);
	}

	public void setBtnAddAL(ActionListener al) {
		this.btnAdd.addActionListener(al);
	}

	public void setBtnDeleteAL(ActionListener al) {
		this.btnDelete.addActionListener(al);
	}

	public void setBtnCreatePatternAL(ActionListener al) {
		this.btnCreatePattern.addActionListener(al);
	}
	public void setBtnRemoveBookAL(ActionListener al) {
		this.btnRemoveBook.addActionListener(al);
	}

	public void setPaternBorrowDetailsML(MouseListener ml) {
		this.paternBorrowDetails.addMouseListener(ml);
	}
	public void setBorrowDateActionListener(ActionListener al) {
		this.datePickerBorrow.addActionListener(al);
	}
	

	///////////////////////////////////////
	//// get content from textField///////////
	@SuppressWarnings("finally")
	public int getPatternID() {
		int a = 0;
		if (tfPatternID.getText().equals("")) {
			return 0;
		} else {
			try {
				a= Integer.parseInt(tfPatternID.getText());
				
			}catch(Exception ex) {
				System.out.println("Invalid input string!");
			}
			finally {
				return a;
			}
		}
	}
	public String getReaderID() {
		return tfReaderID.getText();
	}

	public String getReaderName() {
		return tfReaderName.getText();
	}
	@SuppressWarnings("finally")
	public int getISBN() {
		int a = 0;
		if (tfISBN.getText().equals("")) {
			return 0;
		} else {
			try {
				a= Integer.parseInt(tfISBN.getText());
				
			}catch(Exception ex) {
				System.out.println("Invalid input string!");
			}
			finally {
				return a;
			}
		}
	}

	public String getTitleBook() {
		return tfTittle.getText();
	}

	public double getPrice() {
		if (tfPrice.getText().equals("")) {
			return 0;
		} else {
			return Double.parseDouble(tfPrice.getText());
		}
	}

	public String getBorrowDate() {
		String year = Integer.toString(datePickerBorrow.getModel().getYear());
		String month = Integer.toString(datePickerBorrow.getModel().getMonth() + 1);
		String day = Integer.toString(datePickerBorrow.getModel().getDay());
		String date = year + "-" + month + "-" + day;
		return date;
	}

	public String getReturnDate() {
		String year = Integer.toString(datePickerReturn.getModel().getYear());
		String month = Integer.toString(datePickerReturn.getModel().getMonth() + 1);
		String day = Integer.toString(datePickerReturn.getModel().getDay());
		String date = year + "-" + month + "-" + day;
		return date;
	}

	//////////////////////////////////////
	///// Get TextField/////////////////
	public JTextField getTfPatternID() {
		return tfPatternID;
	}

	public JTextField getTfReaderID() {
		return tfReaderID;
	}

	public JTextField getTfReaderName() {
		return tfReaderName;
	}

	public JTextField getTfISBN() {
		return tfISBN;
	}

	public JTextField getTfTittle() {
		return tfTittle;
	}

//	public JTextField getTfQuantity() {
//		return tfQuantity;
//	}
	public JTextField getTfPrice() {
		return tfPrice;
	}
	/////////////////////////////
	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnCreatePattern() {
		return btnCreatePattern;
	}
	public JButton getBtnRemove() {
		return btnRemoveBook;
	}

	

	////////////////////////////////
	/// FOR CALENDAR/////////////////
	public UtilDateModel getModel1() {
		return model1;
	}
	public UtilDateModel getModel2() {
		return model2;
	}
	public void setReturnDatePicker(JDatePickerImpl datePicker2) {
		this.datePickerReturn = datePicker2;
	}

	public void setDatePickerReturn(JDatePickerImpl datePickerReturn) {
		this.datePickerReturn = datePickerReturn;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = paternBorrowDetails.getModel();
		Vector colsName = new Vector();
		colsName.addElement("Pattern ID");
		colsName.addElement("Reader ID");
		colsName.addElement("Reader Name");
		colsName.addElement("ISBN");
		colsName.addElement("Title");
		colsName.addElement("Borrowing Date");
		colsName.addElement("Return Date");
		colsName.addElement("Deposit");
		model = new DefaultTableModel(list, colsName);
		paternBorrowDetails.setModel(model);
		paternBorrowDetails.repaint();
	}

	public static void main(String[] args) {
		new PatternBorrowView();
	}
}
