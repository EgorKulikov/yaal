package on2014_09.on2014_09_21_September_Cook_Off_2014__ACM_ICPC_Warm_up.World_War_2;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WorldWar2 {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rows = in.readInt();
		int columns = in.readInt();
		long[][] matrix = new long[columns][columns];
		for (int i = 0; i < columns; i++) {
			for (int j = i - 1; j <= i + 1; j += 2) {
				if (j < 0 || j >= columns) {
					continue;
				}
				for (int k = j - 1; k <= j + 1; k++) {
					if (k < 0 || k >= columns) {
						continue;
					}
					matrix[i][k]++;
				}
			}
		}
		long[][] result = Matrix.convert(Matrix.power(Matrix.convert(matrix), (rows - 1) / 2, MOD, columns), columns);
		long answer = 0;
		if (rows % 2 == 1) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < columns; j++) {
					answer += result[i][j];
				}
			}
		} else {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < columns; j++) {
					for (int k = j - 1; k <= j + 1; k += 2) {
						if (k >= 0 && k < columns) {
							answer += result[i][j];
						}
					}
				}
			}
		}
		out.printLine(answer % MOD);
    }
}
