package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private String[] answer = new String[1303];

	{
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 31; j++)
				answer[i * 31 + j * 12] = "" + (j / 10) + (j % 10) + "/" + (i / 10) + (i % 10);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(answer[in.readInt()]);
	}
}
