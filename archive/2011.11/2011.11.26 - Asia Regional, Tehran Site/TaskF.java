package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[] rows = IOUtils.readIntArray(in, rowCount);
		int[] columns = IOUtils.readIntArray(in, columnCount);
		long answer = 0;
		Arrays.sort(rows);
		Arrays.sort(columns);
		for (int i = 0; i < rowCount; i++) {
			answer += rows[i];
			for (int j = columnCount - rows[i]; j < columnCount; j++)
				columns[j]--;
			Arrays.sort(columns);
		}
		for (int i : columns) {
			if (i > 0)
				answer += i;
		}
		out.printLine(answer);
	}
}
