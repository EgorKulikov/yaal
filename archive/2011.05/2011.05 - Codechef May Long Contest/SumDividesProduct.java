import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class SumDividesProduct implements Solver {
	private int[][] primeDivisors;

	public SumDividesProduct() {
		int[] divisors = IntegerUtils.generateDivisorTable(40000);
		primeDivisors = new int[40000][];
		primeDivisors[1] = new int[0];
		for (int i = 2; i < 40000; i++) {
			int number = i;
			int divisor = divisors[i];
			do
				number /= divisor;
			while (number % divisor == 0);
			primeDivisors[i] = new int[primeDivisors[number].length + 1];
			System.arraycopy(primeDivisors[number], 0, primeDivisors[i], 0, primeDivisors[number].length);
			primeDivisors[i][primeDivisors[i].length - 1] = divisor;
		}
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] start = new int[100000];
		int[] end = new int[100000];
		int last = 0;
		int size = 0;
		while (last != n) {
			start[size] = last + 1;
			end[size] = n / (n / start[size]);
			last = end[size++];
		}
		start = Arrays.copyOf(start, size);
		end = Arrays.copyOf(end, size);
		long[] count = new long[size];
		for (int i = 2; i * (i + 1) <= n; i++) {
			for (int j = 0; j < (1 << primeDivisors[i].length); j++) {
				int step = i;
				int sign = 1;
				for (int k = 0; k < primeDivisors[i].length; k++) {
					if ((j >> k & 1) == 1) {
						step *= primeDivisors[i][k];
						sign = -sign;
					}
				}
				add(i * i + step, Math.min(2 * i * i - step, (n / step) * step), step, sign, start, end, count);
			}
		}
		long result = 0;
		for (int i = 0; i < size; i++)
			result += count[i] * (n / start[i]);
		out.println(result);
	}

	private void add(int from, int to, int step, int sign, int[] start, int[] end, long[] count) {
		if (from > to)
			return;
		int index = Arrays.binarySearch(end, from);
		if (index < 0)
			index = -index - 1;
		while (index < start.length && from <= to) {
			int current = Math.min((end[index] / step) * step, to);
			if (current < from) {
				index++;
				continue;
			}
			count[index++] += sign * ((current - from + step) / step);
			from = current + step;
		}

	}
}

