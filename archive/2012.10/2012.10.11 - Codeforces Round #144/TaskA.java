package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[][] graph = new boolean[100][100];
		int full = 0;
		for (int i = 3; i <= 100; i++) {
			if (i * (i - 1) * (i - 2) / 6 <= count)
				full = i;
			else
				break;
		}
		for (int i = 0; i < full; i++) {
			for (int j = 0; j < full; j++) {
				if (i != j)
					graph[i][j] = true;
			}
		}
		count -= full * (full - 1) * (full - 2) / 6;
		int all = 100;
		if (count == 0)
			all = full;
		else {
			for (int j = full; j < 100; j++) {
				int current = 0;
				for (int k = full; k >= 0; k--) {
					if (count >= k * (k - 1) / 2) {
						current = k;
						break;
					}
				}
				count -= current * (current - 1) / 2;
				for (int k = 0; k < current; k++)
					graph[j][k] = graph[k][j] = true;
				if (count == 0) {
					all = j + 1;
					break;
				}
			}
		}
		if (count != 0)
			throw new RuntimeException();
		out.printLine(all);
		for (int i = 0; i < all; i++) {
			for (int j = 0; j < all; j++) {
				if (graph[i][j])
					out.print(1);
				else
					out.print(0);
			}
			out.printLine();
		}
	}
}
