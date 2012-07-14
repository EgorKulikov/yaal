package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		StringBuilder answer = new StringBuilder();
		while (number < 32) {
			number *= 2;
			answer.append('*');
		}
		while (number > 32 && number < 63) {
			number--;
			answer.append('-');
		}
		if (number == 32) {
			number *= 2;
			answer.append('*');
		}
		while (number > 63) {
			number--;
			answer.append('-');
		}
		answer.append("*-***");
		out.printLine(answer.length());
		for (int i = 0; i < answer.length(); i++)
			out.printLine(answer.charAt(i));
	}
}
