package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int rotated = in.readInt();
		int answer;
		if (total != rotated) {
			answer = Math.min(total, 2 * rotated);
		} else {
			answer = (total + 1) / 2;
		}
		out.printLine(answer);
    }
}
