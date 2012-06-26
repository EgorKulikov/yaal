package April2011.CodechefAprilLongContest;

import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class CentralPoint implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		if (n == 0) {
			Exit.exit(in, out);
			return;
		}
		int[] x = new int[n];
		int[] y = new int[n];
		in.readIntArrays(x, y);
		int left = -1000000000;
		int right = 1000000000;
		while (right - left > 10) {
			int centerLeft = left + (right - left) / 3;
			int centerRight = right - (right - left) / 3;
			double leftValue = calculateValue(centerLeft, x, y);
			double rightValue = calculateValue(centerRight, x, y);
			if (leftValue < rightValue)
				right = centerRight;
			else
				left = centerLeft;
		}
		double result = Double.POSITIVE_INFINITY;
		for (int i = left; i <= right; i++)
			result = Math.min(result, calculateValue(i, x, y));
		out.printf("%.6f\n", result);
	}

	private double calculateValue(int centerX, int[] x, int[] y) {
		int left = -1000000000;
		int right = 1000000000;
		while (right - left > 10) {
			int centerLeft = left + (right - left) / 3;
			int centerRight = right - (right - left) / 3;
			@SuppressWarnings({"SuspiciousNameCombination"})
			double leftValue = calculateValue(centerX, centerLeft, x, y);
			@SuppressWarnings({"SuspiciousNameCombination"})
			double rightValue = calculateValue(centerX, centerRight, x, y);
			if (leftValue < rightValue)
				right = centerRight;
			else
				left = centerLeft;
		}
		double result = Double.POSITIVE_INFINITY;
		for (int i = left; i <= right; i++)
			result = Math.min(result, calculateValue(centerX, i, x, y));
		return result;
	}

	private double calculateValue(int centerX, int centerY, int[] x, int[] y) {
		double result = 0;
		for (int i = 0; i < x.length; i++) {
			double dx = centerX - x[i];
			double dy = centerY - y[i];
			result += Math.sqrt(dx * dx + dy * dy);
		}
		return result;
	}
}

