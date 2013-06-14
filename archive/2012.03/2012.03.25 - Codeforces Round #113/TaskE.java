package net.egork;

import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Matrix matrix = new Matrix(2, 2);
		matrix.data[0][1] = 3;
		matrix.data[1][0] = 1;
		matrix.data[1][1] = 2;
		Matrix.mod = 1000000007;
		matrix = matrix.power(in.readInt());
		out.printLine(matrix.data[0][0]);
	}
}
