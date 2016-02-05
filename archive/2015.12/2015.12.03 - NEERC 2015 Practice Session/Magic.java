package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Magic {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		for (int i = 0; i < 2 * n - 2; i++) {
			out.print(9999 - 2 * i);
			for (int j = 0; j < n; j++) {
				int k = i - j;
				if (k >= 0 && k < n)
					out.print(" " + (j * n + k + 1));
			}
			out.printLine();
		}
	}
}
