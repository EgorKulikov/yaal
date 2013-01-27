package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] answer = new long[count];
		Arrays.fill(answer, 1);
		int[][] gcd = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int g = in.readInt();
				gcd[i][j] = gcd[j][i] = g;
				answer[i] = IntegerUtils.lcm(answer[i], g);
				answer[j] = IntegerUtils.lcm(answer[j], g);
			}
		}
		for (int i = 1; i < count; i++) {
			long minMultiplier = answer[i - 1] / answer[i] + 1;
			for (long j = minMultiplier; ; j++) {
				boolean good = true;
				for (int k = 0; k < count; k++) {
					if (k != i && IntegerUtils.gcd(answer[k], answer[i] * j) != gcd[k][i]) {
						good = false;
						break;
					}
				}
				if (good) {
					answer[i] *= j;
					break;
				}
			}
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
