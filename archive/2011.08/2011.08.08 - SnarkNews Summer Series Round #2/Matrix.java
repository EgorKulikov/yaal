import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Matrix implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int queryCount = in.readInt();
		int[] startRow = new int[queryCount];
		int[] startColumn = new int[queryCount];
		int[] rowLength = new int[queryCount];
		int[] columnLength = new int[queryCount];
		int[][] matrix = IOUtils.readIntTable(in, rowCount, columnCount);
		IOUtils.readIntArrays(in, startRow, startColumn, rowLength, columnLength);
		int[][][] gcd = new int[rowCount][columnCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int current = 0;
				for (int k = j; k < columnCount; k++)
					gcd[i][j][k] = current = (int) IntegerUtils.gcd(current, matrix[i][k]);
			}
		}
		for (int i = 0; i < queryCount; i++) {
			long answer = 0;
			for (int j = startRow[i]; j < startRow[i] + rowLength[i]; j++)
				answer = IntegerUtils.gcd(answer, gcd[j][startColumn[i]][startColumn[i] + columnLength[i] - 1]);
			out.println(answer);
		}
	}
}

