package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ExpectedTimeToLove {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double[][] matrix = new double[count][count + 1];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				matrix[i][j] = -in.readDouble() / 100;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				matrix[i][count] -= matrix[i][j] * in.readDouble();
		}
		for (int i = 0; i < count; i++)
			matrix[i][i]++;
		matrix[count - 1][count] = 0;
		matrix[count - 1][count - 1] = 1;
		for (int i = 0; i < count; i++) {
			int index = i;
			double max = Math.abs(matrix[i][i]);
			for (int j = i + 1; j < count; j++) {
				if (Math.abs(matrix[j][i]) > max) {
					max = Math.abs(matrix[j][i]);
					index = j;
				}
			}
			if (i != index) {
				for (int j = i; j <= count; j++) {
					double temp = matrix[i][j];
					matrix[i][j] = matrix[index][j];
					matrix[index][j] = temp;
				}
			}
			for (int j = count; j >= i; j--)
				matrix[i][j] /= matrix[i][i];
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				for (int k = count; k >= i; k--)
					matrix[j][k] -= matrix[i][k] * matrix[j][i];
			}
		}
		out.printFormat("%.6f\n", matrix[0][count]);
    }
}
