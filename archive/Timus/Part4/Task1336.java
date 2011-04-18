package Timus.Part4;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1336 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long n = in.readInt();
		out.println(n * n);
		out.println(n);
	}
}

