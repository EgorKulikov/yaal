import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Kitchen implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		if (testNumber != 1)
			out.println();
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		boolean swapped = rowCount < columnCount;
		if (swapped) {
			int temp = rowCount;
			rowCount = columnCount;
			columnCount = temp;
		}
		char[][] result = new char[rowCount][columnCount];
		ArrayUtils.fill(result, '.');
		for (int i = 0; i < columnCount; i += 3) {
			for (int j = 0; j < rowCount; j++)
				result[j][i] = '#';
			if (i + 1 < columnCount) {
				for (int j = 0; j < rowCount; j += 2)
					result[j][i + 1] = '#';
			}
			if (i + 3 < columnCount) {
				result[0][i + 2] = '#';
				for (int j = 3; j < rowCount; j += 2)
					result[j][i + 2] = '#';
			} else if (i + 2 < columnCount) {
				for (int j = 0; j < rowCount; j += 2)
					result[j][i + 2] = '#';
				result[rowCount - 1][columnCount - 1] = '#';
			}
		}
		if (swapped) {
			for (int i = 0; i < columnCount; i++) {
				for (int j = 0; j < rowCount; j++)
					out.print(result[j][i]);
				out.println();
			}
		} else {
			for (char[] row : result)
				out.println(row);
		}
	}
}

