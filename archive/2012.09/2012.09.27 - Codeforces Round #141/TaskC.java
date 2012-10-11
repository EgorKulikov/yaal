package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		boolean[][][] current = new boolean[16][rowCount][columnCount];
		boolean[][][] next = new boolean[16][rowCount][columnCount];
		for (int i = 0; i < rowCount - 1; i++) {
			for (int j = 0; j < columnCount - 1; j++) {
				int mask = 0;
				if (map[i][j] == '.')
					mask++;
				if (map[i + 1][j] == '.')
					mask += 2;
				if (map[i][j + 1] == '.')
					mask += 4;
				if (map[i + 1][j + 1] == '.')
					mask += 8;
				current[mask][i][j] = true;
			}
		}
		int answer = 0;
		for (int side = 2; 2 * side <= Math.min(rowCount, columnCount); side *= 2) {
			for (int i = 0; i <= rowCount - 2 * side; i++) {
				for (int j = 0; j <= columnCount - 2 * side; j++) {
					for (int k = 0; k < 16; k++) {
						boolean good = true;
						if ((k & 1) != 0)
							good &= current[k][i][j];
						else
							good &= current[0][i][j];
						if ((k & 2) != 0)
							good &= current[k][i + side][j];
						else
							good &= current[0][i + side][j];
						if ((k & 4) != 0)
							good &= current[k][i][j + side];
						else
							good &= current[0][i][j + side];
						if ((k & 8) != 0)
							good &= current[k][i + side][j + side];
						else
							good &= current[0][i + side][j + side];
						next[k][i][j] = good;
						if (good)
							answer++;
					}
				}
			}
			boolean[][][] temp = current;
			current = next;
			next = temp;
		}
		out.printLine(answer);
	}
}
