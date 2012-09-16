package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted()) {
			throw new UnknownError();
		}
		int count = in.readInt();
		int[] start = IOUtils.readIntArray(in, count);
		int[] finish = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = 0; i < count; i++) {
			int delta = 0;
			for (int j = 0; j < count; j++) {
				if (start[j] == -1)
					continue;
				if (start[j] == finish[i]) {
					start[j] = -1;
					break;
				}
				delta++;
			}
			answer += delta;
		}
		out.printLine(answer);
	}
}
