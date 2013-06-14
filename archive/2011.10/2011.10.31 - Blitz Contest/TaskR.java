package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		int start = 0;
		if (digits[0] == '-')
			start = 1;
		out.printLine(digits[start] + digits[digits.length - 1] - 2 * '0');
	}
}
