package Timus.Part7;

import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1656 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int[] heights = in.readIntArray(size * size);
		int[][] result = new int[size][size];
		Arrays.sort(heights);
		int index = 0;
		for (int i = 0, l = size - 1; i <= l; i++, l--) {
			for (int j = 0, k = size - 1; j <= k; j++, k--) {
				result[i][j] = heights[index++];
				if (j != k)
					result[i][k] = heights[index++];
			}
			if (i != l) {
				for (int j = 0, k = size - 1; j <= k; j++, k--) {
					result[l][j] = heights[index++];
					if (j != k)
						result[l][k] = heights[index++];
				}
			}
		}
		for (int i = 0; i < size; i++)
			IOUtils.printArray(result[i], out);
	}
}

