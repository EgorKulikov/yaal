package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LongestWeirdSubsequence {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[][] result = new int[26][26];
		int[][] nextResult = new int[26][26];
		for (char value : s) {
			for (int j = 0; j < 26; j++)
				System.arraycopy(result[j], 0, nextResult[j], 0, 26);
			for (int j = 0; j < 26; j++) {
				for (int k = value - 'a'; k >= 0; k--)
					nextResult[value - 'a'][j] = Math.max(nextResult[value - 'a'][j], result[k][j] + 1);
				for (int k = value - 'a'; k < 26; k++)
					nextResult[j][value - 'a'] = Math.max(nextResult[j][value - 'a'], result[j][k] + 1);
			}
			for (int j = 0; j < 26; j++)
				System.arraycopy(nextResult[j], 0, result[j], 0, 26);
		}
		int answer = 0;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++)
				answer = Math.max(answer, result[i][j]);
		}
		out.printLine(answer);
	}
}
