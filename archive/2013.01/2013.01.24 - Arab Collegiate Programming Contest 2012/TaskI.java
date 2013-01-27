package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] hall = IOUtils.readIntTable(in, rowCount, columnCount);
		Set<Integer> answer = new EHashSet<Integer>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (hall[i][j] == hall[i][j - 1])
					answer.add(hall[i][j]);
			}
		}
		for (int i = 0; i < columnCount; i++) {
			for (int j = 1; j < rowCount; j++) {
				if (hall[j][i] == hall[j - 1][i])
					answer.add(hall[j][i]);
			}
		}
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (hall[i][j] == hall[i - 1][j - 1])
					answer.add(hall[i][j]);
			}
		}
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < columnCount - 1; j++) {
				if (hall[i][j] == hall[i - 1][j + 1])
					answer.add(hall[i][j]);
			}
		}
		answer.remove(-1);
		out.printLine(answer.size());
	}
}
