package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		int width = in.readInt();
		int colorCount = in.readInt();
		boolean[][] inRows = new boolean[height][width - 1];
		boolean[][] inColumns = new boolean[width][height - 1];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width - 1; j++)
				inRows[i][j] = in.readCharacter() == 'E';
			if (i != height - 1) {
				for (int j = 0; j < width; j++)
					inColumns[j][i] = in.readCharacter() == 'E';
			}
		}
		if (colorCount == 1) {
			int total = width * (height - 1) + height * (width - 1);
			total -= total / 4;
			for (boolean[] row : inRows) {
				for (boolean b : row) {
					if (b)
						total--;
				}
			}
			for (boolean[] row : inColumns) {
				for (boolean b : row) {
					if (b)
						total--;
				}
			}
			if (total > 0)
				out.printLine("NO");
			else {
				out.printLine("YES");
				int[] row = new int[width];
				Arrays.fill(row, 1);
				for (int i = 0; i < height; i++)
					out.printLine(row);
			}
			return;
		}
		out.printLine("YES");
		boolean shouldRotate = false;
		if (height > width) {
			int temp = height;
			height = width;
			width = temp;
			shouldRotate = true;
			boolean[][] temp2 = inRows;
			inRows = inColumns;
			inColumns = temp2;
		}
		int[][] answer = new int[height][width];
		answer[0][0] = 1;
		for (int i = 1; i < width; i++) {
			if (inRows[0][i - 1])
				answer[0][i] = answer[0][i - 1];
			else
				answer[0][i] = 3 - answer[0][i - 1];
		}
		for (int i = 1; i < height; i++) {
			int current = 0;
			answer[i][0] = 1;
			if ((answer[i][0] == answer[i - 1][0]) != inColumns[0][i - 1])
				current++;
			for (int j = 1; j < width; j++) {
				if (inRows[i][j - 1])
					answer[i][j] = answer[i][j - 1];
				else
					answer[i][j] = 3 - answer[i][j - 1];
				if ((answer[i][j] == answer[i - 1][j]) != inColumns[j][i - 1])
					current++;
			}
			if (current > width - current) {
				for (int j = 0; j < width; j++)
					answer[i][j] = 3 - answer[i][j];
			}
		}
		if (shouldRotate) {
			int[][] temp = new int[width][height];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++)
					temp[j][i] = answer[i][j];
			}
			answer = temp;
		}
		for (int[] row : answer)
			out.printLine(row);
    }
}
