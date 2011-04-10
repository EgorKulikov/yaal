package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskL implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] sequence = in.readString().toCharArray();
		long result = 0;
		for (char token : sequence) {
			result <<= 2;
			if (token == 'C')
				result++;
			else if (token == 'G')
				result += 2;
			else if (token == 'T')
				result += 3;
		}
		out.println("Case " + testNumber + ": (" + sequence.length + ":" + result + ")");
	}
}

