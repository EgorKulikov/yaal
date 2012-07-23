package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] speciality = IOUtils.readIntTable(in, rowCount, columnCount);
		Pair<Integer, Long> first = go(speciality);
		int[][] transposedSpeciality = new int[columnCount][rowCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				transposedSpeciality[j][i] = speciality[i][j];
		}
		Pair<Integer, Long> second = go(transposedSpeciality);
		out.printLine((first.second + second.second) * 4);
		out.printLine(first.first, second.first);
	}

	private Pair<Integer, Long> go(int[][] speciality) {
		long[] sumRows = new long[speciality.length];
		for (int i = 0; i < speciality.length; i++)
			sumRows[i] = ArrayUtils.sumArray(speciality[i]);
		long best = Long.MAX_VALUE;
		int row = -1;
		for (int i = 0; i <= speciality.length; i++) {
			long current = 0;
			long position = 2 * i - 1;
			for (int j = 0; j < speciality.length; j++) {
				current += sqr(2 * j - position) * sumRows[j];
			}
			if (current < best) {
				best = current;
				row = i;
			}
		}
		return Pair.makePair(row, best);
	}

	private long sqr(long x) {
		return x * x;
	}
}
