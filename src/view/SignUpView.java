package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbUserName,lbPassword, lbRePassWord;
	private JTextField tfUserName;
	private JPasswordField pwField, rePwField;
	private JButton btnSignUp;
	
	public SignUpView() {
		
		setTitle("Sign Up");
		setSize(280, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		JPanel signUpPanel = new JPanel(new GridLayout(3, 2, 10, 5));
		lbUserName = new JLabel("User Name");
		lbPassword = new JLabel("Password");
		lbRePassWord = new JLabel("Re-enter Password");
		tfUserName = new JTextField(10);
		pwField = new JPasswordField(10);
		rePwField = new JPasswordField(10);
		signUpPanel.add(lbUserName);
		signUpPanel.add(tfUserName);
		signUpPanel.add(lbPassword);
		signUpPanel.add(pwField);
		signUpPanel.add(lbRePassWord);
		signUpPanel.add(rePwField);
		btnSignUp = new JButton("Sign Up");
		
		add(signUpPanel);
		add(btnSignUp);
		
		setVisible(true);
	}
	
	public void setBtnSignUpActionListener (ActionListener listener) {
		this.btnSignUp.addActionListener(listener);
	}
	
	public String getUserName() {
		return this.tfUserName.getText();
	}
	
	public String getPassword() {
		return new String(this.pwField.getPassword());
	}
	
	public String getRePassword() {
		return new String(this.rePwField.getPassword());
	}
	public void closeForm() {
		super.dispose();

	}
//	public static void main(String[] args) {
//		new SignUpView();
//	}
}
