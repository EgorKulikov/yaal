package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readInt();
		long answer = 0;
		while (count != 1) {
			boolean found = false;
			for (int i = 2; i * i <= count; i++) {
				if (count % i == 0) {
					found = true;
					answer += i - 1;
					count -= count / i;
					break;
				}
			}
			if (!found) {
				answer += count - 1;
				count--;
			}
		}
		out.printLine(answer);
    }
}
