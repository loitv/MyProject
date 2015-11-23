package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

public class SignUpController {
//	private MainFrame mainFrame;
	private SignUpView signUp;
	
	// Constructor
	public SignUpController() {
//		mainFrame = new MainFrame();
//		mainFrame.setSignUpActionListener(new ActionListener() {

//			@Override
//			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				signUp = new SignUpView();
				signUp.setBtnSignUpActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String pw = signUp.getPassword();
						String rePw = signUp.getRePassword();
						
						if (pw.equals(rePw)) {
							System.out.println("Sign up successfully");
						} else {
							System.out.println("Password not match");
						}
						
					}
					
				});
				
//			}
			
//		});
	}
}
