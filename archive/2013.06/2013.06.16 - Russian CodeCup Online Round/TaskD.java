package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	int[] current;
	OutputWriter out;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		this.out = out;
		int count = in.readInt();
		boolean fromStart = true;
		current = new int[count];
		for (int i = 0; i <= count; i++) {
			go(0, count - 1, fromStart, i);
			fromStart = !fromStart;
		}
    }

	private void go(int from, int to, boolean fromStart, int remaining) {
		Arrays.fill(current, from, to + 1, 1);
		if (remaining == 0) {
			for (int i : current)
				out.print(i);
			out.printLine();
			return;
		}
		if (fromStart) {
			for (int i = from; i + remaining - 1 <= to; i++) {
				current[i] = 0;
				go(i + 1, to, fromStart, remaining - 1);
				current[i] = 1;
				fromStart = !fromStart;
			}
		} else {
			for (int i = to; i - remaining + 1 >= from; i--) {
				current[i] = 0;
				go(from, i - 1, fromStart, remaining - 1);
				current[i] = 1;
				fromStart = !fromStart;
			}
		}
	}
}
