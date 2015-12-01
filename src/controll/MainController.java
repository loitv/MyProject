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

		mainFrame.setAboutUsAL(new ActionListener() {
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
						int type = rs.getInt("type");

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
							// when a ADMIN (MANAGER) login
							if (type == 1) {
								mainFrame.addAdminManagement();
								mainFrame.setAdBookManagementAL(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										new BookController();
									}
								});
								mainFrame.setAdLibManagementAL(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										new LibrarianManagerController();
									}
								});
								mainFrame.setAdReaderManagementAL(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										new ReaderInfoController();
									}
								});
								mainFrame.setAdPersonalInfoAL(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										new PersonalInformationController(userName);
									}
								});
							}
							
							// when a librarian login
							if (type == 2) {
								mainFrame.addLibrarianManagement();
								mainFrame.setLibReaderInfoAL(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										new ReaderInfoController();
									};
								});

								mainFrame.setLibBookManagementAL(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										new BookController();
									};
								});

								mainFrame.setLibCreatePatternAL(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										new PatternBorrowController();
									};
								});

								mainFrame.setLibrarianInfoAL(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										new PersonalInformationController(userName);
									};
								});
							}
							// when a reader login
							if (type == 3) {
								mainFrame.addReaderManagement();
								mainFrame.setReaderInfoAL(new ActionListener() {
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
										new ReaderBorrowingInfoController(userName);
										//////////////////////////////
										///// WRITE SOMETHING HERE/////
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
