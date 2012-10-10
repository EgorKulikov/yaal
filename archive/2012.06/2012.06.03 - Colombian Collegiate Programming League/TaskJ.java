package net.egork;

import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	Matrix matrix = new Matrix(3, 3);

	{
		matrix.data[0][1] = matrix.data[1][2] = matrix.data[2][0] = matrix.data[2][1] = matrix.data[2][2] = 1;
		Matrix.mod = (long) (1e9 + 9);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long index = in.readLong() - 1;
		if (index == -1)
			throw new UnknownError();
		Matrix result = matrix.power(index);
		long answer = result.data[0][1] + 2 * result.data[0][2];
		answer %= Matrix.mod;
		out.printLine(answer);
	}
}
