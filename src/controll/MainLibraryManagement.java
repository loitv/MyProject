package controll;

import javax.swing.SwingUtilities;

import controll.MainController;

public class MainLibraryManagement {
	public static void main(String[] args) {
		// Run GUI codes in the Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				new MainController();
			}
		});
	}
}
