package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1066 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		double previous = in.readDouble();
		double current = 0;
		double answer = 0;
		for (int i = 2; i < n; i++) {
			double next = 2 * current - previous + 2;
			answer = Math.max(answer, -next / i);
			previous = current;
			current = next;
		}
		current += (n - 1) * answer;
		out.printf("%.2f\n", current);
	}
}

