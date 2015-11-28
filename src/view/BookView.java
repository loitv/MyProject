package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.xswingx.*;

public class BookView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JLabel lbISBN, lbTitle, lbAuthor, lbCategory, lbPrice, lbQuantity;
	JTextField tfISBN, tfTitle, tfAuthor, tfCategory, tfPrice, tfQuantity;

	JScrollPane scrollPanel = new JScrollPane();
	JTable bookDetails;

	JPanel panel3 = new JPanel();
	JButton btnAdd, btnEdit, btnDelete;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BookView() {
		super("Book Management");
		setSize(970, 600);
		setLayout(new BorderLayout(5, 5));

		lbISBN = new JLabel("ISBN");
		lbISBN.setForeground(Color.blue);
		lbTitle = new JLabel("Title");
		lbTitle.setForeground(Color.blue);
		lbAuthor = new JLabel("Author");
		lbAuthor.setForeground(Color.blue);
		lbCategory = new JLabel("Category");
		lbCategory.setForeground(Color.blue);
		lbPrice = new JLabel("Price");
		lbPrice.setForeground(Color.blue);
		lbQuantity = new JLabel("Quantity");
		lbQuantity.setForeground(Color.blue);

		tfISBN = new JTextField(36);
		tfTitle = new JTextField(36);
		tfPrice = new JTextField(36);
		tfAuthor = new JTextField(36);
		tfCategory = new JTextField(36);
		tfQuantity = new JTextField(36);
		PromptSupport.setPrompt("Only for button ADD", tfQuantity);

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 6, 5, 10));
		panel1.add(lbISBN);
		panel1.add(tfISBN);
		panel1.add(lbTitle);
		panel1.add(tfTitle);
		panel1.add(lbPrice);
		panel1.add(tfPrice);
		panel1.add(lbAuthor);
		panel1.add(tfAuthor);
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
		
		Vector colsName = new Vector();
		colsName.addElement("ISBN");
		colsName.addElement("Tittle");
		colsName.addElement("Author");
		colsName.addElement("Category");
		colsName.addElement("Price");
		colsName.addElement("Total Book");
		colsName.addElement("Remain Book");
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

		setLocationRelativeTo(null);
		setVisible(true);
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
	public void setBookDetailsML(MouseListener ml) {
		this.bookDetails.addMouseListener(ml);
	}
	
	
	////////////////////////////////
	public int getISBN() {
		if (tfISBN.getText().equals("")) {
			return 0;
		} else {
			return Integer.parseInt(tfISBN.getText());
		}
	}
	public String getTitle() {
		return tfTitle.getText();
	}
	public String getAuthor() {
		return tfAuthor.getText();
	}
	public String getCategory() {
		return tfCategory.getText();
	}
	public double getPrice() {
		if (tfPrice.getText().equals("")) {
			return 0;
		} else {
			return Double.parseDouble(tfPrice.getText());
		}
	}
	public int getQuantity() {
		if (tfQuantity.getText().equals("")) {
			return 0;
		} else {
			return Integer.parseInt(tfQuantity.getText());
		}
	}

	/////////////////////////////////////////////////////////
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = bookDetails.getModel();
		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Title");
		cols.addElement("Author");
		cols.addElement("Category");
		cols.addElement("Price");
		cols.addElement("Total");
		cols.addElement("Remain");
		model = new DefaultTableModel(list, cols);
		bookDetails.setModel(model);
		bookDetails.repaint();
	}
	
	//////////////////////////////////////////////////
	public JTextField getTfQuantity() {
		return tfQuantity;
	}

	
	//////////////////////////////////////
	public static void main(String[] args) {
		new BookView();
	}

	///////////////////////////////////////////
	public JTextField getTfISBN() {
		return tfISBN;
	}

	public JTextField getTfTitle() {
		return tfTitle;
	}

	public JTextField getTfAuthor() {
		return tfAuthor;
	}

	public JTextField getTfCategory() {
		return tfCategory;
	}

	public JTextField getTfPrice() {
		return tfPrice;
	}

	public JTable getBookDetails() {
		return bookDetails;
	}
}
