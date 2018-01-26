package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class MarsExploration {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		StringBuilder builder = new StringBuilder();
		while (builder.length() < s.length) {
			builder.append("SOS");
		}
		int answer = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] != builder.charAt(i)) {
				answer++;
			}
		}
		out.printLine(answer);
	}
}
