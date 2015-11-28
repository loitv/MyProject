package model;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateReturnBook {
	private static Date date = null;
	private static Date datenow;
	// public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

	public static void main(String[] args)

	{
		Calendar DateBorrow = Calendar.getInstance();

		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		
		Date now = new Date();
		
		System.out.println("Borrow Book Date: " + ft.format(now));

		String day = ft.format(DateBorrow.getTime());

		DateBorrow.add(Calendar.MONTH, 6);
		String six_month = ft.format(DateBorrow.getTime());
		System.out.println("Return Book Date: " + six_month);

	}

}