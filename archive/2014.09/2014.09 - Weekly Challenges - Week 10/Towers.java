package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Towers {

	public static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long height = in.readLong();
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		long[][] matrix = new long[15][15];
		for (int i = 1; i < 15; i++) {
			matrix[i][i - 1] = 1;
		}
		for (int i : heights) {
			matrix[0][i - 1] = 1;
		}
		out.printLine(Matrix.power(Matrix.convert(matrix), height, MOD, 15)[0] * 2 % MOD);
    }
}
