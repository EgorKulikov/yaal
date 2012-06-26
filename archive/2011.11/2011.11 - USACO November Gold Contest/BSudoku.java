package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BSudoku {
	private int[][][][][] result;
	private char[][] table;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		table = IOUtils.readTable(in, 9, 9);
		result = new int[10][10][1 << 9][1 << 3][2];
		ArrayUtils.fill(result, -1);
		out.printLine(go(0, 0, 0, 0, 0));
	}

	private int go(int row, int column, int columnMask, int squareMask, int rowMask) {
		if (result[row][column][columnMask][squareMask][rowMask] != -1)
			return result[row][column][columnMask][squareMask][rowMask];
		if (row == 9) {
			if (columnMask != 0)
				return result[row][column][columnMask][squareMask][rowMask] = 81;
			return result[row][column][columnMask][squareMask][rowMask] = 0;
		}
		if (column == 9) {
			if (rowMask != 0)
				return result[row][column][columnMask][squareMask][rowMask] = 81;
			int newSquaresMask = squareMask;
			if (row % 3 == 2) {
				if (squareMask != 0)
					return result[row][column][columnMask][squareMask][rowMask] = 81;
				newSquaresMask = 0;
			}
			return result[row][column][columnMask][squareMask][rowMask] = go(row + 1, 0, columnMask, newSquaresMask, 0);
		}
		return result[row][column][columnMask][squareMask][rowMask] = Math.min(
			go(row, column + 1, columnMask, squareMask, rowMask) + (table[row][column] == '1' ? 1 : 0),
			go(row, column + 1, columnMask ^ (1 << column), squareMask ^ (1 << (column / 3)), 1 - rowMask) +
				(table[row][column] == '1' ? 0 : 1)
		);
	}
}
