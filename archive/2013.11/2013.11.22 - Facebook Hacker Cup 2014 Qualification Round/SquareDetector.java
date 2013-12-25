package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SquareDetector {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] map = IOUtils.readTable(in, size, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == '#') {
					int end = size - 1;
					for (int k = j + 1; k < size; k++) {
						if (map[i][k] != '#') {
							end = k - 1;
							break;
						}
					}
					int side = end - j + 1;
					if (i + side > size) {
						out.printLine("Case #" + testNumber + ": NO");
						return;
					}
					for (int k = i; k < i + side; k++) {
						for (int l = j; l < j + side; l++) {
							if (map[k][l] != '#') {
								out.printLine("Case #" + testNumber + ": NO");
								return;
							}
							map[k][l] = '.';
						}
					}
					for (int k = 0; k < size; k++) {
						for (int l = 0; l < size; l++) {
							if (map[k][l] != '.') {
								out.printLine("Case #" + testNumber + ": NO");
								return;
							}
						}
					}
					out.printLine("Case #" + testNumber + ": YES");
					return;
				}
			}
		}
		throw new RuntimeException();
    }
}
