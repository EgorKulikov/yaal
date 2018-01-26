package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] all = in.readString().toCharArray();
		for (char c : all) {
			if (c == '0') {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
