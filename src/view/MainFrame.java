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
	private JMenuItem login, signUp, logout, searchBook, allBook, readerInfo, borrowBookInfo, 
	libBookManagement, libCreatePattern, libReaderInfo, librarianInfo, adBookManagement, adLibManagerment,adReaderInfo, adInfo,
	aboutUs;

	public MainFrame() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lb = new JLabel();
		lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/main.jpg")));
		panel.add(lb, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menu = new JMenu("Account");
		menu.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/account.png")));
		login = new JMenuItem("Login");
		login.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/login.png")));
		signUp = new JMenuItem("Sign up");
		signUp.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/signup.png")));
		menu.add(login);
		menu.add(signUp);
		menuBar.add(menu);

		menu = new JMenu("Management");
		menu.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/manaMenu.png")));
		menuBar.add(menu);

		menu = new JMenu("Book");
		menu.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/Bookmenu.png")));
		searchBook = new JMenuItem("Search Book");
		searchBook.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/searchB.png")));
		menu.add(searchBook);
		allBook = new JMenuItem("All Books");
		allBook.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/allbook2.png")));
		menu.add(allBook);
		menuBar.add(menu);
		
		menu = new JMenu("Help");
		menu.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/help.png")));
		aboutUs = new JMenuItem("About Us");
		aboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/aboutus1.png")));
		menu.add(aboutUs);
		menuBar.add(menu);

		setJMenuBar(menuBar);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout(5, 5));
		cp.add(panel, BorderLayout.CENTER);

		setTitle("LIBRARY MANAGEMENT");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/library.png")));
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

	public void setSignUpActionListener(ActionListener listener) {
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

	public void setAboutUsAL(ActionListener al) {
		this.aboutUs.addActionListener(al);
	}

	// Add user's name into Menu bar
	public void addAccount(String name) {
		logout = new JMenuItem("Logout");
		logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logout1.png")));
		menu = menuBar.getMenu(0);
		menu.setText(name);
		menu.setForeground(Color.RED);
		menu.remove(login);
		menu.remove(signUp);
		menu.add(logout);
		this.validate();
	}

	// add reader management list when a reader login
	public void addReaderManagement() {
		readerInfo = new JMenuItem("Personal Information");
		readerInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/perInfo1.png")));
		borrowBookInfo = new JMenuItem("Books You Brorrowed");
		borrowBookInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/borrowbooks.png")));
		menu = menuBar.getMenu(1);
		menu.add(borrowBookInfo);
		menu.add(readerInfo);
	}
	public void setReaderInfoAL(ActionListener al) {
		this.readerInfo.addActionListener(al);
	}

	public void setBorrowBookInfoAL(ActionListener al) {
		this.borrowBookInfo.addActionListener(al);
	}

	// add librarian management list when a Librarian login
	public void addLibrarianManagement() {
		libBookManagement = new JMenuItem("Book Management");
		libBookManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/bookManaMenu.png")));
		libCreatePattern = new JMenuItem("Create New Pattern Borrow");
		libCreatePattern.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/createpattern1.png")));
		libReaderInfo = new JMenuItem("Reader Information");
		libReaderInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/perInfo.png")));
		librarianInfo = new JMenuItem("Personal Information");
		librarianInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Icon/perInfo1.png")));
		menu = menuBar.getMenu(1);
		menu.add(libBookManagement);
		menu.add(libCreatePattern);
		menu.add(libReaderInfo);
		menu.add(librarianInfo);
		
	}
	public void setLibBookManagementAL(ActionListener al) {
		this.libBookManagement.addActionListener(al);
	}
	public void setLibCreatePatternAL(ActionListener al) {
		this.libCreatePattern.addActionListener(al);
	}
	public void setLibReaderInfoAL(ActionListener al) {
		this.libReaderInfo.addActionListener(al);
	}
	public void setLibrarianInfoAL(ActionListener al) {
		this.librarianInfo.addActionListener(al);
	}
	
	
	// add librarian management list when a Admin login
	//adBookManagement, adLibManagerment, adInfo
		public void addAdminManagement() {
			adBookManagement = new JMenuItem("Book Management");
			adBookManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource(
					"/Icon/bookManaMenu.png")));
			adLibManagerment = new JMenuItem("Librarian Management");
			adLibManagerment.setIcon(new javax.swing.ImageIcon(getClass().getResource(
					"/Icon/libInfo.png")));
			adReaderInfo = new JMenuItem("Reader Management");
			adReaderInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
					"/Icon/perInfo.png")));
			adInfo = new JMenuItem("Personal Information");
			adInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
					"/Icon/perInfo1.png")));
			menu = menuBar.getMenu(1);
			menu.add(adBookManagement);
			menu.add(adLibManagerment);
			menu.add(adReaderInfo);
			menu.add(adInfo);
		}
		public void setAdBookManagementAL(ActionListener al) {
			this.adBookManagement.addActionListener(al);
		}
		public void setAdLibManagementAL(ActionListener al) {
			this.adLibManagerment.addActionListener(al);
		}
		public void setAdReaderManagementAL(ActionListener al) {
			this.adReaderInfo.addActionListener(al);
		}
		public void setAdPersonalInfoAL(ActionListener al) {
			this.adInfo.addActionListener(al);
		}

	public JMenuItem getLogin() {
		return login;
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
