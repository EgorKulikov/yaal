package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int RIGHT = 3;
	int LEFT = 1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int treasureCount = 0;
		int bombCount = 0;
		int sRow = -1;
		int sColumn = -1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				char c = table[i][j];
				if (Character.isDigit(c))
					treasureCount = Math.max(treasureCount, c - '0');
				else if (c == 'B')
					bombCount++;
				else if (c == 'S') {
					sRow = i;
					sColumn = j;
				}
			}
		}
		table[sRow][sColumn] = '.';
		int[] value = IOUtils.readIntArray(in, treasureCount);
		int[][][] change = new int[4][rowCount][columnCount];
		int bombIndex = treasureCount;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int curIndex = -1;
				if (Character.isDigit(table[i][j]))
					curIndex = table[i][j] - '1';
				else if (table[i][j] == 'B')
					curIndex = bombIndex++;
				if (curIndex == -1)
					continue;
				for (int k = 0; k < i; k++) {
					change[RIGHT][k][j] += 1 << curIndex;
					if (j + 1 < columnCount)
						change[LEFT][k][j + 1] += 1 << curIndex;
				}
			}
		}
		int count = treasureCount + bombCount;
		int[][][] cost = new int[rowCount][columnCount][1 << count];
		ArrayUtils.fill(cost, -1);
		cost[sRow][sColumn][0] = 0;
		int[] rowQueue = new int[rowCount * columnCount * (1 << count)];
		int[] columnQueue = new int[rowQueue.length];
		int[] maskQueue = new int[rowQueue.length];
		rowQueue[0] = sRow;
		columnQueue[0] = sColumn;
		int length = 1;
		for (int i = 0; i < length; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			int mask = maskQueue[i];
			for (int j = 0; j < 4; j++) {
				int nRow = row + MiscUtils.DX4[j];
				int nColumn = column + MiscUtils.DY4[j];
				int nMask = mask ^ change[j][row][column];
				if (!MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) || table[nRow][nColumn] != '.' ||
					cost[nRow][nColumn][nMask] != -1)
				{
					continue;
				}
				cost[nRow][nColumn][nMask] = cost[row][column][mask] + 1;
				rowQueue[length] = nRow;
				columnQueue[length] = nColumn;
				maskQueue[length++] = nMask;
			}
		}
		int answer = 0;
		for (int i = 0; i < (1 << treasureCount); i++) {
			if (cost[sRow][sColumn][i] != -1) {
				int candidate = -cost[sRow][sColumn][i];
				for (int j = 0; j < treasureCount; j++) {
					if ((i >> j & 1) == 1)
						candidate += value[j];
				}
				answer = Math.max(answer, candidate);
			}
		}
		out.printLine(answer);
    }
}
