package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int roublePrice = in.readInt();
		int kopeekPrice = in.readInt();
		int spentRouble = in.readInt();
		int spentKopeek = in.readInt();
		int price = roublePrice * 100 + kopeekPrice;
		int realSpent = -1;
		for (int spent = 100000 + spentRouble * 100 + spentKopeek; spent < 1000000; spent += 1000) {
			if (spent % price == 0) {
				if (realSpent == -1)
					realSpent = spent;
				else
					realSpent = -2;
			}
		}
		if (realSpent == -1) {
			out.printLine(-1);
			return;
		}
		if (realSpent == -2) {
			out.printLine("O-o-ops!");
			return;
		}
		out.printLine(realSpent / price, realSpent / 100, spentKopeek);
	}
}
