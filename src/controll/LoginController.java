package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.*;

import view.*;

public class LoginController {

	private LoginView login;
	private boolean loginStatus = true;
//	private MainFrame mainFrame;
	private int a;
	
	public void Login() {
		String userName = login.getUser();
		String password = login.getPassword();
		if (userName.equals("111") & password.equals("111")) {
			setA(2);
		}
	}

	// Constructor
	public LoginController() {
		login = new LoginView();
		// mainFrame = new MainFrame();
		login.setBtnLoginActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				String userName = login.getUser();
//				String password = login.getPassword();
//				if (userName.equals("111") & password.equals("111")) {
//					setA(2);
//					JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
//					login.closeForm();
//					mainFrame = new MainFrame();
//					mainFrame.addAccount();

//				}
//				if (a != 2) {
//					JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
//				}
				Login();
			}
		});

		login.setTfUserNameActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				String userName = login.getUser();
//				String password = login.getPassword();
//				if (userName.equals("111") & password.equals("111")) {
//					setA(2);
//					JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
//					login.closeForm();
//				}
//				if (a != 2) {
//					JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
//				}
				Login();
			}

		});

		login.setPwFieldActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				String userName = login.getUser();
//				String password = login.getPassword();
//				if (userName.equals("111") & password.equals("111")) {
//					setA(2);
//					JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
//					login.closeForm();
//				}
//				if (a != 2) {
//					JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
//				}
				Login();
			}

		});
	}

	public LoginController(int number) {
		// a = number + 1;
		String userName = login.getUser();
		String password = login.getPassword();
		if (userName.equals("111") & password.equals("111")) {
			setA(2);
		}
	}

	public boolean getLoginStatus() {
		return loginStatus;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public static void main(String[] args) {
		new LoginController();

		// System.out.println(login.getA());
	}
}
