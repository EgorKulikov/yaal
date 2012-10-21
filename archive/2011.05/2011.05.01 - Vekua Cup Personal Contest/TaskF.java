import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n >= 10) {
			out.println(-1);
			return;
		}
		BigInteger result = null;
		for (int i = n; i <= 9; i++) {
			StringBuilder number = new StringBuilder();
			int remaining = i;
			boolean[] was = new boolean[100];
			while (true) {
				if (was[remaining])
					break;
				was[remaining] = true;
				int current = remaining / n;
				number.append(current);
				remaining %= n;
				remaining *= 10;
				remaining += current;
			}
			BigInteger current = new BigInteger(number.toString());
			BigInteger multiply = current.divide(BigInteger.TEN).add(BigInteger.TEN.pow(number.length() - 1).multiply(current.mod(BigInteger.TEN)));
			if (!multiply.mod(current).equals(BigInteger.ZERO))
				continue;
			if (result == null || current.compareTo(result) < 0)
				result = current;
		}
		out.println(result);
	}
}

