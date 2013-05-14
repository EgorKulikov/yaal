import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class AttackOfTheClones implements Solver {
	private static final long MOD = 1000003;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		long[][][][] count = new long[2][2][2][2];
		count[1][1][1][1] = IntegerUtils.power(2, n - 1, MOD);
		count[1][1][1][0] = (IntegerUtils.power(2, (IntegerUtils.power(2, n - 1, MOD - 1) + MOD - 2) % (MOD - 1), MOD) -
			count[1][1][1][1] + MOD) % MOD;
		count[1][1][0][0] = (IntegerUtils.power(2, (IntegerUtils.power(2, n, MOD - 1) + MOD - 3) % (MOD - 1), MOD) -
			count[1][1][1][1] - count[1][1][1][0] + 2 * MOD) % MOD;
		count[1][0][0][1] = count[1][1][1][1];
		count[1][0][0][0] = (count[1][1][0][0] + count[1][1][1][0]) % MOD;
		for (int p = 0; p < 2; p++) {
			for (int d = 0; d < 2; d++)
				System.arraycopy(count[1][1 - p][d], 0, count[0][p][d], 0, 2);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			String expression = in.readString();
			long result = 0;
			for (int z = 0; z < 2; z++) {
				for (int p = 0; p < 2; p++) {
					for (int d = 0; d < 2; d++) {
						for (int a = 0; a < 2; a++) {
							if (count[z][p][d][a] == 0 || evaluate(expression, z, p, d, a) == 0)
								continue;
							result += count[z][p][d][a];
						}
					}
				}
			}
			out.println(result % MOD);
		}
	}

	private int evaluate(String expression, int z, int p, int d, int a) {
		if (expression.length() == 1) {
			if (expression.charAt(0) == 'Z')
				return z;
			if (expression.charAt(0) == 'P')
				return p;
			if (expression.charAt(0) == 'D')
				return d;
			if (expression.charAt(0) == 'A')
				return a;
		}
		int bracketCount = 0;
		for (int i = expression.length() - 1; i >= 0; i--) {
			if (expression.charAt(i) == ')')
				bracketCount++;
			else if (expression.charAt(i) == '(')
				bracketCount--;
			else if (bracketCount == 0) {
				if (expression.charAt(i) == 'v')
					return Math.max(evaluate(expression.substring(0, i), z, p, d, a), evaluate(expression.substring(i + 1), z, p, d, a));
				if (expression.charAt(i) == '^')
					return Math.min(evaluate(expression.substring(0, i), z, p, d, a), evaluate(expression.substring(i + 1), z, p, d, a));
				if (expression.charAt(i) == '\\')
					return Math.min(evaluate(expression.substring(0, i), z, p, d, a), 1 - evaluate(expression.substring(i + 1), z, p, d, a));
			}
		}
		if (expression.charAt(0) == '!')
			return 1 - evaluate(expression.substring(1), z, p, d, a);
		return evaluate(expression.substring(1, expression.length() - 1), z, p, d, a);
	}
}

