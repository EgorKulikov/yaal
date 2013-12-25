package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long first = in.readLong();
		long second = in.readLong();
		long result = in.readLong();
		char answer = 0;
		if (first + second == result) {
			if (answer != 0) {
				out.printLine("Invalid");
				return;
			}
			answer = '+';
		}
		if (first - second == result) {
			if (answer != 0) {
				out.printLine("Invalid");
				return;
			}
			answer = '-';
		}
		if (first * second == result) {
			if (answer != 0) {
				out.printLine("Invalid");
				return;
			}
			answer = '*';
		}
		if (first == result * second && second != 0) {
			if (answer != 0) {
				out.printLine("Invalid");
				return;
			}
			answer = '/';
		}
		if (answer == 0)
			out.printLine("Invalid");
		else
			out.printLine(answer);
    }
}
