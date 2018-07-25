package by.htp.library.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ControllerUtils {
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{6,20})";

	private static Scanner scan = new Scanner(System.in);

	public static int inputInt(String message, int min, int max) {
		int n = -1;
		System.out.println(message);
		do {
			try {
				n = Integer.parseInt(scan.nextLine());
				if (n < min) {
					System.err.println("Number should be greater than " + min);
				} else if (n > max) {
					System.err.println("Number should be less than " + max);
				}
			} catch (NumberFormatException e) {
				System.err.println("Please input number");
			}
		} while (n < min || n > max);
		return n;
	}

	public static String inputString(String message, int minLenght, int maxLenght) {
		String s = "";
		System.out.println(message);
		do {
			s = scan.nextLine();
			if (s.length() < minLenght) {
				System.err.println("String length should be greater than " + minLenght + " characters");
			} else if (s.length() > maxLenght) {
				System.err.println("String length should be less than " + maxLenght + " characters");
			}
		} while (s.length() < minLenght || s.length() > maxLenght);
		return s;
	}

	public static String inputStringPassword(String message) {
		int minLenght = 6;
		int maxLenght = 20;
		String pass = "";
		boolean valid = false;
		do {
			System.out.println(message);
			pass = scan.nextLine();
			valid = Pattern.matches(PASSWORD_PATTERN, pass);
			if (pass.length() < minLenght) {
				System.err.println("Password should be greater than " + minLenght + " characters");
			} else if (pass.length() > maxLenght) {
				System.err.println("Password should be less than " + maxLenght + " characters");
			} else if (!valid) {
				System.err.println("Password should contains 6 and more characters with 1 or more numbers");
			}
		} while (!valid);
		return pass;
	}

	public static LocalDate inputDate(String message) {
		String inputDate = "";
		LocalDate date = null;
		do {
			System.out.println(message);
			inputDate = scan.nextLine();
			try {
				date = LocalDate.parse(inputDate);
			} catch (DateTimeParseException e) {
				System.out.println("Incorrect date");
			}
			if (LocalDate.now().isBefore(date)) {
				System.out.println("Return date may not to be in future");
			}
		} while (date == null || LocalDate.now().isBefore(date));
		return date;
	}

}
