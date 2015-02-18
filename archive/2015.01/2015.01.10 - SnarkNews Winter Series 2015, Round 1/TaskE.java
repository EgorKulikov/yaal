package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int required = in.readInt();
		int waterCount = in.readInt();
		boolean[][] isWater = new boolean[rowCount][columnCount];
		for (int i = 0; i < waterCount; i++) {
			int row = 0;
			int column = 0;
			isWater[0][0] = true;
			char[] canal = in.readString().toCharArray();
			for (char c : canal) {
				if (c == 'd') row++;
				if (c == 'u') row--;
				if (c == 'l') column--;
				if (c == 'r') column++;
				isWater[row][column] = true;
			}
		}
		int answer = 0;
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (isWater[i][j]) continue;
				rowQueue[0] = i;
				columnQueue[0] = j;
				isWater[i][j] = true;
				int size = 1;
				for (int k = 0; k < size; k++) {
					int row = rowQueue[k];
					int column = columnQueue[k];
					for (int l = 0; l < 4; l++) {
						int nRow = row + MiscUtils.DX4[l];
						int nColumn = column + MiscUtils.DY4[l];
						if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) && !isWater[nRow][nColumn]) {
							isWater[nRow][nColumn] = true;
							rowQueue[size] = nRow;
							columnQueue[size++] = nColumn;
						}
					}
				}
				answer += size >= required ? 1 : 0;
			}
		}
		out.printLine(answer);
    }
}
