package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] differences = IOUtils.readIntArray(in, count);
		int answer = 1;
		for (int i = 1; i < count; i++) {
			if (differences[i] == 1 && differences[i - 1] == 2 ||
				differences[i - 1] == 1 && differences[i] == 2) {
				answer++;
			}
		}
		if (differences[count - 1] != 0) answer *= 2;
		out.printLine(answer);
    }
}
