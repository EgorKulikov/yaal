package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int total = 0;
		boolean swap = false;
		if (rowCount > columnCount) {
			swap = true;
			int temp = rowCount;
			rowCount = columnCount;
			columnCount = temp;
		}
		char[][] answer = new char[rowCount][columnCount];
		int remaining = rowCount * columnCount;
		for (int i = 1; 2 * i < rowCount; i++) {
			for (int j = 0; j < i; j++)
				answer[j][i - 1] = (i % 2 == 0) ? 'A' : 'C';
			for (int j = i; j < rowCount; j++)
				answer[j][i - 1] = (i % 2 == 0) ? 'B' : 'D';
			total += 2;
			remaining -= rowCount;
		}
		int curColumn = (rowCount - 1) / 2;
		int curRow = 0;
		int direction = 1;
		if (rowCount % 2 == 0) {
			for (int j = 0; j < rowCount / 2; j++)
				answer[j][curColumn] = 'H';
			total++;
			remaining -= rowCount / 2;
			curRow = rowCount / 2;
		}
		int length = rowCount;
		char letter = 'E';
		while (remaining >= length) {
			total++;
			remaining -= length;
			for (int i = 0; i < length; i++) {
				if (curRow < 0) {
					curColumn++;
					curRow = 0;
					direction = 1;
				} else if (curRow >= rowCount) {
					curColumn++;
					curRow = rowCount - 1;
					direction = -1;
				}
				answer[curRow][curColumn] = letter;
				curRow += direction;
			}
			letter++;
			if (letter == 'H')
				letter = 'E';
			length++;
		}
		letter--;
		if (letter == 'D')
			letter = 'G';
		while (true) {
			if (curRow < 0) {
				curColumn++;
				curRow = 0;
				direction = 1;
			} else if (curRow >= rowCount) {
				curColumn++;
				curRow = rowCount - 1;
				direction = -1;
			}
			if (curColumn >= columnCount)
				break;
			answer[curRow][curColumn] = letter;
			curRow += direction;
		}
		if (swap) {
			char[][] real = new char[columnCount][rowCount];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++)
					real[j][i] = answer[i][j];
			}
			answer = real;
			rowCount = columnCount;
		}
		out.printLine(total);
		for (int i = 0; i < rowCount; i++)
			out.printLine(answer[i]);
    }
}
