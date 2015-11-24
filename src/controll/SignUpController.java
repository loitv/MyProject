package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.SignUpView;

public class SignUpController {
	private SignUpView signUp;

	// Constructor
	public SignUpController() {

		signUp = new SignUpView();
		signUp.setBtnSignUpActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pw = signUp.getPassword();
				String rePw = signUp.getRePassword();

				if (pw.equals(rePw)) {
					JOptionPane.showMessageDialog(null, "SIGN UP SUCESSFULLY");
					signUp.closeForm();
				} else {
					JOptionPane.showMessageDialog(null, "SIGN UP UNSUCESSFULLY");
				}
			}
		});
	}
}
