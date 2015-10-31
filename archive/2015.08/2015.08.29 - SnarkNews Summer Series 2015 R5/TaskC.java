package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] w = new int[n];
		int[] d = new int[n];
		IOUtils.readIntArrays(in, w, d);
		int[] a = new int[m];
		int[] b = new int[m];
		IOUtils.readIntArrays(in, a, b);
		int[][] qty = new int[101][1441];
		int[][] orders = new int[101][1441];
		for (int i = 0; i < n; i++) {
			qty[d[i]][w[i]]++;
		}
		for (int i = 0; i < m; i++) {
			orders[b[i]][a[i]]++;
		}
		int answer = 0;
		long profit = 0;
		int[] remaining = new int[1441];
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 1440; j++) {
				remaining[j] += orders[i][j];
				for (int k = j; k >= 1 && qty[i][j] > 0; k--) {
					if (remaining[k] > 0) {
						for (int l = i; l >= 1 && remaining[k] > 0; l--) {
							int current = Math.min(qty[i][j], orders[l][k]);
							remaining[k] -= current;
							qty[i][j] -= current;
							orders[l][k] -= current;
							answer += current;
							profit += current * (2L * l + 500L * k);
						}
					}
				}
			}
		}
		out.printLine(answer, profit);
	}
}
