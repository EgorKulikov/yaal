package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1283 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double gold = in.readDouble();
		double minimumGold = in.readDouble();
		double multiplier = (100 - in.readInt()) / 100.;
		int result = 0;
		while (gold > minimumGold + 1e-8) {
			result++;
			gold *= multiplier;
		}
		out.println(result);
	}
}

