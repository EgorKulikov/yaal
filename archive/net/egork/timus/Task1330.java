package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1330 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] k = in.readIntArray(n);
		long[] sum = new long[n + 1];
		for (int i = 0; i < n; i++)
			sum[i + 1] = sum[i] + k[i];
		int queryCount = in.readInt();
		for (int it = 0; it < queryCount; it++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			out.println(sum[to] - sum[from]);
		}
	}
}

