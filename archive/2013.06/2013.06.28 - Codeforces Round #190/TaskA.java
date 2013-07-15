package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int row = in.readInt();
		int column = in.readInt();
		if (row == 0 && column == 0) {
			out.printLine("Yes");
			return;
		}
		char[] program = in.readString().toCharArray();
		int[] pRow = new int[program.length];
		int[] pColumn = new int[program.length];
		int cRow = 0;
		int cColumn = 0;
		for (int i = 0; i < program.length; i++) {
			if (program[i] == 'U')
				cColumn++;
			else if (program[i] == 'D')
				cColumn--;
			else if (program[i] == 'L')
				cRow--;
			else
				cRow++;
			pRow[i] = cRow;
			pColumn[i] = cColumn;
		}
		for (int i = 0; i < program.length; i++) {
			if (pRow[i] == row && pColumn[i] == column) {
				out.printLine("Yes");
				return;
			}
			int delta = 0;
			if (cRow != 0)
				delta = (row - pRow[i]) / cRow;
			else if (cColumn != 0)
				delta = (column - pColumn[i]) / cColumn;
			long sRow = pRow[i] + (long)cRow * delta;
			long sColumn = pColumn[i] + (long)cColumn * delta;
			if (sRow == row && sColumn == column && delta >= 0) {
				out.printLine("Yes");
				return;
			}
		}
		out.printLine("No");
    }
}
