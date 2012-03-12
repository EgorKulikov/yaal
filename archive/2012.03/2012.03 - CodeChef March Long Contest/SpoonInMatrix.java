package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SpoonInMatrix {
	static final char[] target = "spoon".toCharArray();

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] matrix = IOUtils.readTable(in, rowCount, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				matrix[i][j] = Character.toLowerCase(matrix[i][j]);
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount - 4; j++) {
				boolean good = true;
				for (int k = 0; k < 5; k++) {
					if (matrix[i][j + k] != target[k]) {
						good = false;
						break;
					}
				}
				if (good) {
					out.printLine("There is a spoon!");
					return;
				}
			}
		}
		for (int i = 0; i < rowCount - 4; i++) {
			for (int j = 0; j < columnCount; j++) {
				boolean good = true;
				for (int k = 0; k < 5; k++) {
					if (matrix[i + k][j] != target[k]) {
						good = false;
						break;
					}
				}
				if (good) {
					out.printLine("There is a spoon!");
					return;
				}
			}
		}
		out.printLine("There is indeed no spoon!");
	}
}
