package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[][] a = IOUtils.readIntTable(in, n, n);
		int[] answer = new int[n];
		int[] qty = new int[n + 1];
		for (int i = 0; i < n; i++) {
			Arrays.fill(qty, 0);
			for (int j = 0; j < n; j++) {
				qty[a[i][j]]++;
			}
			int at = ArrayUtils.maxPosition(qty);
			if (qty[at] > 1) {
				answer[i] = at;
			}
		}
		int current = n - 1;
		for (int i = 0; i < n; i++) {
			if (answer[i] == 0) {
				answer[i] = current++;
			}
		}
		out.printLine(answer);
	}
}
