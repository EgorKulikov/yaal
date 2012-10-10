package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] matrix = IOUtils.readIntTable(in, size, size);
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += matrix[i][i];
			sum += matrix[i][size - i - 1];
			sum += matrix[size / 2][i];
			sum += matrix[i][size / 2];
		}
		sum -= 3 * matrix[size / 2][size / 2];
		out.printLine(sum);
	}
}
