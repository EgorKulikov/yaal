package Timus.Part7;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1607 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = Math.max(in.readInt(), a);
		int d = in.readInt();
		while (a != c) {
			a = Math.min(a + b, c);
			c = Math.max(c - d, a);
		}
		out.println(a);
	}
}

