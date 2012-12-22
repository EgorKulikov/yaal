package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StringManipulation {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int[] count = new int[256];
		for (char c : second)
			count[c]++;
		StringBuilder answer = new StringBuilder();
		for (char c : first) {
			if (count[c] == 0)
				answer.append(c);
			else
				count[c]--;
		}
		out.printLine(answer);
	}
}
