package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		String u = in.readString();
		StringBuilder builder = new StringBuilder(s.length() + 2 * u.length() - 2);
		for (int i = 1; i < u.length(); i++)
			builder.append('$');
		builder.append(s);
		for (int i = 1; i < u.length(); i++)
			builder.append('$');
		s = builder.toString();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= s.length() - u.length(); i++) {
			int current = 0;
			for (int j = 0; j < u.length(); j++) {
				if (s.charAt(i + j) != u.charAt(j))
					current++;
			}
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
