package view;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class BookView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JLabel lbISBN, lbTitle, lbAuthorID, lbCategory, lbPrice, lbQuantity;
	JTextField tfISBN, tfTitle, tfAuthorID, tfCategory, tfPrice, tfQuantity;

	JScrollPane scrollPanel = new JScrollPane();
	JTable bookDetails;

	JPanel panel3 = new JPanel();
	JButton btnAdd, btnEdit, btnDelete;

	public BookView() {
		super("Book Management");
		setSize(970, 600);
		setLayout(new BorderLayout());

		lbISBN = new JLabel("ISBN");
		lbISBN.setForeground(Color.blue);
		lbTitle = new JLabel("Title");
		lbTitle.setForeground(Color.blue);
		lbAuthorID = new JLabel("Author ID");
		lbAuthorID.setForeground(Color.blue);
		lbCategory = new JLabel("Category");
		lbCategory.setForeground(Color.blue);
		lbPrice = new JLabel("Price");
		lbPrice.setForeground(Color.blue);
		lbQuantity = new JLabel("Quantity");
		lbQuantity.setForeground(Color.blue);

		tfISBN = new JTextField(36);
		tfTitle = new JTextField(36);
		tfPrice = new JTextField(36);
		tfAuthorID = new JTextField(36);
		tfCategory = new JTextField(36);
		tfQuantity = new JTextField(36);

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 6, 5, 10));
		panel1.add(lbISBN);
		panel1.add(tfISBN);
		panel1.add(lbTitle);
		panel1.add(tfTitle);
		panel1.add(lbPrice);
		panel1.add(tfPrice);
		panel1.add(lbAuthorID);
		panel1.add(tfAuthorID);
		panel1.add(lbCategory);
		panel1.add(tfCategory);
		panel1.add(lbQuantity);
		panel1.add(tfQuantity);
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());

		add(panel1, BorderLayout.NORTH);
		
		Vector<String> colsName = new Vector<String>();
		colsName.addElement("ISBN");
		colsName.addElement("Tittle");
		colsName.addElement("Author ID");
		colsName.addElement("Category");
		colsName.addElement("Price");
		colsName.addElement("Quantity");
		Vector data = new Vector();
		
		bookDetails = new JTable(data, colsName);
		bookDetails.setPreferredScrollableViewportSize(new Dimension(500, 100));
		bookDetails.setFillsViewportHeight(true);
		scrollPanel = new JScrollPane(bookDetails);
		add(scrollPanel, BorderLayout.CENTER);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 3, 5, 10));
		btnAdd = new JButton("Add");
		btnEdit = new JButton("Edit");
		btnDelete = new JButton("Delete");
		panel3.add(btnAdd);
		panel3.add(btnEdit);
		panel3.add(btnDelete);
		add(panel3, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void main(String[] args) {
		new BookView();
	}
}
