package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndTheGrid {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] map = IOUtils.readTable(in, size, size);
		int answer = 0;
		boolean[] ok = ArrayUtils.createArray(size, true);
		for (int i = size - 1; i >= 0; i--) {
			boolean rowOk = true;
			for (int j = size - 1; j >= 0; j--) {
				if (map[i][j] == '#') {
					rowOk = false;
					ok[j] = false;
				}
				if (rowOk && ok[j]) {
					answer++;
				}
			}
		}
		out.printLine(answer);
    }
}
