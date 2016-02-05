package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Remains {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int n = in.readInt();
		long answer = x + y;
		for (int i = 2; i < n; i++) {
			if (x >= 2 * y) {
				int steps = (n - i) / 3;
				if (y != 0) {
					steps = Math.min(steps, x / (2 * y));
				}
				if (steps != 0) {
					answer += 2L * x * steps - 2L * steps * steps * y;
					x -= 2 * steps * y;
					i += 3 * steps - 1;
					continue;
				}
			}
			int z = Math.abs(x - y);
			answer += z;
			x = y;
			y = z;
		}
		out.printLine(answer);
	}
}
