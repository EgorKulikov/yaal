package net.egork.y2011.m4.d3;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class CancerOrScorpio implements Solver {
	private int[] daysInMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String date = in.readString();
		int year = Integer.parseInt(date.substring(4));
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(2, 4));
		for (int i = 0; i < 280; i++) {
			day++;
			if (day > daysInMonth[month - 1] || month == 2 && day == 29 && (year % 4 != 0 || year % 100 == 0 && year % 400 != 0)) {
				day = 1;
				month++;
			}
			if (month > 12) {
				month = 1;
				year++;
			}
		}
		out.print(testNumber + " " + format(month, 2) + "/" + format(day, 2) + "/" + format(year, 4) + " ");
		if (dateInside(day, month, 1, 21, 2, 19)) {
			out.println("aquarius");
			return;
		}
		if (dateInside(day, month, 2, 20, 3, 20)) {
			out.println("pisces");
			return;
		}
		if (dateInside(day, month, 3, 21, 4, 20)) {
			out.println("aries");
			return;
		}
		if (dateInside(day, month, 4, 21, 5, 21)) {
			out.println("taurus");
			return;
		}
		if (dateInside(day, month, 5, 22, 6, 21)) {
			out.println("gemini");
			return;
		}
		if (dateInside(day, month, 6, 22, 7, 22)) {
			out.println("cancer");
			return;
		}
		if (dateInside(day, month, 7, 23, 8, 21)) {
			out.println("leo");
			return;
		}
		if (dateInside(day, month, 8, 22, 9, 23)) {
			out.println("virgo");
			return;
		}
		if (dateInside(day, month, 9, 24, 10, 23)) {
			out.println("libra");
			return;
		}
		if (dateInside(day, month, 10, 24, 11, 22)) {
			out.println("scorpio");
			return;
		}
		if (dateInside(day, month, 11, 23, 12, 22)) {
			out.println("sagittarius");
			return;
		}
		out.println("capricorn");
	}

	private String format(int number, int digits) {
		String result = Integer.toString(number);
		if (result.length() < digits)
			result = "0" + result;
		return result;
	}

	private boolean dateInside(int day, int month, int startMonth, int startDay, int endMonth, int endDay) {
		if (month > startMonth && month < endMonth)
			return true;
		if (month == startMonth && day >= startDay)
			return true;
		if (month == endMonth && day <= endDay)
			return true;
		return false;
	}
}

