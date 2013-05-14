package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	long[] tenPower = IntegerUtils.generatePowers(10, 19, Long.MAX_VALUE);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long left = in.readLong();
		long right = in.readLong();
		long answer = count(right) - count(left - 1);
		out.printLine(answer);
	}

	private long count(long max) {
		long answer = 0;
		long ten = 1;
		int digitCount = 1;
		while (ten <= max && ten > 0) {
			for (int i = 1; i <= 9 && i * ten <= max; i++) {
				if ((i + 1) * ten - 1 <= max) {
					if (ten == 1)
						answer++;
					else
						answer += tenPower[digitCount - 2];
				} else if (max >= i * ten + i) {
					answer += 1 + (max - i * ten - i) / 10;
				}
			}
			ten *= 10;
			digitCount++;
		}
		return answer;
	}
}
