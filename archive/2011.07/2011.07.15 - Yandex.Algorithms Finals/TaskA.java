import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int[][] index = new int[rowCount][columnCount];
		boolean[][] graph = new boolean[15][15];
		int color = 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] != '.' && index[i][j] == 0) {
					index[i][j] = color;
					index[i + 1][j] = color;
					index[i][j + 1] = color;
					index[i + 1][j + 1] = color++;
				}
			}
		}
		for (int i = 0; i < 28; i++) {
			char symbol;
			if (i < 26)
				symbol = (char) ('a' + i);
			else
				symbol = (char) ('A' + i - 26);
			outer:
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (table[j][k] == symbol) {
						int otherRow = j;
						int otherColumn = k;
						if (j + 1 < rowCount && table[j + 1][k] == symbol)
							otherRow = j + 1;
						else
							otherColumn = k + 1;
						graph[index[j][k]][index[otherRow][otherColumn]] = graph[index[otherRow][otherColumn]][index[j][k]] = true;
						break outer;
					}
				}
			}
		}
		int nonDoubleCount = 0;
		for (int i = 1; i <= 14; i++) {
			if (!graph[i][i])
				nonDoubleCount++;
		}
		int[] doubleIndices = new int[14 - nonDoubleCount];
		int[] nonDoubleIndices = new int[nonDoubleCount];
		int doubleIndex = 0;
		int nonDoubleIndex = 0;
		for (int i = 1; i <= 14; i++) {
			if (graph[i][i])
				doubleIndices[doubleIndex++] = i;
			else
				nonDoubleIndices[nonDoubleIndex++] = i;
		}
		long answer = 0;
		WritableSequence<Integer> permutation = Array.wrap(nonDoubleIndices);
		do {
			boolean good = true;
			for (int i = 7; i < nonDoubleIndex - 1; i++) {
				if (nonDoubleIndices[i] > nonDoubleIndices[i + 1])
					good = false;
			}
			for (int i = 0; i < 7 && good; i++) {
				int firstI = i >= doubleIndex ? nonDoubleIndices[7 + i - doubleIndex] : doubleIndices[i];
				int secondI = nonDoubleIndices[i];
				if (i >= doubleIndex && firstI > secondI) {
					good = false;
					break;
				}
				for (int j = 0; j < 7 && good; j++) {
					int firstJ = j >= doubleIndex ? nonDoubleIndices[7 + j - doubleIndex] : doubleIndices[j];
					int secondJ = nonDoubleIndices[j];
					if (!graph[firstI][firstJ] && !graph[firstI][secondJ] && !graph[secondI][firstJ] && !graph[secondI][secondJ])
						good = false;
				}
			}
			if (good) {
				if (answer == 0) {
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < columnCount; j++) {
							if (table[i][j] == '.')
								continue;
							for (int k = 0; k < doubleIndex; k++) {
								if (index[i][j] == doubleIndices[k]) {
									table[i][j] = (char) ('0' + k);
									break;
								}
							}
							for (int k = 0; k < nonDoubleIndex; k++) {
								if (index[i][j] == nonDoubleIndices[k]) {
									if (k < 7)
										table[i][j] = (char) ('0' + k);
									else
										table[i][j] = (char) ('0' + doubleIndex + k - 7);
									break;
								}
							}
						}
					}
				}
				answer++;
			}
		} while (SequenceUtils.nextPermutation(permutation));
		answer *= IntegerUtils.factorial(7);
		out.println(answer);
		for (char[] row : table)
			out.println(row);
	}
}

