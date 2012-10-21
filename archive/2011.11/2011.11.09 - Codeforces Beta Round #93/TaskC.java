package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] data = IOUtils.readTable(in, size, size);
		boolean[] upRows = new boolean[size];
		boolean[] upColumns = new boolean[size];
		boolean[] downRows = new boolean[size];
		boolean[] downColumns = new boolean[size];
		int answer = 0;
		for (int i = size - 1; i > 0; i--) {
			for (int j = 0; i + j < size; j++) {
				int row = j;
				int column = i + j;
				if ((upRows[row] ^ upColumns[column]) != (data[row][column] == '1')) {
					answer++;
					upRows[row] = !upRows[row];
					upColumns[column] = !upColumns[column];
				}
				row = i + j;
				column = j;
				if ((downRows[row] ^ downColumns[column]) != (data[row][column] == '1')) {
					answer++;
					downRows[row] = !downRows[row];
					downColumns[column] = !downColumns[column];
				}
			}
		}
		for (int i = 0; i < size; i++) {
			if ((upRows[i] ^ upColumns[i] ^ downRows[i] ^ downColumns[i]) != (data[i][i] == '1'))
				answer++;
		}
		out.printLine(answer);
	}
}
