import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	private static final int MOD = 1000000007;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int left = in.readInt();
		int right = in.readInt();
		int result = (count(left, right) + count(left / 2 + 1, (right + 1) / 2)) % MOD;
		result = (int) ((result * 500000004L) % MOD);
		out.println(result);
	}

	private int count(int left, int right) {
		if (left > right)
			return 0;
		long result = 0;
		if (left == 1) {
			result = 4;
			left++;
			if (left > right)
				return 4;
		}
		result += 2L * f(left, right) + 4L * f(left - 1, right - 1);
		return (int) (result % MOD);
	}

	private int f(int left, int right) {
		int result = f(right) - f(left - 1);
		if (result < 0)
			result += MOD;
		return result;
	}

	private int f(int n) {
		return (even(n / 2) + odd((n + 1) / 2)) % MOD;
	}

	private int even(int n) {
		return (2 * odd(n)) % MOD;
	}

	private int odd(int n) {
		long result = IntegerUtils.power(3, n, MOD) - 1;
		result = (result * 500000004) % MOD;
		return (int) result;
	}
}

