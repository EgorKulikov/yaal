package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1059 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		out.println(0);
		for (int i = 1; i <= n; i++) {
			out.println('X');
			out.println('*');
			out.println(i);
			out.println('+');
		}
	}
}

