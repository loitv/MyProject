package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import view.AboutUsView;
import view.LoginView;
import view.MainFrame;

public class MainController {

	private MainFrame mainFrame;
	private LoginView login;
	private boolean loginStatus;

	public MainController() {
		mainFrame = new MainFrame();
		mainFrame.setLoginActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login = new LoginView();

				// handle events for button Login
				login.setBtnLoginActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});

				// handle events for textField UserName
				login.setTfUserNameActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
					}
				});
				// handle events for PasswordField
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

		mainFrame.setSeachBookAL(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SearchBookController searchBook = new SearchBookController();
			}

		});

		mainFrame.setAllBookAL(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AllBookController allBook = new AllBookController();
			}
		});
		
		mainFrame.setAboutUsAL(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new AboutUsView();
			}
		});
	}

	public void login() {

		String inputUser = login.getUser();
		String inputPassword = login.getPassword();
		if (inputUser.equals("") || inputPassword.equals("")) {
			JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
		} else {
			String query = "select * from account";
			Statement statement;
			try {
				statement = controll.ConnectDatabase.getConnection().createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (!rs.first()) {
					JOptionPane.showMessageDialog(null, "NONE ACCOUNT HAS BEEN CREATED");
				} else {
					do {
						String userName = rs.getString("userName");
						String password = rs.getString("password");

						if (userName.equalsIgnoreCase(inputUser) & password.equals(inputPassword)) {
							loginStatus = true;
							JOptionPane.showMessageDialog(null, "LOGIN SUCESSFULLY");
							login.closeForm(); // close login window
							mainFrame.addAccount(userName);
							// create event for logout
							mainFrame.setLogoutAL(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									mainFrame.dispose();
									new MainController();
								}
							});
							// when a librarian login
							if (userName.equals("Librarian")) {
								mainFrame.addLibrarianManagement();
								mainFrame.setLibReaderInfoAL(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										new ReaderInformationController();
									};
								});
								
								mainFrame.setLibBookManagementAL(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										new BookController();
									};
								});
							}
							
							if ((!userName.equals("Admin"))&(!userName.equals("Librarian"))) {
								mainFrame.addReaderManagement();
								mainFrame.setReaderInfoAL(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										new PersonalInformationController(userName);
									}
								});
								mainFrame.setBorrowBookInfoAL(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										
										//////////////////////////////
										/////WRITE SOMETHING HERE/////
										//////////////////////////////
										
									}
								});
								
							}
							
						}
						
					} while (rs.next());
					if (!loginStatus) {
						JOptionPane.showMessageDialog(null, "LOGIN UNSUCESSFULLY");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MainController();
	}
}
