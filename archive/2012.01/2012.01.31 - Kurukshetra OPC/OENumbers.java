package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class OENumbers {
	int[][] total = new int[9][145];
	int[] ten = new int[9];

	{
		ten[0] = 1;
		for (int i = 1; i < 9; i++)
			ten[i] = ten[i - 1] * 10;
		int[][] sum = new int[9][145];
		sum[0][72] = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 72 - 9 * i; j <= 72 + 9 * i; j++) {
				for (int k = 0; k < 10; k++) {
					if (k % 2 == 0)
						sum[i + 1][j - k] += sum[i][j];
					else
						sum[i + 1][j + k] += sum[i][j];
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			int curSum = 0;
			for (int j = 0; j < 145; j++) {
				total[i][j] = curSum;
				curSum += sum[i][j];
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		out.printLine(count(to + 1) - count(from));
	}

	private int count(int to) {
		int result = 0;
		int delta = 72;
		for (int i = 8; i >= 0; i--) {
			for (int j = 0; j < to / ten[i]; j++) {
				result += total[i][delta + (j % 2 == 0 ? j : -j)];
			}
			delta += (to / ten[i] % 2 == 0) ? to / ten[i] : -to / ten[i];
			to %= ten[i];
		}
		return result;
	}
}
