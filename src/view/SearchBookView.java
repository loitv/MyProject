package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author CST The TimKiemSach class display the form TimKiemSach the user
 *         manipulation on interface
 */
@SuppressWarnings("serial")
public class SearchBookView extends JFrame {
	private JTextField tfBook;
	private JButton btnSearch;
	private JLabel lbBook;
	private JTable searchBook;
	private JComboBox<String> cbCategory;
	private JLabel lbCategory;

	/**
	 * the constructor,create interface Search Book,
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SearchBookView() {
		JPanel panel = new JPanel(new FlowLayout());
		lbBook = new JLabel("Enter Book Name");
		lbBook.setForeground(Color.blue);
		tfBook = new JTextField(15);
		btnSearch = new JButton("Search");
		btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/c1.png")));
		lbCategory = new JLabel("Category");
		lbCategory.setForeground(Color.blue);
		cbCategory = new JComboBox<String>();
		cbCategory.setPreferredSize(new Dimension(140, 20));

		panel.add(lbBook);
		panel.add(tfBook);
		panel.add(btnSearch);
		panel.add(new JLabel("                                                       "));
		panel.add(lbCategory);
		panel.add(cbCategory);
		Vector colsName = new Vector();
		colsName.addElement("ISBN");
		colsName.addElement("Book Name");
		colsName.addElement("Author");
		colsName.addElement("Category");
		colsName.addElement("Price");
//		colsName.addElement("Quantity");
		Vector data = new Vector();
		searchBook = new JTable(data, colsName);
		searchBook.setPreferredScrollableViewportSize(new Dimension(500, 100));
		searchBook.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(searchBook);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout(5, 5));
		cp.add(panel, BorderLayout.NORTH);
		cp.add(scrollPane, BorderLayout.CENTER);

		setTitle("SEARCH BOOK");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/searchBook.png")));
		setSize(970, 600);
		dispose();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	public void setBtnSearchAL(ActionListener al) {
		this.btnSearch.addActionListener(al);
	}

	public void setTfBookAL(ActionListener al) {
		this.tfBook.addActionListener(al);
	}

	public void addItemToComboBox(String name) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbCategory.getModel();
		model.addElement(name);
	}
	
	public String getInputBookName() {
		return tfBook.getText();
	}
	
	public void setItemChangeListener(ItemListener il) {
		this.cbCategory.addItemListener(il);
	}
	
	public String getSelectedID() {
		return (String) cbCategory.getSelectedItem();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = searchBook.getModel();
		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Book Name");
		cols.addElement("Author");
		cols.addElement("Category");
		cols.addElement("Price");
//		cols.addElement("Quantity");
		model = new DefaultTableModel(list, cols);
		searchBook.setModel(model);
		searchBook.repaint();
	}

	public static void main(String[] args) {
		new SearchBookView();
	}

}
