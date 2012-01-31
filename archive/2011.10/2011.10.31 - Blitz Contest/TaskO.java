package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskO {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		int sum = 0;
		for (char digit : digits) {
			if (digit != '-')
				sum += digit - '0';
		}
		out.printLine(sum);
	}
}
