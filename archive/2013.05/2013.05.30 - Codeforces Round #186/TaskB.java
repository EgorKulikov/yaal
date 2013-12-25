package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int[] count = new int[s.length()];
		for (int i = 1; i < s.length(); i++)
			count[i] = count[i - 1] + (s.charAt(i) == s.charAt(i - 1) ? 1 : 0);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int left = in.readInt() - 1;
			int right = in.readInt() - 1;
			out.printLine(count[right] - count[left]);
		}
    }
}
