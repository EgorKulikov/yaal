package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1260 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		long[] a = new long[n + 1];
		a[1] = 1;
		for (int i = 2; i <= n; i++) {
			a[i] = a[i - 1];
			if (i >= 3)
				a[i] += a[i - 3];
		}
		long result = a[n];
		for (int i = n - 2; i >= 0; i--)
			result += a[i];
		out.println(result);
	}
}

