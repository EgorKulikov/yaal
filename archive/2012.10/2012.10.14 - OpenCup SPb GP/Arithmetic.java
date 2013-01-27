package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Arithmetic {
	private int[] a;
	int[][] current;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count >= 7) {
			out.printLine(0);
			return;
		}
//		a = new int[7];
//		go(0, 1);
		int[] numbers = IOUtils.readIntArray(in, count);
		current = new int[count][];
		for (int i = 1; i < count; i++)
			current[i] = new int[count - i];
		current[0] = numbers.clone();
		out.printLine(calculate(0));
	}

	private int calculate(int step) {
		if (step == current.length - 1)
			return current[step][0];
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < current[step].length; i++) {
			for (int j = i + 1; j < current[step].length; j++) {
				System.arraycopy(current[step], 0, current[step + 1], 1, i);
				System.arraycopy(current[step], i + 1, current[step + 1], i + 1, j - i - 1);
				System.arraycopy(current[step], j + 1, current[step + 1], j, current[step].length - j - 1);
				current[step + 1][0] = current[step][i] + current[step][j];
				result = Math.min(result, calculate(step + 1));
				current[step + 1][0] = Math.abs(current[step][i] - current[step][j]);
				result = Math.min(result, calculate(step + 1));
				current[step + 1][0] = current[step][i] * current[step][j];
				result = Math.min(result, calculate(step + 1));
			}
		}
		return result;
	}

	private void go(int step, int min) {
		if (step == 7) {
			if (!go2(0, 0, false)) {
				System.err.println(Array.wrap(a).toString());
				throw new RuntimeException();
			}
			return;
		}
		for (int i = min; i <= 30; i++) {
			a[step] = i;
			go(step + 1, i + 1);
		}
	}

	private boolean go2(int step, int sum, boolean done) {
		if (step == 7)
			return sum == 0 && done;
		return go2(step + 1, sum, done) || go2(step + 1, sum - a[step], true) || go2(step + 1, sum + a[step], true);
	}
}
