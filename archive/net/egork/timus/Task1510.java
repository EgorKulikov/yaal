package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1510 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] k = in.readIntArray(n);
		Arrays.sort(k);
		for (int i = 0; i + n / 2 < n; i++) {
			if (k[i] == k[i + n / 2]) {
				out.println(k[i]);
				return;
			}
		}
		throw new RuntimeException();
	}
}

