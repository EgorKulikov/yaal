package net.egork.timus;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1104 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String number = in.readString();
		int sumDigits = IntegerUtils.sumDigits(number);
		int minBase = 2;
		for (int i = number.length() - 1; i >= 0; i--) {
			minBase = Math.max(minBase, IntegerUtils.digitValue(number.charAt(i)) + 1);
		}
		for (int k = minBase; k <= 36; k++) {
			if (sumDigits % (k - 1) == 0) {
				out.println(k);
				return;
			}
		}
		out.println("No solution.");
	}
}

