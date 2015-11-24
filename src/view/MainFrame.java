package view;
import java.awt.BorderLayout;
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
	private JMenuItem login, signUp, logout;

	public MainFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lb = new JLabel();
		lb.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/s3.jpg")));
		panel.add(lb, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menu = new JMenu("Account");
		login = new JMenuItem("Login");
		signUp = new JMenuItem("Sign up");
		menu.add(login);
		menu.add(signUp);
		menuBar.add(menu);
		
		menu = new JMenu("Category");
		JMenuItem serchBook = new JMenuItem("Search Book");
		menu.add(serchBook);
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
	
	// Add user's information into Menu bar
	public void addAccount() {
		logout = new JMenuItem("Logout");
		menu = menuBar.getMenu(0);
		menu.setText("LOI");
		menu.remove(0);
		menu.add(logout);
		this.validate();
	}
	
	

	public static void main(String[] args) {
		new MainFrame();
	}
}
