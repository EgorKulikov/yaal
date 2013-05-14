package on2013_01.on2013_01_19_SnarkNews_Winter_Series_Round__3.Way;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Way {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][][] times = new int[rowCount][columnCount][];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int segmentCount = in.readInt();
				times[i][j] = IOUtils.readIntArray(in, 2 * segmentCount);
			}
		}
		times[rowCount - 1][columnCount - 1] = new int[0];
		int[][] result = new int[rowCount][columnCount];
		boolean[][] processed = new boolean[rowCount][columnCount];
		ArrayUtils.fill(result, Integer.MAX_VALUE);
		result[0][0] = 0;
		for (int i = 0; i < rowCount * columnCount; i++) {
			int min = Integer.MAX_VALUE;
			int row = -1;
			int column = -1;
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (!processed[j][k] && result[j][k] < min) {
						min = result[j][k];
						row = j;
						column = k;
					}
				}
			}
			processed[row][column] = true;
			for (int j = 0; j < 4; j++) {
				int nextRow = row + MiscUtils.DX4[j];
				int nextColumn = column + MiscUtils.DY4[j];
				if (nextRow < 0 || nextRow >= rowCount || nextColumn < 0 || nextColumn >= columnCount || result[nextRow][nextColumn] != Integer.MAX_VALUE)
					continue;
				boolean found = false;
				for (int k = 0; k < times[nextRow][nextColumn].length; k++) {
					if (times[nextRow][nextColumn][k] > min) {
						if ((k & 1) == 0)
							result[nextRow][nextColumn] = min;
						else
							result[nextRow][nextColumn] = times[nextRow][nextColumn][k];
						found = true;
						break;
					}
				}
				if (!found)
					result[nextRow][nextColumn] = min;
			}
		}
		out.printLine(result[rowCount - 1][columnCount - 1]);
    }
}
