package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("unused")
public class AllBookView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable bookDetail;
	private JLabel lbCategory;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCategory;
	private JScrollPane scrollPane;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AllBookView() {

		setLayout(new BorderLayout(10, 20));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		lbCategory = new JLabel("Category");
		cbCategory = new JComboBox<String>();
		cbCategory.addItem("All");
		cbCategory.setPreferredSize(new Dimension(140, 20));
		panel1.add(lbCategory, FlowLayout.LEFT);
		panel1.add(cbCategory);
		panel1.add(new JLabel("                                                 "));
		panel1.add(new JLabel("                                                 "));
		panel1.add(new JLabel("                                                 "));
		panel1.add(new JLabel("                                                 "));
		panel1.add(new JLabel("                                                 "));
		add(panel1, BorderLayout.NORTH);

		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Book Name");
		cols.addElement("Author");
		cols.addElement("Category");
		cols.addElement("Price");
//		cols.addElement("Quantity");
		Vector bookData = new Vector();
		bookDetail = new JTable(bookData, cols);

		bookDetail.setPreferredScrollableViewportSize(new Dimension(500, 100));
		bookDetail.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(bookDetail);
		add(scrollPane, BorderLayout.CENTER);

		setTitle("ALL BOOKS");

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/allBook.jpg")));
		setSize(970, 600);
		dispose();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void addItemsToComboBox(String name) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbCategory.getModel();
		model.addElement(name);
	}

	public void setItemChangeListener(ItemListener il) {
		this.cbCategory.addItemListener(il);
	}

	public String getSelectedID() {
		return (String) cbCategory.getSelectedItem();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = bookDetail.getModel();
		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Book Name");
		cols.addElement("Author");
		cols.addElement("Category");
		cols.addElement("Price");
		model = new DefaultTableModel(list, cols);
		bookDetail.setModel(model);
		bookDetail.repaint();
	}

	public static void main(String[] args) {
		new AllBookView();
	}

}
