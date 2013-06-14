package on2012_10.on2012_10_28_.TaskG;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int count = 0;
		int square = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == '0')
					continue;
				rowQueue[0] = i;
				columnQueue[0] = j;
				map[i][j] = '0';
				int size = 1;
				for (int k = 0; k < size; k++) {
					int row = rowQueue[k];
					int column = columnQueue[k];
					for (int l = 0; l < 4; l++) {
						int nextRow = row + MiscUtils.DX4[l];
						int nextColumn = column + MiscUtils.DY4[l];
						if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount && map[nextRow][nextColumn] == '1') {
							map[nextRow][nextColumn] = '0';
							rowQueue[size] = nextRow;
							columnQueue[size++] = nextColumn;
						}
					}
				}
				count++;
				square = Math.max(square, size);
			}
		}
		out.printLine(count, square);
	}
}
