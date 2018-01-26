package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		char temp = digits[0];
		digits[0] = digits[2];
		digits[2] = temp;
		out.printLine(Integer.parseInt(new String(digits)));
	}
}
