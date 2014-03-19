package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LongestCommonPattern {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] qtyFirst = getQty(in);
		int[] qtySecond = getQty(in);
		int answer = 0;
		for (int i = 0; i < 256; i++)
			answer += Math.min(qtyFirst[i], qtySecond[i]);
		out.printLine(answer);
    }

	private int[] getQty(InputReader in) {
		int[] result = new int[256];
		char[] s = in.readString().toCharArray();
		for (char c : s)
			result[c]++;
		return result;
	}
}
