package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskO {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int answer = 0;
		while (second != 0) {
			answer += first / second;
			int temp = first % second;
			first = second;
			second = temp;
		}
		out.printLine(answer);
	}
}
