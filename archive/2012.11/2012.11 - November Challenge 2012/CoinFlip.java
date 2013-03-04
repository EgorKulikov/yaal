package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoinFlip {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int gameCount = in.readInt();
		for (int i = 0; i < gameCount; i++) {
			int initial = in.readInt();
			int count = in.readInt();
			int query = in.readInt();
			if (initial == query)
				out.printLine(count / 2);
			else
				out.printLine((count + 1) / 2);
		}
	}
}
