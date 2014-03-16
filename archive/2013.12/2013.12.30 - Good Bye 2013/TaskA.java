package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int initial = in.readInt();
		int ratio = in.readInt();
		int answer = 0;
		while (initial >= ratio) {
			int additional = initial / ratio;
			answer += additional * ratio;
			initial %= ratio;
			initial += additional;
		}
		answer += initial;
		out.printLine(answer);
    }
}
