package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheWhiteLotusAndCaterpillarGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long total = 0;
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (Math.abs(i - j) <= 1) {
					total += rowCount - 1;
				} else if (i < j) {
					total += Math.max(rowCount - 1, columnCount - 1 - i);
				} else {
					total += Math.max(rowCount - 1, i);
				}
			}
		}
		out.printLine((double)total / columnCount / columnCount);
	}
}
