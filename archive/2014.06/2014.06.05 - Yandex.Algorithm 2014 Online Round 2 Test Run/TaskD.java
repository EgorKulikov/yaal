package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int inversions = in.readInt();
		int count;
		for (count = 1; count * (count - 1) / 2 < inversions; count++);
		int first = 1 + inversions - (count - 1) * (count - 2) / 2;
		int[] answer = new int[count];
		answer[0] = first;
		int j = 1;
		for (int i = count; i > 0; i--) {
			if (i == first)
				continue;
			answer[j++] = i;
		}
		out.printLine(count);
		out.printLine(answer);
    }
}
