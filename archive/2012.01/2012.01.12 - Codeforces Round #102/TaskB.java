package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount == 1 || columnCount == 1) {
			out.printLine(rowCount * columnCount);
			return;
		}
		int answer = 0;
		int black = 0;
		if (columnCount % 4 == 0)
			black = columnCount / 2;
		else
			black = columnCount / 2 + 1;
		for (int i = 0; i < rowCount; i += 2) {
			int current = i / 2 % 2 == 0 ? black : columnCount - black;
			if (i + 1 != rowCount)
				current *= 2;
			answer += current;
		}
		out.printLine(answer);
	}
}
