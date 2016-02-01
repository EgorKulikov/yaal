package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = IOUtils.readIntArray(in, 2 * n);
		Arrays.sort(x);
		double left = -2e6;
		double right = 2e6;
		for (int i = 0; i < 100; i++) {
			double midLeft = (2 * left + right) / 3;
			double midRight = (2 * right + left) / 3;
			double leftValue = calculate(x, midLeft);
			double rightValue = calculate(x, midRight);
			if (leftValue < rightValue) {
				right = midRight;
			} else {
				left = midLeft;
			}
		}
		out.printLine(calculate(x, (left + right) / 2));
	}

	private double calculate(int[] x, double start) {
		double result = 0;
		for (int i = 0; i < x.length; i++) {
			double delta = x[i] - (start + i);
			result += Math.sqrt(delta * delta + 1);
		}
		return result;
	}
}
