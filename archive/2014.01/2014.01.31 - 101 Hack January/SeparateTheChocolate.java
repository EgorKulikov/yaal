package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SeparateTheChocolate {
//	long[][][][][] result;
//	char[][] table;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount * columnCount == 0) {
			out.printLine(1);
			return;
		}
		int delta = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		long answer = 0;
		long base = 0;
		long canChange = 0;
		long all = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'U')
					canChange += 1L << (i * 8 + j);
				if (map[i][j] == 'T')
					base += 1L << (i * 8 + j);
				all += 1L << (i * 8 + j);
			}
		}
		long[] masks = new long[(rowCount - 1) * (columnCount - 1)];
		for (int i = 0; i < rowCount - 1; i++) {
			for (int j = 0; j < columnCount - 1; j++)
				masks[i * (columnCount - 1) + j] = (3L << (i * 8 + j)) + (3L << ((i + 1) * 8 + j));
		}
		long current = canChange;
		int[] queue = new int[rowCount * columnCount];
		for (long mask : masks) {
			if (Long.bitCount(base & mask) == 4 || ((canChange + base) & mask) == 0) {
				out.printLine(0);
				return;
			}
		}
		while (true) {
			long toCheck = current + base;
			if (Math.abs(rowCount * columnCount - 2 * Long.bitCount(toCheck)) <= delta) {
				boolean good = true;
				for (long mask : masks) {
					if ((toCheck & mask) == 0 || Long.bitCount(toCheck & mask) == 4) {
						good = false;
						break;
					}
				}
				if (good && toCheck != 0) {
					queue[0] = Long.bitCount(Long.lowestOneBit(toCheck) - 1);
					long processed = 1L << queue[0];
					int size = 1;
					for (int i = 0; i < size; i++) {
						int row = queue[i] >> 3;
						int column = queue[i] & 7;
						for (int j = 0; j < 4; j++) {
							int nRow = row + MiscUtils.DX4[j];
							int nColumn = column + MiscUtils.DY4[j];
							int key = (nRow << 3) + nColumn;
							if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && (processed >> key & 1) == 0 &&
								(toCheck >> key & 1) == 1)
							{
								processed += 1L << key;
								queue[size++] = key;
							}
						}
					}
					if (processed != toCheck)
						good = false;
				}
				if (good && toCheck != all) {
					queue[0] = Long.bitCount(Long.lowestOneBit(toCheck ^ all) - 1);
					long processed = 1L << queue[0];
					int size = 1;
					for (int i = 0; i < size; i++) {
						int row = queue[i] >> 3;
						int column = queue[i] & 7;
						for (int j = 0; j < 4; j++) {
							int nRow = row + MiscUtils.DX4[j];
							int nColumn = column + MiscUtils.DY4[j];
							int key = (nRow << 3) + nColumn;
							if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && (processed >> key & 1) == 0 &&
								(toCheck >> key & 1) == 0)
							{
								processed += 1L << key;
								queue[size++] = key;
							}
						}
					}
					if (processed != (toCheck ^ all))
						good = false;
				}
				if (good)
					answer++;
			}
			if (current == 0)
				break;
			current = (current - 1) & canChange;
		}
		out.printLine(answer);
/*		long answer = 0;
		if (rowCount == 1) {
			for (int j = 0; j <= columnCount; j++) {
				if (Math.abs(2 * j - columnCount) > delta)
					continue;
				boolean good = true;
				for (int k = 0; k < j; k++) {
					if (map[0][k] == 'T')
						good = false;
				}
				for (int k = j; k < columnCount; k++) {
					if (map[0][k] == 'B')
						good = false;
				}
				if (good)
					answer++;
				if (j != 0 && j != columnCount) {
					good = true;
					for (int k = 0; k < columnCount - j; k++) {
						if (map[0][k] == 'T')
							good = false;
					}
					for (int k = columnCount - j; k < columnCount; k++) {
						if (map[0][k] == 'B')
							good = false;
					}
					if (good)
						answer++;
				}
			}
			out.printLine(answer);
			return;
		}
		if (columnCount == 1) {
			for (int j = 0; j <= rowCount; j++) {
				if (Math.abs(2 * j - rowCount) > delta)
					continue;
				boolean good = true;
				for (int k = 0; k < j; k++) {
					if (map[k][0] == 'T')
						good = false;
				}
				for (int k = j; k < rowCount; k++) {
					if (map[k][0] == 'B')
						good = false;
				}
				if (good)
					answer++;
				if (j != 0 && j != rowCount) {
					good = true;
					for (int k = 0; k < rowCount - j; k++) {
						if (map[k][0] == 'T')
							good = false;
					}
					for (int k = rowCount - j; k < rowCount; k++) {
						if (map[k][0] == 'B')
							good = false;
					}
					if (good)
						answer++;
				}
			}
			out.printLine(answer);
			return;
		}
		IntPair[] border = new IntPair[2 * rowCount + 2 * columnCount - 4];
		int at = 0;
		for (int i = 0; i < columnCount; i++)
			border[at++] = new IntPair(0, i);
		for (int i = 1; i < rowCount; i++)
			border[at++] = new IntPair(i, columnCount - 1);
		for (int i = columnCount - 2; i >= 0; i--)
			border[at++] = new IntPair(rowCount - 1, i);
		for (int i = rowCount - 2; i > 0; i--)
			border[at++] = new IntPair(i, 0);
		result = new long[1 << (columnCount - 2)][1 << (columnCount - 2)][rowCount][columnCount][rowCount * columnCount + 1];
		for (int i = 0; i < border.length; i++) {

		}*/
    }
}
