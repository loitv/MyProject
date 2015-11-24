package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.LoginView;
import view.MainFrame;

public class MainController {

	private MainFrame mainFrame;
	private LoginController loginCtrl;
	private LoginView login;
	private int a;

	public void login() {
		String userName = login.getUser();
		String password = login.getPassword();
		if (userName.equals("111") & password.equals("111")) {
			a = 2;
		}
		if (a == 2) {
			JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
			login.closeForm(); // close login window
			mainFrame.addAccount(); // add account, logout into menubar

			// create event for logout
			mainFrame.setLogoutAL(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainFrame.dispose();
					new MainController();
				}

			});

		} else {
			JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
		}
	}

	public void afterLogin() {
		loginCtrl = new LoginController(4);
		a = loginCtrl.getA();
		if (a == 2) {
			JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
			login.closeForm();
			mainFrame.addAccount();
		} else {
			JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
		}
	}

	public MainController() {
		mainFrame = new MainFrame();
		mainFrame.setLoginActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login = new LoginView();

				// login by pressing button
				login.setBtnLoginActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});

				// login after enter user name
				login.setTfUserNameActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
				// login after enter password
				login.setPwFieldActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
			}
		});

		mainFrame.setSignUpActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SignUpController();

			}

		});
	}

	public static void main(String[] args) {
		new MainController();
	}
}
