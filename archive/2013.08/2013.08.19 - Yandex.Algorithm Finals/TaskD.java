package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long size = in.readLong();
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			long start = in.readLong() - 1;
			long end = in.readLong();
			long time = in.readLong();
			long answer = calculate(size, end, time) - calculate(size, start, time);
			out.printLine(answer);
		}
    }

	private long calculate(long size, long upTo, long time) {
		if (time <= size)
			return Math.min(time, upTo);
		if (2 * time / size >= size + 1) {
			long total = upTo * (upTo + 1) / 2;
			long delta = time - size * (size + 1) / 2;
			total += (delta / size) * upTo;
			delta %= size;
			total += Math.min(delta, upTo);
			return total;
		}
		long total = upTo;
		time -= size;
		long left = 0;
		long right = size - 1;
		while (left < right) {
			long middle = (left + right + 1) >> 1;
			if (2 * time / middle >= middle + 1)
				left = middle;
			else
				right = middle - 1;
		}
		long remaining = size - left;
		if (upTo >= remaining) {
			upTo -= remaining;
			total += upTo * (upTo + 1) / 2;
			time -= left * (left + 1) / 2;
			total += Math.min(upTo + 1, time);
		}
		return total;
	}
}
