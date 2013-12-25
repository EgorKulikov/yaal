package net.egork;

public class HyperKnight {
	public long countCells(int a, int b, int numRows, int numColumns, int k) {
		long answer = 0;
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		for (int i = 0; i < 5; i++) {
			long minRow, maxRow;
			if (i == 0) {
				minRow = 1;
				maxRow = Math.min(numRows, a);
			} else if (i == 1) {
				minRow = a + 1;
				maxRow = Math.min(numRows, b);
			} else if (i == 2) {
				minRow = b + 1;
				maxRow = Math.max(numRows - b, 0);
			} else if (i == 3) {
				minRow = Math.max(numRows - b + 1, 1);
				maxRow = numRows - a;
			} else {
				minRow = Math.max(numRows - a + 1, 1);
				maxRow = numRows;
			}
			if (minRow > maxRow)
				continue;
			for (int j = 0; j < 5; j++) {
				long minColumn, maxColumn;
				if (j == 0) {
					minColumn = 1;
					maxColumn = Math.min(numColumns, a);
				} else if (j == 1) {
					minColumn = a + 1;
					maxColumn = Math.min(numColumns, b);
				} else if (j == 2) {
					minColumn = b + 1;
					maxColumn = Math.max(numColumns - b, 0);
				} else if (j == 3) {
					minColumn = Math.max(numColumns - b + 1, 1);
					maxColumn = numColumns - a;
				} else {
					minColumn = Math.max(numColumns - a + 1, 1);
					maxColumn = numColumns;
				}
				if (minColumn > maxColumn)
					continue;
				int count = 0;
				for (int x = -1; x <= 1; x += 2) {
					for (int y = -1; y <= 1; y += 2) {
						long row = maxRow + x * a;
						long column = maxColumn + y * b;
						if (row > 0 && row <= numRows && column > 0 && column <= numColumns)
							count++;
						row = maxRow + x * b;
						column = maxColumn + y * a;
						if (row > 0 && row <= numRows && column > 0 && column <= numColumns)
							count++;
					}
				}
				if (count == k)
					answer += (maxRow - minRow + 1) * (maxColumn - minColumn + 1);
			}
		}
		return answer;
	}
}
