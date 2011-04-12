package Timus.Part3;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1295 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int b = 1;
		int c = 1;
		int d = 1;
		for (int i = 0; i < n; i++) {
			b = b * 2 % 100;
			c = c * 3 % 100;
			d = d * 4 % 100;
		}
		if ((1 + b + c + d) % 100 == 0)
			out.println(2);
		else if ((1 + b + c + d) % 10 == 0)
			out.println(1);
		else
			out.println(0);
	}
}

