package net.egork.timus;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1157 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int m = in.readInt();
		int n = in.readInt();
		int k = in.readInt();
		for (int l = k + 1; l <= 10000; l++) {
			if ((IntegerUtils.calculateNumDivisors(l) + 1) / 2 == n && (IntegerUtils.calculateNumDivisors(l - k) + 1) / 2 == m) {
				out.println(l);
				return;
			}
		}
		out.println(0);
	}
}

