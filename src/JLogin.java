import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbUser, lbPassword;
	private JTextField tfUser;
	private JPasswordField pwField;
	private JButton btnLogin;

	public JLogin() {
		JPanel loginPanel = new JPanel(new GridLayout(2, 2, 10, 5));
		lbUser = new JLabel("User");
		tfUser = new JTextField(10);
		lbPassword = new JLabel("Password");
		pwField = new JPasswordField(10);
		loginPanel.add(lbUser);
		loginPanel.add(tfUser);
		loginPanel.add(lbPassword);
		loginPanel.add(pwField);
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName = getUser();
				String password = getPassword();
				if (userName.equals("loitv") & password.equals("loi123")) {
					JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
					closeForm();
				} else {
					JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
				}
			}

		});
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

	public String getUser() {
		return this.tfUser.getText();
	}

	public String getPassword() {
		return (new String(this.pwField.getPassword()));
	}
	
	public void closeForm() {
		super.dispose();

	}

	public static void main(String[] args) {
		new JLogin();
	}

}
