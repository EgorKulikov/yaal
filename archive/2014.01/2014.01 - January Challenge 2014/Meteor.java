package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Meteor {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		boolean[][] bad = new boolean[rowCount][columnCount];
		for (int i = 0; i < count; i++) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			bad[row][column] = true;
		}
		int[][] width = new int[rowCount][rowCount];
		int[] last = new int[columnCount];
		int[] stack = new int[columnCount];
		int[] left = new int[columnCount];
		Arrays.fill(last, -1);
		for (int j = 0; j < rowCount; j++) {
			int size = -1;
			for (int k = 0; k < columnCount; k++) {
				if (bad[j][k])
					last[k] = j;
				while (size != -1 && last[stack[size]] <= last[k])
					size--;
				left[k] = size == -1 ? -1 : stack[size];
				stack[++size] = k;
			}
			size = -1;
			for (int k = columnCount - 1; k >= 0; k--) {
				while (size != -1 && last[stack[size]] <= last[k])
					size--;
				int right = size == -1 ? columnCount : stack[size];
				if (last[k] != j)
					width[last[k] + 1][j] = Math.max(width[last[k] + 1][j], (right - left[k] - 1));
				stack[++size] = k;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = rowCount - 1; j > i; j--) {
				width[i][j - 1] = Math.max(width[i][j - 1], width[i][j]);
				width[i + 1][j] = Math.max(width[i + 1][j], width[i][j]);
			}
		}
		int[][] answer = new int[rowCount][rowCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = i; j < rowCount; j++)
				answer[i][j] = width[i][j] * (j - i + 1);
		}
		for (int i = rowCount - 1; i >= 0; i--) {
			for (int j = i; j < rowCount; j++) {
				if (i != 0)
					answer[i - 1][j] = Math.max(answer[i - 1][j], answer[i][j]);
				if (j != rowCount - 1)
					answer[i][j + 1] = Math.max(answer[i][j + 1], answer[i][j]);
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			out.printLine(answer[from][to]);
		}
    }
}
