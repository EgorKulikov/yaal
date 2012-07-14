package April2011.UVaHugeEasyContestII;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.old.io.stringinputreader.StringInputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String expression = in.readString().replace('(', ' ').replace(')', ' ').replace('+', ' ').replace('^', ' ');
		in = new StringInputReader(expression);
		String a = in.readString();
		String b = in.readString();
		int k = in.readInt();
		long[][] c = IntegerUtils.generateBinomialCoefficients(k);
		out.print("Case " + testNumber + ": ");
		if (k == 1) {
			out.println(a + "+" + b);
			return;
		}
		out.print(a + "^" + k);
		for (int i = 1; i < k; i++) {
			out.print("+" + c[k][i] + "*" + power(a, k - i) + "*" + power(b, i));
		}
		out.println("+" + b + "^" + k);
	}

	private String power(String a, int k) {
		if (k == 1)
			return a;
		return a + "^" + k;
	}
}

