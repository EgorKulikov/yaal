package on2012_11.on2012_11_01_November_Challenge_2012.Candy_Collecting_Game;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CandyCollectingGame {
	private long[][][] rowSums;
	private long[][][] columnSums;
	private long[][][][] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long[][] candies = IOUtils.readLongTable(in, rowCount, columnCount);
		long total = 0;
		for (long[] row : candies) {
			for (long cell : row)
				total += cell;
		}
		rowSums = new long[rowCount][columnCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				rowSums[i][j][j] = candies[i][j];
				for (int k = j + 1; k < columnCount; k++)
					rowSums[i][j][k] = rowSums[i][j][k - 1] + candies[i][k];
			}
		}
		columnSums = new long[columnCount][rowCount][rowCount];
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				columnSums[i][j][j] = candies[j][i];
				for (int k = j + 1; k < rowCount; k++)
					columnSums[i][j][k] = columnSums[i][j][k - 1] + candies[k][i];
			}
		}
		answer = new long[rowCount][rowCount][columnCount][columnCount];
		ArrayUtils.fill(answer, -1);
		long bobCandy = alice(0, rowCount - 1, 0, columnCount - 1);
		long result = Math.max(bobCandy, total - bobCandy);
		if (bobCandy * 2 == total)
			result = total;
		out.printLine(result);
	}

	private long alice(int fromRow, int toRow, int fromColumn, int toColumn) {
		if (fromRow > toRow || fromColumn > toColumn)
			return 0;
		if (answer[fromRow][toRow][fromColumn][toColumn] != -1)
			return answer[fromRow][toRow][fromColumn][toColumn];
		long firstRow = rowSums[fromRow][fromColumn][toColumn];
		long lastRow = rowSums[toRow][fromColumn][toColumn];
		long firstColumn = columnSums[fromColumn][fromRow][toRow];
		long lastColumn = columnSums[toColumn][fromRow][toRow];
		if (firstRow <= lastRow && firstRow <= firstColumn && firstRow <= lastColumn)
			return answer[fromRow][toRow][fromColumn][toColumn] = bob(fromRow + 1, toRow, fromColumn, toColumn);
		if (lastRow <= firstColumn && lastRow <= lastColumn)
			return answer[fromRow][toRow][fromColumn][toColumn] = bob(fromRow, toRow - 1, fromColumn, toColumn);
		if (firstColumn <= lastColumn)
			return answer[fromRow][toRow][fromColumn][toColumn] = bob(fromRow, toRow, fromColumn + 1, toColumn);
		return answer[fromRow][toRow][fromColumn][toColumn] = bob(fromRow, toRow, fromColumn, toColumn - 1);
	}

	private long bob(int fromRow, int toRow, int fromColumn, int toColumn) {
		if (fromRow > toRow || fromColumn > toColumn)
			return 0;
		if (answer[fromRow][toRow][fromColumn][toColumn] != -1)
			return answer[fromRow][toRow][fromColumn][toColumn];
		long firstRow = rowSums[fromRow][fromColumn][toColumn] + alice(fromRow + 1, toRow, fromColumn, toColumn);
		long lastRow = rowSums[toRow][fromColumn][toColumn] + alice(fromRow, toRow - 1, fromColumn, toColumn);
		long firstColumn = columnSums[fromColumn][fromRow][toRow] + alice(fromRow, toRow, fromColumn + 1, toColumn);
		long lastColumn = columnSums[toColumn][fromRow][toRow] + alice(fromRow, toRow, fromColumn, toColumn - 1);
		return answer[fromRow][toRow][fromColumn][toColumn] = Math.max(Math.max(firstRow, lastRow), Math.max(firstColumn, lastColumn));
	}

}
