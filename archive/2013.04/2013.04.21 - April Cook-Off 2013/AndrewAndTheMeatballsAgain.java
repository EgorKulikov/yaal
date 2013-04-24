package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AndrewAndTheMeatballsAgain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		if (required == 1)
			out.printLine(1);
		else if (required * 2 <= count) {
			int[] answer = new int[required];
			for (int i = 0; i < required; i++)
				answer[i] = 2 * i + 2;
			out.printLine(answer);
		} else
			out.printLine(-1);
    }
}
