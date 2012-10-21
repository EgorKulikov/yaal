package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] input = in.readString().toCharArray();
		boolean shift = true;
		for (int i = input.length - 1; i >= 0 && shift; i--) {
			if (input[i] != '9') {
				input[i]++;
				shift = false;
			} else
				input[i] = '0';
		}
		if (shift)
			out.print('1');
		out.printLine(input);
	}
}
