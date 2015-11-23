package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BookStaticView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lbISBN, lbTotalBook;
	JTextField tfISBN, tfTotalBook;
	JButton enter;
	JTable bookStatic;

	public BookStaticView() {
		super("Check payable management");
		setSize(500, 540);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 4, 5, 10));
		lbISBN = new JLabel("ISBN");
		lbISBN.setForeground(Color.blue);
		lbTotalBook = new JLabel("Total Book");
		lbTotalBook.setForeground(Color.blue);
		tfISBN = new JTextField();
		tfTotalBook = new JTextField();
		panel1.add(lbISBN);
		panel1.add(tfISBN);
		panel1.add(lbTotalBook);
		panel1.add(tfTotalBook);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		add(panel1, BorderLayout.NORTH);

		enter = new JButton("ENTER");
		add(enter, BorderLayout.CENTER);

		Vector colName = new Vector();
		colName.addElement("ISBN");
		colName.addElement("Total Book");
		colName.addElement("Remain in store");
		Vector data = new Vector();
		bookStatic = new JTable(data, colName);
		JScrollPane panel3 = new JScrollPane(bookStatic);
		add(panel3, BorderLayout.SOUTH);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BookStaticView();
	}
}
