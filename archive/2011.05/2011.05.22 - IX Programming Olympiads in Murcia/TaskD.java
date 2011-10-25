import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long[] result = new long[count + 1];
		result[0] = 1;
		long[][] c = IntegerUtils.generateBinomialCoefficients(count);
		for (int i = 1; i <= count; i++) {
			for (int j = 1; j <= i; j++) {
				for (int k = 0; j + k <= i; k++)
					result[i] += c[i - 1][j - 1] * c[i - j][k] * result[k] * result[i - j - k];
			}
		}
		out.println(result[count]);
	}
}

