package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		int start = 0;
		if (digits[0] == '-')
			start = 1;
		for (int i = start; i < start + 3; i++)
			digits[i] -= '0';
		int product = 1;
		int sum = 0;
		for (int i = start; i < start + 3; i++) {
			product *= digits[i];
			sum += digits[i];
		}
		out.printLine(product - sum);
	}
}
