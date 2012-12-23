package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, size);
		int[] from = new int[count];
		int[] to = new int[count];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to, permutation);
		double[][] less = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < i; j++)
				less[i][j] = 1;
		}
		for (int i = count - 1; i >= 0; i--) {
			if (from[i] > to[i]) {
				int temp = from[i];
				from[i] = to[i];
				to[i] = temp;
			}
			for (int j = 0; j < size; j++) {
				if (j != from[i] && j != to[i]) {
					less[from[i]][j] = less[to[i]][j] = (less[from[i]][j] + less[to[i]][j]) / 2;
					less[j][from[i]] = less[j][to[i]] = (less[j][from[i]] + less[j][to[i]]) / 2;
				}
			}
			less[from[i]][to[i]] = less[to[i]][from[i]] = (less[from[i]][to[i]] + less[to[i]][from[i]]) / 2;
		}
		double answer = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (permutation[i] < permutation[j])
					answer += less[i][j];
			}
		}
		out.printLine(answer);
	}
}
