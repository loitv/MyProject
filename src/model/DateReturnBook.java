package model;

import java.util.Calendar;
//import java.util.Date;
//import java.text.SimpleDateFormat;

public class DateReturnBook {

	// public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

	// public static void main(String[] args)
	public DateReturnBook()

	{
		Calendar DateBorrow = Calendar.getInstance();

		// SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

		// System.out.println("Borrow Book Date: " + ft.format(now));

		DateBorrow.add(Calendar.MONTH, 6);
		// return ft.format(DateBorrow.getTime());
		// System.out.println("Return Book Date: " + six_month);

	}

}