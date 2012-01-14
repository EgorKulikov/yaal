package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (columnCount == 1) {
			out.printLine("Second");
			return;
		}
		int maxPerMove = in.readInt();
		char[][] field = IOUtils.readTable(in, rowCount, columnCount);
		if (columnCount == 2) {
			boolean firstCanMove = false;
			boolean secondCanMove = false;
			for (int i = 0; i < rowCount; i++) {
				if (field[i][0] == 'G' && field[i][1] == '-' || field[i][1] == 'G' && field[i][0] == '-')
					firstCanMove = true;
				if (field[i][0] == 'R' && field[i][1] == '-' || field[i][1] == 'R' && field[i][0] == '-')
					secondCanMove = true;
			}
			if (!firstCanMove)
				out.printLine("Second");
			else if (!secondCanMove)
				out.printLine("First");
			else
				out.printLine("Draw");
			return;
		}
		int[] distance = new int[rowCount];
		boolean firstCanSkip = false;
		boolean secondCanSkip = false;
		for (int i = 0; i < rowCount; i++) {
			int rPosition = -1;
			int gPosition = -1;
			for (int j = 0; j < columnCount; j++) {
				if (field[i][j] == 'G')
					gPosition = j;
				else if (field[i][j] == 'R')
					rPosition = j;
			}
			if (rPosition == -1) {
				if (gPosition != -1)
					firstCanSkip = true;
			} else if (gPosition == -1)
				secondCanSkip = true;
			else
				distance[i] = Math.abs(rPosition - gPosition) - 1;
		}
		if (firstCanSkip) {
			if (secondCanSkip) {
				out.printLine("Draw");
				return;
			}
			out.printLine("First");
			return;
		} else if (secondCanSkip) {
			out.printLine("Second");
			return;
		}
		for (int i = 0; i < 7; i++) {
			int current = 0;
			for (int j : distance)
				current += j >> i & 1;
			if (current % (maxPerMove + 1) != 0) {
				out.printLine("First");
				return;
			}
		}
		out.printLine("Second");
	}
}
