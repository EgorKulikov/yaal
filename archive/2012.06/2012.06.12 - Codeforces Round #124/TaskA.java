package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		StringBuilder result = new StringBuilder();
		int position = 0;
		for (char c = 'z'; c >= 'a'; c--) {
			for (int i = position; i < s.length(); i++) {
				if (s.charAt(i) == c) {
					position = i;
					result.append(c);
				}
			}
		}
		out.printLine(result);
	}
}
