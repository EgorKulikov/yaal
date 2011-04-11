package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1054 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] d = in.readIntArray(n);
		int from = 1;
		int to = 2;
		long answer = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (d[i] == from)
				to = 6 - from - to;
			else if (d[i] == to) {
				answer += 1 << i;
				from = 6 - from - to;
			} else {
				out.println(-1);
				return;
			}
		}
		out.println(answer);
	}
}

