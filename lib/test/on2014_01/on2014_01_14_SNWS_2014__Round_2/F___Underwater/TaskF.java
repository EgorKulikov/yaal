package on2014_01.on2014_01_14_SNWS_2014__Round_2.F___Underwater;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
	private int rowCount;
	private int columnCount;
	private char[][] map;
	private boolean[][] was;
	private int baseKeys;
	private int[] rowQueue;
	private int[] columnQueue;
	private int[][] doorRows;
	private int[][] doorColumns;
	private int[] doorQty = new int[26];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		rowCount = in.readInt();
		columnCount = in.readInt();
		map = IOUtils.readTable(in, rowCount, columnCount);
		char[] keys = in.readString().toCharArray();
		was = new boolean[rowCount][columnCount];
		baseKeys = 0;
		int answer = 0;
		rowQueue = new int[rowCount * columnCount];
		columnQueue = new int[rowCount * columnCount];
		doorRows = new int[26][rowCount * columnCount];
		doorColumns = new int[26][rowCount * columnCount];
		for (char c : keys) {
			if (Character.isLetter(c))
				baseKeys |= 1 << (c - 'a');
		}
		answer = solve();
		out.printLine(answer);
    }

	private int solve() {
		Arrays.fill(doorQty, 0);
		int size = 0;
		for (int i = 0; i < rowCount; i++) {
			size = check(i, 0, size);
			size = check(i, columnCount - 1, size);
		}
		for (int i = 1; i < columnCount - 1; i++) {
			size = check(0, i, size);
			size = check(rowCount - 1, i, size);
		}
		int curKeys = baseKeys;
		int result = 0;
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			if (map[row][column] == '$')
				result++;
			if (Character.isLowerCase(map[row][column])) {
				int id = map[row][column] - 'a';
				if ((curKeys >> id & 1) == 0) {
					curKeys += 1 << id;
					System.arraycopy(doorRows[id], 0, rowQueue, size, doorQty[id]);
					System.arraycopy(doorColumns[id], 0, columnQueue, size, doorQty[id]);
					size += doorQty[id];
				}
			}
			for (int j = 0; j < 4; j++) {
				int nRow = row + MiscUtils.DX4[j];
				int nColumn = column + MiscUtils.DY4[j];
				if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && map[nRow][nColumn] != '*' && !was[nRow][nColumn]) {
					was[nRow][nColumn] = true;
					if (Character.isUpperCase(map[nRow][nColumn]) && ((curKeys >> (map[nRow][nColumn] - 'A')) & 1) == 0) {
						int id = map[nRow][nColumn] - 'A';
						doorRows[id][doorQty[id]] = nRow;
						doorColumns[id][doorQty[id]++] = nColumn;
					} else {
						rowQueue[size] = nRow;
						columnQueue[size++] = nColumn;
					}
				}
			}
		}
		return result;
	}

	private int check(int row, int column, int size) {
		if (map[row][column] == '*')
			return size;
		if (Character.isUpperCase(map[row][column]) && ((baseKeys >> (map[row][column] - 'A')) & 1) == 0) {
			int id = map[row][column] - 'A';
			doorRows[id][doorQty[id]] = row;
			doorColumns[id][doorQty[id]++] = column;
			return size;
		}
		rowQueue[size] = row;
		columnQueue[size] = column;
		was[row][column] = true;
		return size + 1;
	}
}
