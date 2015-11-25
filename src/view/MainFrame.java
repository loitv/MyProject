package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem login, signUp, logout, searchBook, allBook;

	public MainFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lb = new JLabel();
		lb.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/main.jpg")));
		panel.add(lb, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menu = new JMenu("Account");
		login = new JMenuItem("Login");
		signUp = new JMenuItem("Sign up");
		menu.add(login);
		menu.add(signUp);
		menuBar.add(menu);
		
		menu = new JMenu("Book");
		searchBook = new JMenuItem("Search Book");
		menu.add(searchBook);
		allBook = new JMenuItem("All Books");
		menu.add(allBook);
		menuBar.add(menu);
		
		menu = new JMenu("Management");
		menuBar.add(menu);
		
		menu = new JMenu("Help");
		JMenuItem aboutUs = new JMenuItem("About Us");
		menu.add(aboutUs);
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout(5, 5));
		cp.add(panel, BorderLayout.CENTER);

		setTitle("MANAGEMENT OF LIBRARY BOOKS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/Icon/b5.png")));
		setSize(970, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public MainFrame(int number) {
		
	}
	
	public void setLoginActionListener(ActionListener listener) {
		this.login.addActionListener(listener);
	}
	
	public void setSignUpActionListener (ActionListener listener) {
		this.signUp.addActionListener(listener);
	}
	
	public void setLogoutAL(ActionListener ls) {
		this.logout.addActionListener(ls);
	}
	
	public void setSeachBookAL(ActionListener al) {
		this.searchBook.addActionListener(al);
	}
	
	public void setAllBookAL(ActionListener al) {
		this.allBook.addActionListener(al);
	}
	
	// Add user's name into Menu bar
	public void addAccount(String name) {
		logout = new JMenuItem("Logout");
		menu = menuBar.getMenu(0);
		menu.setText(name);
		menu.setForeground(Color.RED);
		menu.remove(login);
		menu.remove(signUp);
		menu.add(logout);
		this.validate();
	}
	
	public JMenuItem getLogin() {
		return login;
	}
	
	

	public static void main(String[] args) {
		new MainFrame();
	}

}
