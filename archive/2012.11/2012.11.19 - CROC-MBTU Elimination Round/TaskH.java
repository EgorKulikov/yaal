package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int total = 0;
		int[] odd = new int[s.length];
		int[] even = new int[s.length - 1];
		for (int i = 0; i < s.length; i++) {
			odd[i] = 1;
			for (int j = 1; j <= i && i + j < s.length; j++) {
				if (s[i - j] == s[i + j])
					odd[i]++;
				else
					break;
			}
			if (i != s.length - 1) {
				for (int j = 0; j <= i && i + j + 1 < s.length; j++) {
					if (s[i - j] == s[i + j + 1])
						even[i]++;
					else
						break;
				}
			}
		}
		int[][] answer = new int[s.length][s.length];
		int[] end = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			int count = 0;
			for (int j = i; j < s.length; j++) {
				if (2 * j - i < s.length && odd[j] > j - i)
					end[count++] = 2 * j - i;
				if (2 * j - i + 1 < s.length && even[j] > j - i)
					end[count++] = 2 * j - i + 1;
			}
			int current = 0;
			int k = 0;
			for (int j = i; j < s.length; j++) {
				if (k != count && end[k] == j) {
					k++;
					current++;
				}
				answer[i][j] = current;
			}
		}
		for (int i = s.length - 1; i > 0; i--) {
			for (int j = i; j < s.length; j++)
				answer[i - 1][j] += answer[i][j];
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			out.printLine(answer[from][to]);
		}
	}
}
