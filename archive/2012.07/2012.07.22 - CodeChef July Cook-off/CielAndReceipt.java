package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CielAndReceipt {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int favorite = in.readInt();
		int answer = favorite / 2048 + Integer.bitCount(favorite & 2047);
		out.printLine(answer);
	}
}
