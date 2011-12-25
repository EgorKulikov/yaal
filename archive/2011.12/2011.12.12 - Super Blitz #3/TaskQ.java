package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskQ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int[] row = new int[count];
		int[] column = new int[count];
		IOUtils.readIntArrays(in, row, column);
		MiscUtils.decreaseByOne(row, column);
		Set<Pair<Integer, Integer>> set = new HashSet<Pair<Integer, Integer>>();
		for (int i = 0; i < count; i++)
			set.add(Pair.makePair(row[i], column[i]));
		char[][] result = new char[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (set.contains(Pair.makePair(i, j))) {
					result[i][j] = '*';
					continue;
				}
				result[i][j] = '0';
				for (int k = 0; k < 8; k++) {
					if (set.contains(Pair.makePair(i + MiscUtils.DX8[k], j + MiscUtils.DY8[k])))
						result[i][j]++;
				}
			}
		}
		for (char[] resultRow : result)
			out.printLine(Array.wrap(resultRow).toArray());
	}
}
