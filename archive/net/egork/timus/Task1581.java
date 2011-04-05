package net.egork.timus;

import net.egork.io.IOUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Task1581 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] number = in.readIntArray(n);
		int start = 0;
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			if (number[i] != number[start]) {
				result.add(i - start);
				result.add(number[start]);
				start = i;
			}
		}
		result.add(n - start);
		result.add(number[start]);
		IOUtils.printCollection(result, out);
	}

}

