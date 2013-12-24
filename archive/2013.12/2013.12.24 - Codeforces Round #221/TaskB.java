package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int[] perLength = new int[columnCount + 1];
		perLength[0] = rowCount;
		int[] length = new int[rowCount];
		int answer = 0;
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				perLength[length[j]]--;
				if (table[j][i] == '1')
					length[j]++;
				else
					length[j] = 0;
				perLength[length[j]]++;
			}
			int current = 0;
			for (int j = columnCount; j >= 0; j--) {
				current += perLength[j];
				answer = Math.max(answer, current * j);
			}
		}
		out.printLine(answer);
    }
}
