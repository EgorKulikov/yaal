package net.egork.timus;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1353 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] count = new int[82];
		int[] result = new int[82];
		Arrays.fill(count, 1, 10, 1);
		for (int i = 0; i < 9; i++) {
			for (int j = 1; j <= 81; j++)
				result[j] += count[j];
			for (int j = 81; j >= 1; j--) {
				for (int k = 1; k <= 9 && k < j; k++)
					count[j] += count[j - k];
			}
		}
		result[1]++;
		out.println(result[in.readInt()]);
	}
}

