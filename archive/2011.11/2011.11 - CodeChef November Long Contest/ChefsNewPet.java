package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefsNewPet {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long length = in.readLong();
		int[] jumps = IOUtils.readIntArray(in, in.readInt());
		Matrix.mod = 1000000007;
		Matrix matrix = new Matrix(15, 15);
		for (int i = 1; i < 15; i++)
			matrix.data[i][i - 1] = 1;
		for (int jump : jumps)
			matrix.data[0][jump - 1] = 1;
		matrix = matrix.power(length);
		out.printLine(matrix.data[0][0]);
	}
}
