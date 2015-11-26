package view;

import javax.swing.*;

public class AboutUsView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea content;
	
	public AboutUsView() {
		super("About Us");
		setSize(370,200);
		
		content = new JTextArea();
		String aboutUs = "Product Version: 1.0.0\n\n"
				+ "Group Author:    Tran Van Loi"
				+ "\n\tPhan Thi Ngat\n\n"
				+ "Address:             Hanoi University of Science and Technology.\n\n"
				+ "E-mail:\tloitran1763@gmail.com\n"
				+ "\tphanthingat@gmail.com";
		content.setText(aboutUs);
		content.setEditable(false);
		add(content);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new AboutUsView();
	}
}
