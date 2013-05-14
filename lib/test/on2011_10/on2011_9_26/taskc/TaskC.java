package on2011_10.on2011_9_26.taskc;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] labyrinth = IOUtils.readTable(in, rowCount, columnCount);
		int[] queueRow = new int[rowCount * columnCount];
		int[] queueColumn = new int[rowCount * columnCount];
		int[][] distance = new int[rowCount][columnCount];
		boolean[][] visited = new boolean[rowCount][columnCount];
		queueRow[0] = rowCount - 2;
		queueColumn[0] = columnCount - 2;
		int size = 1;
		for (int i = 0; i < size; i++) {
			int row = queueRow[i];
			int column = queueColumn[i];
			for (int j = 0; j < 4; j++) {
				int newRow = row + MiscUtils.DX4[j];
				int newColumn = column + MiscUtils.DY4[j];
				if (labyrinth[newRow][newColumn] != '#' && !visited[newRow][newColumn]) {
					visited[newRow][newColumn] = true;
					distance[newRow][newColumn] = distance[row][column] + 1;
					queueRow[size] = newRow;
					queueColumn[size++] = newColumn;
				}
			}
		}
		int tigerRow = -1;
		int tigerColumn = -1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (labyrinth[i][j] == 'T') {
					tigerRow = i;
					tigerColumn = j;
				}
			}
		}
		if (distance[1][1] < distance[tigerRow][tigerColumn])
			out.println("Yes");
		else
			out.println("No");
	}
}
