package net.egork;

import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Matrix.mod = 6;
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount == 0 && columnCount == 0) throw new UnknownError();
		Matrix direct = new Matrix(rowCount, columnCount);
		for (int i = 0; i < rowCount; i++) for (int j = 0; j < columnCount; j++) direct.data[i][j] = in.readInt();
		Matrix reverse = new Matrix(columnCount, rowCount);
		for (int i = 0; i < columnCount; i++) for (int j = 0; j < rowCount; j++) reverse.data[i][j] = in.readInt();
		Matrix middle = Matrix.multiply(reverse, direct);
		Matrix exponent = middle.power(rowCount * rowCount - 1);
		Matrix result = Matrix.multiply(Matrix.multiply(direct, exponent), reverse);
		int answer = 0;
		for (int i = 0; i < rowCount; i++) for (int j = 0; j < rowCount; j++) answer += result.data[i][j];
		out.printLine(answer);
    }
}
