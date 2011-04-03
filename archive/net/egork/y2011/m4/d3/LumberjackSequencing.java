package net.egork.y2011.m4.d3;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class LumberjackSequencing implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		if (testNumber == 1)
			out.println("Lumberjacks:");
		int[] lengths = in.readIntArray(10);
		int[] sortedLengths = lengths.clone();
		Arrays.sort(sortedLengths);
		boolean ordered = true;
		for (int i = 0; i < 10; i++) {
			if (lengths[i] != sortedLengths[i])
				ordered = false;
		}
		if (ordered) {
			out.println("Ordered");
			return;
		}
		ordered = true;
		for (int i = 0; i < 10; i++) {
			if (lengths[i] != sortedLengths[9 - i])
				ordered = false;
		}
		if (ordered) {
			out.println("Ordered");
			return;
		}
		out.println("Unordered");
	}
}

