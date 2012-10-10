import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class MaximalScorePath implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] weight = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, weight);
		long[] key = new long[edgeCount];
		for (int i = 0; i < edgeCount; i++)
			key[i] = -(long)weight[i] * edgeCount - i;
		Arrays.sort(key);
		int[][] result = new int[vertexCount][vertexCount];
		boolean[][] connected = new boolean[vertexCount][vertexCount];
		for (int i = 0; i < vertexCount; i++)
			connected[i][i] = true;
		int[] first = new int[vertexCount];
		int[] second = new int[vertexCount];
		for (long x : key) {
			int i = (int) (Math.abs(x) % edgeCount);
			if (!connected[from[i]][to[i]]) {
				int firstSize = 0;
				int secondSize = 0;
				for (int j = 0; j < vertexCount; j++) {
					if (connected[from[i]][j])
						first[firstSize++] = j;
					else if (connected[to[i]][j])
						second[secondSize++] = j;
				}
				for (int j = 0; j < firstSize; j++) {
					for (int k = 0; k < secondSize; k++) {
						connected[first[j]][second[k]] = true;
						result[first[j]][second[k]] = weight[i];
					}
				}
				for (int k = 0; k < secondSize; k++) {
					for (int j = 0; j < firstSize; j++) {
						connected[second[k]][first[j]] = true;
						result[second[k]][first[j]] = weight[i];
					}
				}
			}
		}
		for (int[] row : result)
			IOUtils.printArray(row, out);
	}
}

