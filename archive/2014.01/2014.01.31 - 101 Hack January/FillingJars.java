package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class FillingJars {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		long total = 0;
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			long toAdd = in.readInt();
			total += toAdd * (to - from + 1);
		}
		out.printLine(total / count);
    }
}
