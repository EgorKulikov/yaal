package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long x = in.readLong();
		long y = in.readLong();
		long threshold = in.readLong();
		if (x >= threshold || y >= threshold) {
			out.printLine(0);
			return;
		}
		if (x <= 0 && y <= 0) {
			out.printLine(-1);
			return;
		}
		long answer = 0;
		if (x < 0) {
			answer = Math.abs(x) / y;
			x += answer * y;
		} else if (y < 0) {
			answer = Math.abs(y) / x;
			y += answer * x;
		}
		while (x < threshold && y < threshold) {
			if (x < y)
				x += y;
			else
				y += x;
			answer++;
		}
		out.printLine(answer);
    }
}
