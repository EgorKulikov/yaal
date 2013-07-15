package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheProbabilityOfWinning {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int t1 = in.readInt();
		int t2 = in.readInt();
		in.readInt();
		in.readInt();
		out.printLine((double)t1 / (t1 + t2));
    }
}
