package on2013_11.on2013_11_22_20_20_Hack_November.Oil_Well;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class OilWell {
	int[][][][] result;
	int[][] map;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		map = IOUtils.readIntTable(in, rowCount, columnCount);
		result = new int[rowCount][columnCount][rowCount][columnCount];
		ArrayUtils.fill(result, -1);
		out.printLine(go(0, 0, rowCount - 1, columnCount - 1));
    }

	private int go(int fRow, int fColumn, int tRow, int tColumn) {
		if (result[fRow][fColumn][tRow][tColumn] != -1)
			return result[fRow][fColumn][tRow][tColumn];
		if (fRow == tRow && fColumn == tColumn)
			return result[fRow][fColumn][tRow][tColumn] = 0;
		result[fRow][fColumn][tRow][tColumn] = Integer.MAX_VALUE;
		if (fRow != tRow) {
			{
				int candidate = go(fRow + 1, fColumn, tRow, tColumn);
				for (int i = fColumn; i <= tColumn; i++) {
					if (map[fRow][i] == 1)
						candidate += Math.max(tRow - fRow, Math.max(tColumn - i, i - fColumn));
				}
				result[fRow][fColumn][tRow][tColumn] = Math.min(result[fRow][fColumn][tRow][tColumn], candidate);
			}
			{
				int candidate = go(fRow, fColumn, tRow - 1, tColumn);
				for (int i = fColumn; i <= tColumn; i++) {
					if (map[tRow][i] == 1)
						candidate += Math.max(tRow - fRow, Math.max(tColumn - i, i - fColumn));
				}
				result[fRow][fColumn][tRow][tColumn] = Math.min(result[fRow][fColumn][tRow][tColumn], candidate);
			}
		}
		if (fColumn != tColumn) {
			{
				int candidate = go(fRow, fColumn + 1, tRow, tColumn);
				for (int i = fRow; i <= tRow; i++) {
					if (map[i][fColumn] == 1)
						candidate += Math.max(tColumn - fColumn, Math.max(tRow - i, i - fRow));
				}
				result[fRow][fColumn][tRow][tColumn] = Math.min(result[fRow][fColumn][tRow][tColumn], candidate);
			}
			{
				int candidate = go(fRow, fColumn, tRow, tColumn - 1);
				for (int i = fRow; i <= tRow; i++) {
					if (map[i][tColumn] == 1)
						candidate += Math.max(tColumn - fColumn, Math.max(tRow - i, i - fRow));
				}
				result[fRow][fColumn][tRow][tColumn] = Math.min(result[fRow][fColumn][tRow][tColumn], candidate);
			}
		}
		return result[fRow][fColumn][tRow][tColumn];
	}
}
