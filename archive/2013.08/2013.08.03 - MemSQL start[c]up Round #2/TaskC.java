package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int[][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int done = in.readInt();
		int[] row = new int[done];
		int[] column = new int[done];
		IOUtils.readIntArrays(in, row, column);
		MiscUtils.decreaseByOne(row, column);
		result = new int[3][3][rowCount + 1];
		ArrayUtils.fill(result, -1);
		ArrayUtils.orderBy(row, column);
		int lastRow = -1;
		int type = 2;
		int answer = 0;
		for (int i = 0; i < done; i++) {
			answer ^= solve(row[i] - lastRow - 1, type, column[i]);
			lastRow = row[i];
			type = column[i];
		}
		answer ^= solve(rowCount - lastRow - 1, type, 2);
		out.printLine(answer != 0 ? "WIN" : "LOSE");
    }

	private int solve(int length, int firstType, int secondType) {
		if (result[firstType][secondType][length] != -1)
			return result[firstType][secondType][length];
		IntSet has = new IntHashSet();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 2; j++) {
				if (i == 0 && j == firstType || i == length - 1 && j == secondType)
					continue;
				has.add(solve(i, firstType, 1 - j) ^ solve(length - i - 1, 1 - j, secondType));
			}
		}
		for (int i = 0; ; i++) {
			if (!has.contains(i))
				return result[firstType][secondType][length] = i;
		}
	}
}
