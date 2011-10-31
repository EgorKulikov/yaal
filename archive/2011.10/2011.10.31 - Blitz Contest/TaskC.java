package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		char temp = digits[0];
		digits[0] = digits[2];
		digits[2] = temp;
		out.printLine(Integer.parseInt(new String(digits)));
	}
}
