package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class MainController {
	
	private MainFrame mainFrame;
	
	public MainController() {
		mainFrame = new MainFrame();
		
		mainFrame.setLoginActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginController();
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
