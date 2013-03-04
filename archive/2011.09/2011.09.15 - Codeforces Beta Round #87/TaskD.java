import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class TaskD implements Solver {
	private static final long MOD = 1000003;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String formula = "+" + in.readString();
		if (!Character.isDigit(formula.charAt(formula.length() - 1))) {
			out.println(0);
			return;
		}
		String[] tokens = formula.split("[0-9]+");
		int[] count = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			for (int j = 1; j < tokens[i].length(); j++) {
				if (tokens[i].charAt(j) != '+' && tokens[i].charAt(j) != '-') {
					out.println(0);
					return;
				}
			}
			count[i] = tokens[i].length() - 1;
		}
		long[] result = new long[count.length];
		long[] nextResult = new long[count.length];
		result[0] = 1;
		long[] reverse = new long[count.length];
		BigInteger bigMod = BigInteger.valueOf(MOD);
		for (int i = 0; i < count.length; i++)
			reverse[i] = BigInteger.valueOf(i + 1).modInverse(bigMod).longValue();
		for (int i = count.length - 2; i >= 0; i--) {
			int maxPower = count.length - 1 - i;
			Arrays.fill(nextResult, 0, maxPower + 1, 0);
			long multiplyBy = 1;
			for (int j = 0; j <= maxPower; j++) {
				for (int k = j; k <= maxPower; k++)
					nextResult[k] += result[k - j] * multiplyBy;
				nextResult[j] %= MOD;
				multiplyBy = multiplyBy * (count[i] + j + 1) % MOD * reverse[j] % MOD;
			}
			long[] temp = result;
			result = nextResult;
			nextResult = temp;
		}
		out.println(result[count.length - 1]);
	}
}

