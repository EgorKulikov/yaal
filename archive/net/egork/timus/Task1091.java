package net.egork.timus;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1091 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int k = in.readInt();
		int s = in.readInt();
		long[][] c = IntegerUtils.generateBinomialCoefficients(s);
		long result = 0;
		for (int i = 2; i <= s; i++)
			result += -MultiplicativeFunction.MOBIUS.calculate(i) * c[s / i][k];
		out.println(Math.min(result, 10000));
	}
}

