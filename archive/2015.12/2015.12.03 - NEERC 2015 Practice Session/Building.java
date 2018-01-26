package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Building {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		out.printLine(2, n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char letter = (char) (i < 26 ? 'A' + i : 'a' + (i - 26));
				out.print(letter);
			}
			out.printLine();
		}
		out.printLine();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char letter = (char) (j < 26 ? 'A' + j : 'a' + (j - 26));
				out.print(letter);
			}
			out.printLine();
		}
	}
}
