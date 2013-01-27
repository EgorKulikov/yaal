package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] table = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i + j < size - 1)
					table[i][j] = '2';
				else if (i + j > size - 1)
					table[i][j] = '1';
				else
					table[i][j] = '0';
			}
		}
		for (char[] row : table)
			out.printLine(row);
	}
}
