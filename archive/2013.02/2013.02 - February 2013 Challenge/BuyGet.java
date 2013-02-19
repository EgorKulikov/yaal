package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BuyGet {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] required = in.readString().toCharArray();
		int[] qty = new int[256];
		for (char c : required)
			qty[c]++;
		int answer = 0;
		for (int i : qty)
			answer += (i + 1) >> 1;
		out.printLine(answer);
    }
}
