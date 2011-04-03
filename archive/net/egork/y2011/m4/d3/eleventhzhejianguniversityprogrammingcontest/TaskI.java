package net.egork.y2011.m4.d3.eleventhzhejianguniversityprogrammingcontest;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class TaskI implements Solver {
	private int[] daysInMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] id = in.readString().toCharArray();
		char[] result = id.clone();
		if (id.length == 15) {
			int year = (id[6] - '0') * 10 + id[7] - '0';
			int month = (id[8] - '0') * 10 + id[9] - '0';
			int day = (id[10] - '0') * 10 + id[11] - '0';
			int difference = Integer.MAX_VALUE;
			for (int y = 0; y < 100; y++) {
				for (int m = 1; m <= 12; m++) {
					for (int d = 1; d <= daysInMonth[m - 1]; d++) {
						int currentDifference = calculateDifference(year, y) + calculateDifference(month, m) +
							calculateDifference(day, d);
						if (currentDifference < difference && (isValidDate(y + 1900, m, d) || isValidDate(y + 2000, m, d))) {
							result[6] = (char) ((y / 10) + '0');
							result[7] = (char) ((y % 10) + '0');
							result[8] = (char) ((m / 10) + '0');
							result[9] = (char) ((m % 10) + '0');
							result[10] = (char) ((d / 10) + '0');
							result[11] = (char) ((d % 10) + '0');
							difference = currentDifference;
						}
					}
				}
			}
		} else {
			int year = (id[6] - '0') * 1000 + (id[7] - '0') * 100 + (id[8] - '0') * 10 + id[9] - '0';
			int month = (id[10] - '0') * 10 + id[11] - '0';
			int day = (id[12] - '0') * 10 + id[13] - '0';
			int sum = ((id[0] - '0') * 7 + (id[1] - '0') * 9 + (id[2] - '0') * 10 + (id[3] - '0') * 5 +
				(id[4] - '0') * 8 + (id[5] - '0') * 4 + (id[14] - '0') * 8 + (id[15] - '0') * 4 + (id[16] - '0') * 2) % 11;
			int checksum = (id[17] == 'X') ? 10 : id[17] - '0';
			int difference = Integer.MAX_VALUE;
			for (int y = 1900; y <= 2011; y++) {
				for (int m = 1; m <= 12; m++) {
					for (int d = 1; d <= daysInMonth[m - 1]; d++) {
						int addSum = (y / 1000) * 2 + ((y / 100) % 10) + ((y / 10) % 10) * 6 + (y % 10) * 3 + (m / 10) * 7 + (m % 10) * 9 +
							(d / 10) * 10 + (d % 10) * 5;
						int currentCheckSum = (12 - (sum + addSum) % 11) % 11;
						int currentDifference = calculateDifference(year, y) + calculateDifference(month, m) +
							calculateDifference(day, d);
						if (checksum != currentCheckSum)
							currentDifference++;
						if (currentDifference < difference && isValidDate(y, m, d)) {
							result[6] = (char) ((y / 1000) + '0');
							result[7] = (char) (((y / 100) % 10) + '0');
							result[8] = (char) (((y / 10) % 10) + '0');
							result[9] = (char) ((y % 10) + '0');
							result[10] = (char) ((m / 10) + '0');
							result[11] = (char) ((m % 10) + '0');
							result[12] = (char) ((d / 10) + '0');
							result[13] = (char) ((d % 10) + '0');
							result[17] = currentCheckSum == 10 ? 'X' : (char) (currentCheckSum + '0');
							difference = currentDifference;
						}
					}
				}
			}
		}
		for (char c : result)
			out.print(c);
		out.println();
	}

	private int calculateDifference(int a, int b) {
		int result = 0;
		while (a != 0 || b != 0) {
			if (a % 10 != b % 10)
				result++;
			a /= 10;
			b /= 10;
		}
		return result;
	}

	boolean isValidDate(int year, int month, int day) {
		if (year < 1900 || year > 2011)
			return false;
		if (month < 1 || month > 12)
			return false;
		if (day < 1 || day > daysInMonth[month - 1])
			return false;
		if (month == 2 && ((year & 3) != 0 || year == 1900) && day == 29)
			return false;
		if (year == 2011 && (month > 4 || month == 4 && day > 2))
			return false;
		return true;
	}
}

