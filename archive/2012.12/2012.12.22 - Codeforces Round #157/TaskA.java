package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		StringBuilder result = new StringBuilder();
		boolean wasZero = false;
		for (int i = 0; i < number.length; i++) {
			if (number[i] == '0' && !wasZero) {
				wasZero = true;
				continue;
			}
			if (!wasZero && i == number.length - 1)
				continue;
			result.append(number[i]);
		}
		out.printLine(result);
	}
}
