package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MakeItAnagram {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int[] qty = new int[256];
		for (char c : first)
			qty[c]++;
		for (char c : second)
			qty[c]--;
		int answer = 0;
		for (int i : qty)
			answer += Math.abs(i);
		out.printLine(answer);
    }
}
