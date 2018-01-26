package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class BudgetFriendly {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int c = in.readInt();
		int b = in.readInt();
		double[] answer = new double[b + 1];
		Arrays.fill(answer, Double.NEGATIVE_INFINITY);
		answer[0] = 0;
		double[] next = new double[b + 1];
		for (int i = 0; i < c; i++) {
			int n = in.readInt();
			Arrays.fill(next, Double.NEGATIVE_INFINITY);
			for (int j = 0; j < n; j++) {
				int p = in.readInt();
				double s = in.readDouble();
				for (int k = p; k <= b; k++) {
					next[k] = Math.max(next[k], answer[k - p] + s);
				}
			}
			double[] temp = answer;
			answer = next;
			next = temp;
		}
		double result = Double.NEGATIVE_INFINITY;
		for (double d : answer) {
			result = Math.max(result, d);
		}
		if (result == Double.NEGATIVE_INFINITY) {
			out.printLine(-1);
		} else {
			out.printFormat("%.2f\n", result);
		}
	}
}
