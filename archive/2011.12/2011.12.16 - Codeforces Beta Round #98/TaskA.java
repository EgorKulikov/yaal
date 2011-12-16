package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] items = in.readString().toCharArray();
		int count = 0;
		char current = items[0];
		int answer = 0;
		for (char c : items) {
			if (count == 5 || current != c) {
				count = 0;
				answer++;
				current = c;
			}
			count++;
		}
		answer++;
		out.printLine(answer);
	}
}
