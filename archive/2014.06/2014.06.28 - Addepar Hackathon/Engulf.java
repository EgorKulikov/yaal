package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Engulf {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int[] colors = IOUtils.readIntArray(in, rowCount * columnCount);
		MiscUtils.decreaseByOne(colors);
		int[] qty = new int[count];
		for (int i : colors) {
			qty[i]++;
		}
		int bestColor = ArrayUtils.maxPosition(qty);
		int[] type = new int[columnCount];
		Arrays.fill(type, -1);
		type[0] = bestColor;
		int at = 1;
		int mx = count;
		if (count == 6) {
			mx = 2;
			if (mx > bestColor) {
				mx++;
			}
		}
		for (int i = 0; i < mx; i++) {
			if (i == bestColor) {
				continue;
			}
			for (int j = 0; j < qty[i] / (rowCount - 2); j++) {
				type[at++] = i;
			}
			type[at++] = bestColor;
		}
		int[] answer = new int[rowCount * columnCount];
		int[] height = new int[columnCount];
		int k = columnCount - 1;
		for (int i = rowCount * columnCount - 1; i >= 0 && k >= 0; i--) {
			if (colors[i] == bestColor) {
				while (k >= 0 && (type[k] == -1 || type[k] == bestColor)) {
					k--;
				}
				if (k < 0) {
					break;
				}
				answer[i] = k + 1;
				k--;
			}
		}
		for (int i = 0; i < rowCount * columnCount; i++) {
			if (answer[i] != 0) {
				continue;
			}
			if (colors[i] == bestColor) {
				for (int j = 0; j < columnCount; j++) {
					if (height[j] == 0 && (type[j] != -1 && type[j] != bestColor)) {
						answer[i] = j + 1;
						height[j] = 1;
						break;
					}
				}
				if (answer[i] == 0) {
					for (int j = 0; j < columnCount; j++) {
						if (type[j] == bestColor && height[j] < rowCount) {
							answer[i] = j + 1;
							height[j]++;
							break;
						}
					}
				}
				if (answer[i] == 0) {
					for (int j = 0; j < columnCount; j++) {
						if (type[j] != -1 && type[j] != bestColor && height[j] < rowCount - 1) {
							answer[i] = j + 1;
							height[j]++;
							break;
						}
					}
				}
				if (answer[i] == 0) {
					for (int j = 0; j < columnCount; j++) {
						if (type[j] == -1 && height[j] < rowCount) {
							answer[i] = j + 1;
							height[j]++;
							break;
						}
					}
				}
			} else {
				for (int j = 0; j < columnCount; j++) {
					if (height[j] > 0 && type[j] == colors[i] && height[j] < rowCount - 1) {
						answer[i] = j + 1;
						height[j]++;
						break;
					}
				}
				if (answer[i] == 0) {
					for (int j = 0; j < columnCount; j++) {
						if (type[j] == -1 && height[j] < rowCount) {
							answer[i] = j + 1;
							height[j]++;
							break;
						}
					}
				}
			}
		}
		Arrays.fill(height, 0);
		int[][] state = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount * columnCount; i++) {
			int column = answer[i] - 1;
			state[height[column]++][column] = colors[i] + 1;
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				System.err.print(state[i][j] + " ");
			}
			System.err.println();
		}
		out.printLine(answer);
	}
}
