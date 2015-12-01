package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.DateLabelFormatter;

public class PersonalInformationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbID, lbIDName, lbName, lbGender, lbPhone, lbAddress, lbDateOfBirth, lbEmail;
	private JTextField tfName, tfPhone, tfAddress, tfEmail;
	private JComboBox<String> cbGender;
	private JTable personalInfo;
	private JButton btnUpdate;
	private JDatePickerImpl datePicker;
	private UtilDateModel model;

	// constructor
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PersonalInformationView() {
		super("Personal Information");
		setSize(900, 450);
		setLayout(new BorderLayout(5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/perInfo1.png")));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 2, 10, 10));
		lbID = new JLabel("ID");
		lbID.setForeground(Color.BLUE);
		lbIDName = new JLabel();
		lbIDName.setForeground(Color.RED);
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
		tfPhone = new JTextField(15);
		tfAddress = new JTextField(15);
		tfEmail = new JTextField(15);
		
		cbGender = new JComboBox();
		cbGender.addItem("Male");
		cbGender.addItem("Female");
		
		model = new UtilDateModel();
		model.setDate(1993, 3, 17);
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		

		panel1.add(lbID);
		panel1.add(lbIDName);
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
		colsName.addElement("Name");
		colsName.addElement("Gender");
		colsName.addElement("Phone");
		colsName.addElement("Address");
		colsName.addElement("Date of Birth");
		colsName.addElement("Email");
		Vector data = new Vector();

		personalInfo = new JTable(data, colsName);
		personalInfo.setFillsViewportHeight(true);
		JScrollPane scrollPanel = new JScrollPane(personalInfo);
		add(scrollPanel, BorderLayout.CENTER);

		btnUpdate = new JButton("Update Information");
		btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnUpdate.png")));
		add(btnUpdate, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setBtnUpdateAL(ActionListener al) {
		this.btnUpdate.addActionListener(al);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = personalInfo.getModel();
		Vector cols = new Vector();
		cols.addElement("Name");
		cols.addElement("Gender");
		cols.addElement("Phone");
		cols.addElement("Address");
		cols.addElement("Date of Birth");
		cols.addElement("Email");
		model = new DefaultTableModel(list, cols);
		personalInfo.setModel(model);
		personalInfo.repaint();
	}

	public void setJTableMouseListener(MouseListener listener) {
		this.personalInfo.addMouseListener(listener);
	}

	public JTable getTable() {
		return this.personalInfo;
	}

	public String getName() {
		return tfName.getText();
	}

	public String getCbGender() {
		return (String)cbGender.getSelectedItem();
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
		String month = Integer.toString(datePicker.getModel().getMonth()+1);
		String day = Integer.toString(datePicker.getModel().getDay());
		String date = year+"-"+month+"-"+day;
		return date;
	}

	public String getEmail() {
		return tfEmail.getText();
	}

	public void setIDName(String lbIDName) {
		this.lbIDName.setText(lbIDName);
		;
	}

	// get text field name, gender, phone, address, dateofBirth, email
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
	public JLabel getLbIDName() {
		return lbIDName;
	}
	public JComboBox<String> getCbGen() {
		return cbGender;
	}
	public static void main(String[] args) {
		new PersonalInformationView();
	}
}
