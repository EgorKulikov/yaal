package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int empty = in.readInt();
		long answer = 0;
		int N = count + 1;
		int end = count - empty + 1;
		for (int i = empty + 1; 2 * i <= count + 1; i++) {
//			if (i % 1000 == 0)
//				System.err.println(i);
//			long left = empty + 1;
//			long right = end - 1;
//			while (left < right) {
//				long middle = (left + right) >> 1;
//				if (value(N, i, middle, empty + 1) >= 0)
//					left = middle + 1;
//				else
//					right = middle;
//			}
			int k = empty + 1;
			int value = value(N, i, k, empty + 1);
			int sum = i + empty + 1;
			int current = 0;
			int x = count - empty;
			for (int j = empty + 1; j <= x; j++) {
				if (value >= 0) {
					if (value < sum) {
						k++;
						value -= sum;
					} else {
						int delta = value / sum + 1;
						k += delta;
						value -= delta * sum;
					}
				}
				if (k >= end)
					break;
				current += end - k;
				sum++;
				value += (N << 1) - (i + k);
			}
			if (2 * i != count + 1)
				current *= 2;
			answer += current;
		}
		answer *= 3;
		out.printLine(answer);
    }

	private int value(int n, int i, int k, int j) {
		return 2 * n * j - j * k - j * i - n * i - i * k + 2 * i * i;
	}
}
