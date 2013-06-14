package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskT {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		for (int i = 0; i < digits.length; i++) {
			if (digits[i] != digits[digits.length - 1 - i]) {
				out.printLine("No");
				return;
			}
		}
		out.printLine("Yes");
	}
}
