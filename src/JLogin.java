import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class JLogin extends JFrame {

	private JLabel lbUser, lbPassword;
	private JTextField tfUser;
	private JPasswordField pwField;
	private JButton btnLogin;

	public JLogin() {
		JPanel loginPanel = new JPanel(new GridLayout(2, 2, 10, 2));
		lbUser = new JLabel("User");
		tfUser = new JTextField(10);
		lbPassword = new JLabel("Password");
		pwField = new JPasswordField(10);
		loginPanel.add(lbUser);
		loginPanel.add(tfUser);
		loginPanel.add(lbPassword);
		loginPanel.add(pwField);
		btnLogin = new JButton("Login");

		// Setup the content-pane of JFrame in BorderLayout
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(loginPanel);
		cp.add(btnLogin);

		setTitle("Log in");
		setSize(280, 130);
		setVisible(true);
		// setResizable(false);

	}

	public static void main(String[] args) {
		new JLogin();
	}
}
