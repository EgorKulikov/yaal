package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ThreeIsCrowd {
	static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		Matrix matrix = new Matrix(3, 3);
		matrix.data[0][0] = matrix.data[0][1] = matrix.data[0][2] = matrix.data[1][0] = matrix.data[2][1] = 1;
		Matrix.mod = MOD;
		matrix = matrix.power(count);
		long answer = ((IntegerUtils.power(2, count, MOD) - matrix.data[0][0] - matrix.data[1][0] - matrix.data[2][0]) % MOD + MOD) % MOD;
		out.printLine(answer);
	}
}
