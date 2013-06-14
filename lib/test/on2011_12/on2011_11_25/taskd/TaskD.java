package on2011_12.on2011_11_25.taskd;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		char[][] first = new char[(rowCount + columnCount) / 2][(rowCount + 1) / 2 + (columnCount + 1) / 2 - 1];
		int start = (rowCount + 1) / 2;
		for (int i = 0; i < rowCount + columnCount - 1; i += 2) {
			start--;
			for (int j = 0; j < columnCount && j <= i; j++) {
				if (i - j >= rowCount)
					continue;
				first[i / 2][j + start] = table[i - j][j];
			}
		}
		char[][] second = new char[(rowCount + columnCount - 1) / 2][rowCount / 2 + columnCount / 2];
		start = rowCount / 2;
		for (int i = 1; i < rowCount + columnCount - 1; i += 2) {
			start--;
			for (int j = 0; j < columnCount && j <= i; j++) {
				if (i - j >= rowCount)
					continue;
				second[i / 2][j + start] = table[i - j][j];
			}
		}
		if ((solve(first) ^ solve(second)) == 0)
			out.printLine("LOSE");
		else
			out.printLine("WIN");
	}

	private int[][][][] result;
	private char[][] table;
	private int[][] sequences;
	private int index = -1;

	private int solve(char[][] table) {
		if (table.length == 0)
			return 0;
		result = new int[table.length][table.length][table[0].length][table[0].length];
		ArrayUtils.fill(result, -1);
		this.table = table;
		sequences = new int[table.length + table[0].length + 5][table.length * table[0].length];
		return go(0, table.length - 1, 0, table[0].length - 1);
	}

	private int go(int fromRow, int toRow, int fromColumn, int toColumn) {
		if (fromRow > toRow || fromColumn > toColumn)
			return 0;
		if (result[fromRow][toRow][fromColumn][toColumn] != -1)
			return result[fromRow][toRow][fromColumn][toColumn];
		index++;
		int sequenceIndex = 0;
		for (int i = fromRow; i <= toRow; i++) {
			for (int j = fromColumn; j <= toColumn; j++) {
				if (table[i][j] == 'X')
					sequences[index][sequenceIndex++] = go(fromRow, i - 1, fromColumn, j - 1) ^ go(i + 1, toRow, fromColumn, j - 1) ^
						go(fromRow, i - 1, j + 1, toColumn) ^ go(i + 1, toRow, j + 1, toColumn);
				else if (table[i][j] == 'L')
					sequences[index][sequenceIndex++] = go(fromRow, i - 1, fromColumn, toColumn) ^ go(i + 1, toRow, fromColumn, toColumn);
				else if (table[i][j] == 'R')
					sequences[index][sequenceIndex++] = go(fromRow, toRow, fromColumn, j - 1) ^ go(fromRow, toRow, j + 1, toColumn);
			}
		}
		Arrays.sort(sequences[index], 0, sequenceIndex);
		if (sequenceIndex == 0 || sequences[index][0] != 0) {
			result[fromRow][toRow][fromColumn][toColumn] = 0;
		} else {
			for (int i = 1; i < sequenceIndex; i++) {
				if (sequences[index][i] > sequences[index][i - 1] + 1) {
					result[fromRow][toRow][fromColumn][toColumn] = sequences[index][i - 1] + 1;
					break;
				}
			}
			if (result[fromRow][toRow][fromColumn][toColumn] == -1)
				result[fromRow][toRow][fromColumn][toColumn] = sequences[index][sequenceIndex - 1] + 1;
		}
		index--;
		return result[fromRow][toRow][fromColumn][toColumn];
	}
}
