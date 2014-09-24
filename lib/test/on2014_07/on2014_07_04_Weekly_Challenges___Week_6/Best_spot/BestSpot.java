package on2014_07.on2014_07_04_Weekly_Challenges___Week_6.Best_spot;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BestSpot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		int width = in.readInt();
		int[][] grid = IOUtils.readIntTable(in, height, width);
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] sample = IOUtils.readIntTable(in, rowCount, columnCount);
		long[] first = new long[height * width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				first[i * width + j] = grid[i][j];
				grid[i][j] *= grid[i][j];
			}
		}
		long[] second = new long[rowCount * width];
		long sampleSquared = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				second[second.length - 1 - i * width - j] = sample[i][j];
				sampleSquared += sample[i][j] * sample[i][j];
			}
		}
		long[] result = FastFourierTransform.multiply(first, second);
		long[][] gridSquared = ArrayUtils.partialSums(grid);
		long best = Long.MAX_VALUE;
		int row = -1;
		int column = -1;
		for (int i = 0; i <= height - rowCount; i++) {
			for (int j = 0; j <= width - columnCount; j++) {
				long candidate = gridSquared[i][j] + gridSquared[i + rowCount][j + columnCount] -
					gridSquared[i][j + columnCount] - gridSquared[i + rowCount][j] + sampleSquared -
					2 * result[i * width + j + second.length - 1];
				if (candidate < best) {
					best = candidate;
					row = i + 1;
					column = j + 1;
				}
			}
		}
		out.printLine(best);
		out.printLine(row, column);
	}
}
