package Timus.Part2;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1142 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n == -1) {
			Exit.exit(in, out);
			return;
		}
		long[][] c = IntegerUtils.generateBinomialCoefficients(n);
		long[] answer = new long[n + 1];
		answer[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				for (int k = 0; k <= i - j; k++)
					answer[i] += c[i - 1][j - 1] * c[i - j][k] * answer[k] * answer[i - j - k];
			}
		}
		out.println(answer[n]);
	}
}

