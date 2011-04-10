package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.io.IOUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskT implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i * i < n; i++) {
			int x = n - i * i;
			if (x % i == 0)
				result.add(x);
		}
		Collections.sort(result);
		out.print("Case " + testNumber + ": ");
		IOUtils.printCollection(result, out);
	}
}

