package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int good = in.readInt();
		int size = in.readInt();
		int[][] table = IOUtils.readIntTable(in, size, size);
		boolean[] isGood = new boolean[size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (table[i][j] == good)
					isGood[j] = true;
			}
		}
		for (boolean value : isGood) {
			if (value)
				out.printLine("YES");
			else
				out.printLine("NO");
		}
	}
}
