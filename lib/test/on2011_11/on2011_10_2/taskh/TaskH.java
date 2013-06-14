package on2011_11.on2011_10_2.taskh;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] garden = IOUtils.readTable(in, rowCount, columnCount);
		int queenRow = in.readInt() - 1;
		int queenColumn = in.readInt() - 1;
		int time = in.readInt();
		int[] friendRow = new int[4];
		int[] friendColumn = new int[4];
		int[] count = new int[4];
		IOUtils.readIntArrays(in, friendRow, friendColumn, count);
		MiscUtils.decreaseByOne(friendColumn, friendRow);
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int[][] distance = new int[rowCount][columnCount];
		ArrayUtils.fill(distance, Integer.MAX_VALUE);
		int size = 1;
		rowQueue[0] = queenRow;
		columnQueue[0] = queenColumn;
		distance[queenRow][queenColumn] = 0;
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			for (int j = 0; j < 4; j++) {
				int newRow = row + MiscUtils.DX4[j];
				int newColumn = column + MiscUtils.DY4[j];
				if (garden[newRow][newColumn] == '0') {
					garden[newRow][newColumn] = '1';
					distance[newRow][newColumn] = distance[row][column] + 1;
					rowQueue[size] = newRow;
					columnQueue[size++] = newColumn;
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			if (distance[friendRow[i]][friendColumn[i]] <= time)
				answer += count[i];
		}
		out.printLine(answer);
	}
}
