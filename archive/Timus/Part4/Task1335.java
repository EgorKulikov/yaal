package Timus.Part4;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1335 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		out.println((n * n + n) + " " + (n * n + 2 * n) + " " + (n * n));
	}
}

