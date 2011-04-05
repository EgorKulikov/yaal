package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1336 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long n = in.readInt();
		out.println(n * n);
		out.println(n);
	}
}

