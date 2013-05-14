package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = 0;
		for (int i = 1; i < count; i++)
			answer += (count - i) * i;
		answer += count;
		out.printLine(answer);
    }
}
