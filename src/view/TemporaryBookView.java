package view;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TemporaryBookView extends JFrame{

	private JTable temporaryBook;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	Vector data;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TemporaryBookView() {
		super("Temporary Books");
		Vector colName = new Vector();
		colName.addElement("ISBN");
		colName.addElement("Title");
		colName.addElement("Price");
		Vector data = new Vector();
		temporaryBook = new JTable(data, colName);
		temporaryBook.setPreferredScrollableViewportSize(new Dimension(500, 100));
		temporaryBook.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(temporaryBook);
		add(scrollPane);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refreshTable(Vector list) {
		TableModel model = temporaryBook.getModel();
		Vector cols = new Vector();
		cols.addElement("ISBN");
		cols.addElement("Title");
		cols.addElement("Price");
		model = new DefaultTableModel(list, cols);
		temporaryBook.setModel(model);
		temporaryBook.repaint();
	}
	
	public void closeForm() {
		super.dispose();

	}
	
	public static void main(String[] args) {
		new TemporaryBookView();
	}
}
