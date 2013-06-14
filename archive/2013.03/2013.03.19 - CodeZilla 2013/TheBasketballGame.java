package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheBasketballGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer;
		if (count % 2 == 0) {
			answer = calculate(count / 2 + 1, count % 6 / 2 + 1, 3);
			if (count >= 4)
				answer += calculate((count - 4) / 2 + 1, (count - 4) % 6 / 2 + 1, 3);
		} else {
			answer = calculate((count - 1) / 2 + 1, (count - 1) % 6 / 2 + 1, 3);
			if (count >= 3)
				answer += calculate((count - 3) / 2 + 1, (count - 3) % 6 / 2 + 1, 3);
		}
		out.printLine(answer % 100001);
    }

	private long calculate(int from, int to, int step) {
		long qty = (from - to) / step + 1;
		return (from + to) * qty / 2;
	}
}
