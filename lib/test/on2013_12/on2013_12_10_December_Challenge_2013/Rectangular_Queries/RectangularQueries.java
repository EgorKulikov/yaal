package on2013_12.on2013_12_10_December_Challenge_2013.Rectangular_Queries;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RectangularQueries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] table = IOUtils.readIntTable(in, size, size);
		MiscUtils.decreaseByOne(table);
		int[][][] answer = new int[10][size + 1][size + 1];
		for (int i = 0; i < 10; i++) {
			for (int j = 1; j <= size; j++) {
				for (int k = 1; k <= size; k++) {
					answer[i][j][k] = answer[i][j - 1][k] + answer[i][j][k - 1] - answer[i][j - 1][k - 1] +
						(table[j - 1][k - 1] == i ? 1 : 0);
				}
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int x0 = in.readInt() - 1;
			int y0 = in.readInt() - 1;
			int x1 = in.readInt();
			int y1 = in.readInt();
			int result = 0;
			for (int j = 0; j < 10; j++) {
				if (answer[j][x1][y1] + answer[j][x0][y0] - answer[j][x1][y0] - answer[j][x0][y1] > 0)
					result++;
			}
			out.printLine(result);
		}
    }
}
