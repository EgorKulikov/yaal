package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1582 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double k1 = in.readDouble();
		double k2 = in.readDouble();
		double k3 = in.readDouble();
		double sum = 1 / k1 + 1 / k2 + 1 / k3;
		out.printf("%.0f\n", 1000 / sum);
	}
}

