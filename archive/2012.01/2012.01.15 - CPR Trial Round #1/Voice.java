package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Voice {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] words = in.readLine().split(" ");
		String[] recognized = in.readLine().split(" ");
		int[][] cost = new int[recognized.length][words.length];
		for (int i = 0; i < recognized.length; i++) {
			for (int j = 0; j < words.length; j++)
				cost[i][j] = getCost(recognized[i], words[j]);
		}
		int[][] answer = new int[words.length + 1][1 << recognized.length];
		ArrayUtils.fill(answer, Integer.MAX_VALUE);
		answer[0][0] = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < (1 << recognized.length); j++) {
				answer[i + 1][j] = Math.min(answer[i + 1][j], answer[i][j]);
				if (answer[i][j] != Integer.MAX_VALUE) {
					for (int k = 0; k < recognized.length; k++) {
						if ((j >> k & 1) == 0)
							answer[i + 1][j + (1 << k)] = Math.min(answer[i + 1][j + (1 << k)], answer[i][j] + cost[k][i]);
					}
				}
			}
		}
		out.printLine(answer[words.length][(1 << recognized.length) - 1]);
	}

	private int getCost(String first, String second) {
		int[][] result = new int[first.length() + 1][second.length() + 1];
		ArrayUtils.fill(result, Integer.MAX_VALUE);
		result[0][0] = 0;
		for (int i = 0; i <= first.length(); i++) {
			for (int j = 0; j <= second.length(); j++) {
				if (i != 0)
					result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
				if (j != 0)
					result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
				if (i != 0 && j != 0) {
					if (first.charAt(i - 1) == second.charAt(j - 1))
						result[i][j] = Math.min(result[i][j], result[i - 1][j - 1]);
					else
						result[i][j] = Math.min(result[i][j], result[i - 1][j - 1] + 1);
				}
			}
		}
		return result[first.length()][second.length()];
	}
}
