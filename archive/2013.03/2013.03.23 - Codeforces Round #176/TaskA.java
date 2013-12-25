package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if ((count & 3) >= 2) {
			out.printLine(-1);
			return;
		}
		int[] answer = new int[count];
		if ((count & 1) == 1)
			answer[count >> 1] = count >> 1;
		for (int i = 0; i < (count >> 1); i += 2) {
			answer[i] = i + 1;
			answer[i + 1] = count - i - 1;
			answer[count - i - 1] = count - i - 2;
			answer[count - i - 2] = i;
		}
		for (int i = 0; i < count; i++)
			answer[i]++;
		out.printLine(answer);
    }
}
