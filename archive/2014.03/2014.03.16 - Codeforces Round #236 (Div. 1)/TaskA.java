package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int additional = in.readInt();
		boolean[][] matrix = new boolean[count][count];
		for (int i = 1; i < count; i++)
			matrix[i - 1][i] = true;
		matrix[0][count - 1] = true;
		for (int i = 2; i < count; i++)
			matrix[i - 2][i] = true;
		matrix[0][count - 2] = true;
		matrix[1][count - 1] = true;
		int total = count + additional;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (!matrix[i][j] && additional > 0) {
					additional--;
					matrix[i][j] = true;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (matrix[i][j])
					out.printLine(i + 1, j + 1);
			}
		}
    }
}
