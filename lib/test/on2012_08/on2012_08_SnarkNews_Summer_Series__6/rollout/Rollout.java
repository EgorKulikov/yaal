package on2012_08.on2012_08_SnarkNews_Summer_Series__6.rollout;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Rollout {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int size = 0;
		int[][] distance = new int[rowCount][columnCount];
		ArrayUtils.fill(distance, -1);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'L') {
					rowQueue[size] = i;
					columnQueue[size++] = j;
					distance[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			for (int j = 0; j < 4; j++) {
				int nextRow = row + MiscUtils.DX4[j];
				int nextColumn = column + MiscUtils.DY4[j];
				if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount && distance[nextRow][nextColumn] == -1 && map[nextRow][nextColumn] != 'X') {
					distance[nextRow][nextColumn] = distance[row][column] + 1;
					rowQueue[size] = nextRow;
					columnQueue[size++] = nextColumn;
				}
			}
		}
		long answer = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'B') {
					if (distance[i][j] == -1) {
						out.printLine(-1);
						return;
					}
					answer += 2 * distance[i][j];
				}
			}
		}
		out.printLine(answer);
	}
}
