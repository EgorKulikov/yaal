package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskP {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] digits = in.readString().toCharArray();
		long answer = 1;
		for (char digit : digits) {
			digit -= '0';
			if (digit % 2 == 0)
				answer *= digit;
		}
		if (answer == 1)
			answer = -1;
		out.printLine(answer);
	}
}
