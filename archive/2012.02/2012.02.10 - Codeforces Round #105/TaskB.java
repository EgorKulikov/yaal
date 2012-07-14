package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String time = in.readString();
		String hours = time.split(":")[0];
		String minutes = time.split(":")[1];
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = 2; i <= 59; i++) {
			int hour = parse(hours, i);
			int minute = parse(minutes, i);
			if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60)
				answer.add(i);
		}
		int hour = parse(hours, 60);
		int minute = parse(minutes, 60);
		if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
			out.printLine(-1);
			return;
		}
		if (answer.isEmpty())
			out.printLine(0);
		else
			out.printLine(answer.toArray());
	}

	private int parse(String number, int base) {
		int result = 0;
		for (char c : number.toCharArray()) {
			int digit = Character.isDigit(c) ? c - '0' : c - 'A' + 10;
			if (digit >= base)
				return -1;
			result *= base;
			result += digit;
		}
		return result;
	}
}
