package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BerryToughDistribution {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] candies = IOUtils.readIntArray(in, count);
		int[][] maxVariety = new int[count + 1][size];
		int[][] maxCandies = new int[count + 1][size];
		ArrayUtils.fill(maxVariety, Integer.MIN_VALUE);
		maxVariety[0][0] = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < size; j++) {
				if (maxVariety[i][j] == Integer.MIN_VALUE)
					continue;
				if (maxVariety[i][j] > maxVariety[i + 1][j] || maxVariety[i][j] == maxVariety[i + 1][j] && maxCandies[i][j] > maxCandies[i + 1][j]) {
					maxVariety[i + 1][j] = maxVariety[i][j];
					maxCandies[i + 1][j] = maxCandies[i][j];
				}
				int next = (j + candies[i]) % size;
				if (maxVariety[i + 1][next] < maxVariety[i][j] + 1 || maxVariety[i + 1][next] == maxVariety[i][j] + 1 && maxCandies[i + 1][next] < maxCandies[i][j] + candies[i]) {
					maxVariety[i + 1][next] = maxVariety[i][j] + 1;
					maxCandies[i + 1][next] = maxCandies[i][j] + candies[i];
				}
			}
		}
		out.printLine(maxVariety[count][0], maxCandies[count][0] / size);
	}
}
