package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] table = IOUtils.readIntTable(in, size, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (table[i][j] == 0 || i < size - 1 && table[i][j] == table[i + 1][j] || j < size - 1 && table[i][j] == table[i][j + 1]) {
					out.printLine("YES");
					return;
				}
			}
		}
		out.printLine("NO");
	}
}
