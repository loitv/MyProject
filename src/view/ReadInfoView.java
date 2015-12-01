package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ReadInfoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbReaderID;
	private JComboBox<String> cbReaderID;
	private JButton btnBorInfo;
	private JTable readerInfo;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReadInfoView() {
		super("Reader Information");
		setSize(1100, 550);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/perinfo.png")));
		setLayout(new BorderLayout(5, 10));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 4, 5, 10));
		lbReaderID = new JLabel("Reader ID");
		lbReaderID.setForeground(Color.BLUE);
		cbReaderID = new JComboBox<String>();
		cbReaderID.addItem("All");
		btnBorInfo = new JButton("Reader Borrowing Information");
		btnBorInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/btnBorInfo.png")));
		panel1.add(lbReaderID);
		panel1.add(cbReaderID);
		panel1.add(btnBorInfo);
		panel1.add(new JLabel());
		add(panel1, BorderLayout.NORTH);

		Vector colsName = new Vector();
		colsName.addElement("Reader ID");
		colsName.addElement("Full Name");
		colsName.addElement("Gender");
		colsName.addElement("Phone");
		colsName.addElement("Address");
		colsName.addElement("Date of Birth");
		colsName.addElement("Email");
		Vector data = new Vector();

		readerInfo = new JTable(data, colsName);
		readerInfo.setPreferredScrollableViewportSize(new Dimension(500, 100));
		readerInfo.setFillsViewportHeight(true);
		JScrollPane scrollPanel = new JScrollPane(readerInfo);
		add(scrollPanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JButton getBtnBorInfo() {
		return btnBorInfo;
	}

	public void setBtnBorInfoAL(ActionListener al) {
		this.btnBorInfo.addActionListener(al);
	}

	public void setReaderInfoML(MouseListener ml) {
		this.readerInfo.addMouseListener(ml);
	}
	
	public void addItemToComboBox(String name) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbReaderID.getModel();
		model.addElement(name);
	}
	public void setIDChangeListener(ItemListener il) {
		this.cbReaderID.addItemListener(il);
	}
	
	public String getSelectedID() {
		return (String) cbReaderID.getSelectedItem();
	}
	public JTable getReaderInfoTable() {
		return readerInfo;
	}
	public JComboBox<String> getCbReaderID() {
		return cbReaderID;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = readerInfo.getModel();
		Vector cols = new Vector();
		cols.addElement("Reader ID");
		cols.addElement("Full Name");
		cols.addElement("Gender");
		cols.addElement("Phone");
		cols.addElement("Address");
		cols.addElement("Date of Birth");
		cols.addElement("Email");
		model = new DefaultTableModel(list, cols);
		readerInfo.setModel(model);
		readerInfo.repaint();
	}

	public static void main(String[] args) {
		new ReadInfoView();
	}
}
