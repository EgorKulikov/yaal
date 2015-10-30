package on2015_01.on2015_01_21_SnarkNews_Winter_Series_2015__Round_3.TaskF;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[] sumRow = IOUtils.readIntArray(in, rowCount);
		final int[] sumColumn = IOUtils.readIntArray(in, columnCount);
		ArrayUtils.sort(sumColumn);
		int[][] result = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			int same = columnCount - 1;
			while (same > 0 && sumColumn[same - 1] == sumColumn[same]) {
				same--;
			}
			int at = same;
			for (int j = 0; j < sumRow[i]; j++) {
				if (at == columnCount || result[i][at] == 9) {
					while (same > 0 && (sumColumn[same - 1] == sumColumn[same] || result[i][same] == 9)) {
						same--;
					}
					at = same;
				}
				if (result[i][at] == 9 || sumColumn[at] == 0) {
					out.printLine("None");
					return;
				}
				sumColumn[at]--;
				result[i][at]++;
				at++;
			}
		}
		if (ArrayUtils.count(sumColumn, 0) != columnCount) {
			out.printLine("None");
			return;
		}
		int goodRows = 0;
		for (int i = 0; i < rowCount; i++) {
			int goodCells = 0;
			for (int j : result[i]) {
				if (j != 0 && j != 9) {
					goodCells++;
				}
			}
			if (goodCells > 1) {
				goodRows++;
			}
		}
		if (goodRows > 1) {
			out.printLine("Multiple");
		} else {
			out.printLine("Unique");
		}
    }
}
