package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sum = in.readInt();
		if (count / 2 > sum || count == 1 && sum != 0) {
			out.printLine(-1);
			return;
		}
		if (count == 1) {
			out.printLine(1);
			return;
		}
		int[] answer = new int[count];
		for (int i = 0; i < count - 2 - count % 2; i++)
			answer[i] = i + 1;
		int remaining = sum - count / 2 + 1;
		int first = (int) 5e8;
		first -= first % remaining;
		answer[count - 2 - count % 2] = first;
		answer[count - 1 - count % 2] = first + remaining;
		if (count % 2 == 1)
			answer[count - 1] = count;
		out.printLine(answer);
    }
}
