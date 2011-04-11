package net.egork.timus;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1639 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int m = in.readInt();
		int n = in.readInt();
		if (m * n % 2 == 0)
			out.println("[:=[first]");
		else
			out.print("[second]=:]");
	}
}

