package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CubeCakes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int similarity = in.readInt();
		char[][][] firstCube = new char[size][size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					firstCube[i][j][k] = in.readCharacter();
				}
			}
		}
		char[][][] secondCube = new char[size][size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					secondCube[i][j][k] = in.readCharacter();
				}
			}
		}
		int[][][] qty = new int[size + 1][size + 1][size + 1];
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				for (int k = 1; k <= size; k++) {
					qty[i][j][k] = qty[i][j][k - 1] + qty[i][j - 1][k] + qty[i - 1][j][k] -
						qty[i - 1][j - 1][k] - qty[i - 1][j][k - 1] - qty[i][j - 1][k - 1] +
						qty[i - 1][j - 1][k - 1] + (firstCube[i - 1][j - 1][k - 1] == secondCube[i - 1][j - 1][k - 1] ? 1 : 0);
				}
			}
		}
		int[] answer = new int[size + 1];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					int upTo = Math.min(size - i, Math.min(size - j, size - k));
					for (int l = 1; l <= upTo; l++) {
						int current = qty[i + l][j + l][k + l] - qty[i][j + l][k + l] - qty[i + l][j][k + l] - qty[i + l][j + l][k] +
							qty[i][j][k + l] + qty[i][j + l][k] + qty[i + l][j][k] - qty[i][j][k];
						if (current * 100 >= similarity * l * l * l)
							answer[l]++;
					}
				}
			}
		}
		for (int i = size; i > 0; i--) {
			if (answer[i] > 0) {
				out.printLine(i, answer[i]);
				return;
			}
		}
		out.printLine(-1);
    }
}
