package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1051 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (n == 1) {
			out.println((m + 1) / 2);
			return;
		}
		if (m == 1) {
			out.println((n + 1) / 2);
			return;
		}
		if (m % 3 == 0 || n % 3 == 0)
			out.println(2);
		else
			out.println(1);
	}
}

