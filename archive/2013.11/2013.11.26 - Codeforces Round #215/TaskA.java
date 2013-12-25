package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[][] qty = new int[3][s.length + 1];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < 3; j++)
				qty[j][i + 1] = qty[j][i];
			qty[s[i] - 'x'][i + 1]++;
		}
		int queryCount = in.readInt();
		int[] cQty = new int[3];
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			if (to - from < 3) {
				out.printLine("YES");
				continue;
			}
			for (int j = 0; j < 3; j++)
				cQty[j] = qty[j][to] - qty[j][from];
			boolean good = true;
			for (int j = 0; j < 3; j++) {
				for (int k = j + 1; k < 3; k++) {
					if (Math.abs(cQty[j] - cQty[k]) > 1)
						good = false;
				}
			}
			if (good)
				out.printLine("YES");
			else
				out.printLine("NO");
		}
    }
}
