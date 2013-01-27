import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		double[] probabilities = IOUtils.readDoubleArray(in, count + 1);
		double best = 0;
		int lastBest = 0;
		double[] expectation = new double[5 * count + 1];
		double[] nextExpectation = new double[5 * count + 1];
		Arrays.fill(expectation, Double.NEGATIVE_INFINITY);
		expectation[5 * count] = 0;
		for (int i = 0; i <= lastBest + count; i++) {
			for (int j = 0; j <= 5 * count; j++) {
				nextExpectation[j] = Double.NEGATIVE_INFINITY;
				for (int k = 0; k <= count; k++) {
					int index = j + count - 2 * k;
					if (index >= 0 && index <= 5 * count)
						nextExpectation[j] = Math.max(nextExpectation[j], expectation[index] + probabilities[k]);
				}
				double current = nextExpectation[j] / (i + 1);
				if (current > best + 1e-14) {
					best = current;
					lastBest = i;
				}
			}
			double[] temp = expectation;
			expectation = nextExpectation;
			nextExpectation = temp;
		}
		out.printf("%.12f\n", best);
	}
}

