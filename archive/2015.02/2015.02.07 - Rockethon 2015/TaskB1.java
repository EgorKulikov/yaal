package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long index = in.readLong() - 1;
		int[] answer = new int[count];
		int start = 0;
		int end = count - 1;
		for (int i = 1; i < count; i++) {
			if (index < 1L << (count - i - 1)) {
				answer[start++] = i;
			} else {
				answer[end--] = i;
				index -= 1L << (count - i - 1);
			}
		}
		answer[start] = count;
		out.printLine(answer);
    }
}
