package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ToGgLeD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long claps = in.readLong() + 1;
		for (int i = 0; i < count; i++) {
			if ((claps & 1) != 0) {
				out.printLine("OFF");
				return;
			}
			claps >>>= 1;
		}
		out.printLine("ON");
	}
}
