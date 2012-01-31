package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		for (int i = digits.length - 1; i >= 0; i--)
			out.print(digits[i]);
		out.printLine();
	}
}
