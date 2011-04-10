package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskJ implements Solver {
	private int[] result;

	public TaskJ() {
		long[] divisorCount = MultiplicativeFunction.DIVISOR_COUNT.calculateUpTo(1000001);
		result = new int[1000001];
		for (int i = 1; i <= 1000000; i++) {
			if (divisorCount[i - 1] > divisorCount[i]) {
				divisorCount[i] = divisorCount[i - 1];
				result[i] = result[i - 1];
			} else
				result[i] = i;
		}
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		out.println(result[in.readInt()]);
	}
}

