package on2012_08.on2012_7_12.maximumjewels;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MaximumJewels {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.setFilter(new InputReader.SpaceCharFilter() {
			public boolean isSpaceChar(int c) {
				return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1 || c == ',';
			}
		});
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long[][] initialMap = IOUtils.readLongTable(in, rowCount, columnCount);
		long[][] map = new long[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				map[i][j] = initialMap[i][j];
		}
		boolean[][] forbidden = new boolean[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (initialMap[i][j] < 0) {
					forbidden[i][j] = true;
					for (int k = 0; k < 8; k++) {
						int row = i + MiscUtils.DX8[k];
						int column = j + MiscUtils.DY8[k];
						if (row >= 0 && row < rowCount && column >= 0 && column < columnCount)
							map[row][column] += initialMap[i][j];
					}
				}
			}
		}
		long[][] answer = new long[rowCount][columnCount];
		ArrayUtils.fill(answer, Long.MIN_VALUE / 2);
		answer[0][0] = 0;
		map[0][0] = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (forbidden[i][j])
					continue;
				if (i != 0)
					answer[i][j] = Math.max(answer[i][j], answer[i - 1][j]);
				if (j != 0)
					answer[i][j] = Math.max(answer[i][j], answer[i][j - 1]);
				answer[i][j] += map[i][j];
			}
		}
		out.printLine(answer[rowCount - 1][columnCount - 1]);
	}
}
