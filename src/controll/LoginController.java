package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.*;

public class LoginController {
	
//	private MainFrame mainFrame;
	private LoginView login;

	public LoginController() {
//		mainFrame = new MainFrame();
//		mainFrame.setLoginActionListener(new ActionListener() {

//			@Override
//			public void actionPerformed(ActionEvent arg0) {
				login = new LoginView();
				login.setBtnLoginActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String userName = login.getUser();
						String password = login.getPassword();
						if (userName.equals("loitv") & password.equals("loi123")) {
							JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
							login.closeForm();
						} else {
							JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
						}
						
					}
					
				});
//			}

//		});
	}
}
