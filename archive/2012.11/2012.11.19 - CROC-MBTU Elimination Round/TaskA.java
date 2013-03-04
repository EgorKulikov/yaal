package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int first = 0;
		int second = 0;
		for (int i = 0; i < count; i++) {
			int server = in.readInt();
			int good = in.readInt();
			int bad = in.readInt();
			if (server == 1)
				first += good - bad;
			else
				second += good - bad;
		}
		if (first >= 0)
			out.printLine("LIVE");
		else
			out.printLine("DEAD");
		if (second >= 0)
			out.printLine("LIVE");
		else
			out.printLine("DEAD");
	}
}
