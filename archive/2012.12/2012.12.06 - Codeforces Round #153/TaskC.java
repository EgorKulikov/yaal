package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	static final int STEP = 360360;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		int movesBound = in.readInt();
		long movesPerStep = 0;
		{
			long a = STEP;
			long b = 0;
			long answer = 0;
			while (a > b) {
				long next = a - 1;
				for (int i = 2; i <= movesBound; i++) {
					long candidate = a - a % i;
					if (candidate >= b)
						next = Math.min(next, candidate);
				}
				movesPerStep++;
				a = next;
			}
		}
		long answer = 0;
		while (from > to && from % STEP != 0) {
			long next = from - 1;
			for (int i = 2; i <= movesBound; i++) {
				long candidate = from - from % i;
				if (candidate >= to)
					next = Math.min(next, candidate);
			}
			answer++;
			from = next;
		}
		if (from == to) {
			out.printLine(answer);
			return;
		}
		long next = ((to - 1) / STEP + 1) * STEP;
		answer += (from - next) / STEP * movesPerStep;
		from = next;
		while (from > to) {
			next = from - 1;
			for (int i = 2; i <= movesBound; i++) {
				long candidate = from - from % i;
				if (candidate >= to)
					next = Math.min(next, candidate);
			}
			answer++;
			from = next;
		}
		out.printLine(answer);
	}
}
