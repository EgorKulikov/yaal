package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int time = in.readInt();
		int[] length = IOUtils.readIntArray(in, count);
		int j = 0;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			while (j < count && time >= length[j])
				time -= length[j++];
			answer = Math.max(answer, j - i);
			time += length[i];
		}
		out.printLine(answer);
	}
}
