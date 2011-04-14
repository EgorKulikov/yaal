package Timus.Part2;

import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1157 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int m = in.readInt();
		int n = in.readInt();
		int k = in.readInt();
		for (int l = k + 1; l <= 10000; l++) {
			if ((MultiplicativeFunction.DIVISOR_COUNT.calculate(l) + 1) / 2 == n && (MultiplicativeFunction.DIVISOR_COUNT.calculate(l - k) + 1) / 2 == m) {
				out.println(l);
				return;
			}
		}
		out.println(0);
	}
}

