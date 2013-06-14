package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int perMan = in.readInt();
		int perWoman = in.readInt();
		int total = in.readInt();
		int answer = 0;
		for (int i = 1; i * perMan < total; i++)
			answer += (total - perMan * i - 1) / perWoman;
		out.printLine(answer);
	}
}
