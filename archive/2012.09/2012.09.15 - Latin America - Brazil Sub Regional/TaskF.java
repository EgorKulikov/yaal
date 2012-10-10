package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted()) {
			throw new UnknownError();
		}
		int count = in.readInt();
		int start = in.readInt() - 1;
		int target = in.readInt() - 1;
		int fail = in.readInt() - 1;
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		double[][] matrix = new double[count][count + 1];
		for (int i = 0; i < count; i++) {
			matrix[i][i] = 1;
			if (i == target)
				matrix[i][count] = 1;
			if (i == target || i == fail)
				continue;
			for (int j : graph[i])
				matrix[i][j] = -1d / graph[i].length;
		}
		int[] index = new int[count];
		for (int i = 0; i < count; i++)
			index[i] = i;
		for (int i = 0; i < count; i++) {
			int row = i;
			int column = i;
			for (int j = i; j < count; j++) {
				for (int k = i; k < count; k++) {
					if (Math.abs(matrix[j][k]) > Math.abs(matrix[row][column])) {
						row = j;
						column = k;
					}
				}
			}
			for (int j = 0; j < count; j++) {
				double temp = matrix[j][i];
				matrix[j][i] = matrix[j][column];
				matrix[j][column] = temp;
			}
			for (int j = i; j <= count; j++) {
				double temp = matrix[i][j];
				matrix[i][j] = matrix[row][j];
				matrix[row][j] = temp;
			}
			int temp = index[i];
			index[i] = index[column];
			index[column] = temp;
			for (int j = count; j >= i; j--)
				matrix[i][j] /= matrix[i][i];
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				for (int k = count; k >= i; k--)
					matrix[j][k] -= matrix[i][k] * matrix[j][i];
			}
		}
		for (int i = 0; i < count; i++) {
			if (index[i] == start) {
				out.printFormat("%.6f\n", matrix[i][count]);
				return;
			}
		}
	}
}
