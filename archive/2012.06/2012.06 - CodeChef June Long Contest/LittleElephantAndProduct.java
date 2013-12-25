package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndProduct {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		out.printLine(go(from, to, 0, 0, (long)1e18, 18));
	}

	private int go(long from, long to, int count4, int count7, long ten, int step) {
		if (step == -1)
			return count4 * count7;
		if (from == 0 && to == 10 * ten - 1)
			return construct(count4, count7, step + 1);
		long fromDigit = from / ten;
		long toDigit = to / ten;
		if (fromDigit == toDigit)
			return go(from % ten, to % ten, count4 + (fromDigit == 4 ? 1 : 0), count7 + (fromDigit == 7 ? 1 : 0), ten / 10, step - 1);
		int result = 0;
		if (fromDigit < 4 && toDigit > 4)
			result = Math.max(result, construct(count4 + 1, count7, step));
		if (fromDigit < 7 && toDigit > 7)
			result = Math.max(result, construct(count4, count7 + 1, step));
		if (toDigit - fromDigit > 1 || from == 0 || to == 10 * ten - 1)
			result = Math.max(result, construct(count4, count7, step));
		result = Math.max(result, go(from % ten, ten - 1, count4 + (fromDigit == 4 ? 1 : 0), count7 + (fromDigit == 7 ? 1 : 0), ten / 10, step - 1));
		result = Math.max(result, go(0, to % ten, count4 + (toDigit == 4 ? 1 : 0), count7 + (toDigit == 7 ? 1 : 0), ten / 10, step - 1));
		return result;
	}

	private int construct(int count4, int count7, int remaining) {
		if (count4 < count7) {
			int delta = Math.min(count7 - count4, remaining);
			count4 += delta;
			remaining -= delta;
		} else {
			int delta = Math.min(count4 - count7, remaining);
			count7 += delta;
			remaining -= delta;
		}
		count4 += remaining / 2;
		count7 += (remaining + 1) / 2;
		return count4 * count7;
	}
}
