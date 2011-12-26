package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskM {
	private int[][] answer = new int[50000][10];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int count = go(n, m, 0, 0);
		for (int i = 0; i < count; i++) {
			out.print(answer[i][0]);
			for (int j = 1; answer[i][j] != -1; j++)
				out.print("*" + answer[i][j]);
			out.printLine();
		}
	}

	private int go(int n, int m, int index, int inRow) {
		if (n == 1) {
			answer[index][inRow] = -1;
			return index + 1;
		}
		for (int i = m; i <= n; i++) {
			if (n % i == 0) {
				int oldIndex = index;
				index = go(n / i, i, index, inRow + 1);
				for (int j = oldIndex; j < index; j++)
					answer[j][inRow] = i;
			}
		}
		return index;
	}
}
