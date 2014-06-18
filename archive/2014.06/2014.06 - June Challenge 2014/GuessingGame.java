package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GuessingGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		if (first % 2 == 0 || second % 2 == 0)
			out.printLine("1/2");
		else {
			long product = (long)first * second;
			out.printLine((product / 2) + "/" + product);
		}
    }
}
