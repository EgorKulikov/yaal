package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class Weights {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		if (count % 3 == 1 || count <= 3) {
			out.println("Impossible");
			return;
		}
		int[][] weights = new int[3][];
		int[] indices = new int[3];
		int last;
		if (count % 6 == 0) {
			weights[0] = new int[count / 3];
			weights[1] = new int[count / 3];
			weights[2] = new int[count / 3];
			last = 0;
		} else if (count % 6 == 3) {
			weights[0] = new int[count / 3 - 1];
			weights[1] = new int[count / 3 - 1];
			weights[2] = new int[count / 3 + 2];
			weights[0][indices[0]++] = 6;
			weights[0][indices[0]++] = 9;
			weights[1][indices[1]++] = 7;
			weights[1][indices[1]++] = 8;
			weights[2][indices[2]++] = 1;
			weights[2][indices[2]++] = 2;
			weights[2][indices[2]++] = 3;
			weights[2][indices[2]++] = 4;
			weights[2][indices[2]++] = 5;
			last = 9;
		} else if (count % 6 == 2) {
			weights[0] = new int[count / 3];
			weights[1] = new int[count / 3];
			weights[2] = new int[count / 3 + 2];
			weights[0][indices[0]++] = 8;
			weights[0][indices[0]++] = 4;
			weights[1][indices[1]++] = 5;
			weights[1][indices[1]++] = 7;
			weights[2][indices[2]++] = 1;
			weights[2][indices[2]++] = 2;
			weights[2][indices[2]++] = 3;
			weights[2][indices[2]++] = 6;
			last = 8;
		} else {
			weights[0] = new int[count / 3];
			weights[1] = new int[count / 3 + 1];
			weights[2] = new int[count / 3 + 1];
			weights[0][indices[0]++] = 5;
			weights[1][indices[1]++] = 3;
			weights[1][indices[1]++] = 2;
			weights[2][indices[2]++] = 1;
			weights[2][indices[2]++] = 4;
			last = 5;
		}
		fillRemainder(weights, indices, last);
		for (int[] row : weights) {
			out.println(row.length);
			IOUtils.printArray(row, out);
		}
	}

	private void fillRemainder(int[][] weights, int[] indices, int last) {
		int current = last;
		for (int i = indices[0]; i < weights[0].length; i += 2) {
			weights[0][i] = current + 1;
			weights[0][i + 1] = current + 6;
			current += 6;
		}
		current = last;
		for (int i = indices[1]; i < weights[1].length; i += 2) {
			weights[1][i] = current + 2;
			weights[1][i + 1] = current + 5;
			current += 6;
		}
		current = last;
		for (int i = indices[2]; i < weights[2].length; i += 2) {
			weights[2][i] = current + 3;
			weights[2][i + 1] = current + 4;
			current += 6;
		}
	}
}
