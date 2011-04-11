package net.egork.timus;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1146 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		out.println(MiscUtils.maximalRectangleSum(in.readLongTable(n, n)));
	}
}

