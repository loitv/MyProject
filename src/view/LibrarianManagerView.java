package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.DateLabelFormatter;

public class LibrarianManagerView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lbID, lbName, lbGender, lbPhone, lbAddress, lbDateOfBirth, lbEmail;
	private JTextField tfID, tfName, tfPhone, tfAddress, tfEmail;
	private JComboBox<String> cbGender;
	private JTable librarianManegerInfo;
	private JButton btnAdd, btnEdit, btnDelete;
	private JDatePickerImpl datePicker;
	private UtilDateModel model;

	// constructor
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LibrarianManagerView() {
		super("Librarian Management");
		setSize(900, 450);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/libInfo.png")));
		setLayout(new BorderLayout(5, 5));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 2, 10, 10));
		lbID = new JLabel("Librarian ID");
		lbID.setForeground(Color.BLUE);
		lbName = new JLabel("Full Name");
		lbName.setForeground(Color.BLUE);
		lbGender = new JLabel("Gender");
		lbGender.setForeground(Color.BLUE);
		lbPhone = new JLabel("Phone");
		lbPhone.setForeground(Color.BLUE);
		lbAddress = new JLabel("Addredd");
		lbAddress.setForeground(Color.BLUE);
		lbDateOfBirth = new JLabel("Date of Birth");
		lbDateOfBirth.setForeground(Color.BLUE);
		lbEmail = new JLabel("Email");
		lbEmail.setForeground(Color.BLUE);

		tfName = new JTextField(15);
		tfID = new JTextField(15);
		tfPhone = new JTextField(15);
		tfAddress = new JTextField(15);
		tfEmail = new JTextField(15);

		cbGender = new JComboBox();
		cbGender.addItem("Male");
		cbGender.addItem("Female");

		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		panel1.add(lbID);
		panel1.add(tfID);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(lbName);
		panel1.add(tfName);
		panel1.add(lbGender);
		panel1.add(cbGender);
		panel1.add(lbPhone);
		panel1.add(tfPhone);
		panel1.add(lbAddress);
		panel1.add(tfAddress);
		panel1.add(lbDateOfBirth);
		panel1.add(datePicker);
		panel1.add(lbEmail);
		panel1.add(tfEmail);
		add(panel1, BorderLayout.NORTH);

		Vector colsName = new Vector();
		colsName.addElement("Librarian ID");
		colsName.addElement("Full Name");
		colsName.addElement("Gender");
		colsName.addElement("Phone");
		colsName.addElement("Address");
		colsName.addElement("Date of Birth");
		colsName.addElement("Email");
		Vector data = new Vector();

		librarianManegerInfo = new JTable(data, colsName);
		librarianManegerInfo.setFillsViewportHeight(true);
		JScrollPane scrollPanel = new JScrollPane(librarianManegerInfo);
		add(scrollPanel, BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 3, 5, 10));
		btnAdd = new JButton("Add");
		btnEdit = new JButton("Edit");
		btnDelete = new JButton("Delete");
		btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnAdd.png")));
		btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnEdit.png")));
		btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnDelete.png")));
		panel2.add(btnAdd);
		panel2.add(btnEdit);
		panel2.add(btnDelete);
		add(panel2, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	// GETTER METHOD
	public JTextField getTfName() {
		return tfName;
	}

	public JTextField getTfPhone() {
		return tfPhone;
	}

	public JTextField getTfAddress() {
		return tfAddress;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public JComboBox<String> getCbGender() {
		return cbGender;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}

	public JTextField getTfID() {
		return tfID;
	}

	public JTable getLibrarianManegerInfoTable() {
		return this.librarianManegerInfo;
	}

	// SET EVENTS LISTENER
	public void setTfLibIDDocumentListener(DocumentListener dl) {
		this.tfID.getDocument().addDocumentListener(dl);
	}

	public void setBtnAddAL(ActionListener al) {
		this.btnAdd.addActionListener(al);
	}

	public void setBtnEditAL(ActionListener al) {
		this.btnEdit.addActionListener(al);
	}

	public void setBtnDeleteAL(ActionListener al) {
		this.btnDelete.addActionListener(al);
	}

	public void setJTableMouseListener(MouseListener listener) {
		this.librarianManegerInfo.addMouseListener(listener);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = librarianManegerInfo.getModel();
		Vector cols = new Vector();
		cols.addElement("Librarian ID");
		cols.addElement("Full Name");
		cols.addElement("Gender");
		cols.addElement("Phone");
		cols.addElement("Address");
		cols.addElement("Date of Birth");
		cols.addElement("Email");
		model = new DefaultTableModel(list, cols);
		librarianManegerInfo.setModel(model);
		librarianManegerInfo.repaint();
	}

	public String getLibID() {
		return tfID.getText();
	}

	public String getName() {
		return tfName.getText();
	}

	public String getGender() {
		return (String) cbGender.getSelectedItem();
	}

	public int getPhone() {
		if (tfPhone.getText().equals("")) {
			return 0;
		} else {
			return Integer.parseInt(tfPhone.getText());
		}
	}

	public String getAddress() {
		return tfAddress.getText();
	}

	// get Date of Birth
	public String getDateOfBirth() {
		String year = Integer.toString(datePicker.getModel().getYear());
		String month = Integer.toString(datePicker.getModel().getMonth() + 1);
		String day = Integer.toString(datePicker.getModel().getDay());
		String date = year + "-" + month + "-" + day;
		return date;
	}

	public String getEmail() {
		return tfEmail.getText();
	}

	// get text field name, gender, phone, address, dateofBirth, email

	public static void main(String[] args) {
		new LibrarianManagerView();
	}
}
