package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1249 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		boolean[] lastRow = new boolean[columnCount];
		boolean[] nextRow = new boolean[columnCount];
		for (int i = 0; i < rowCount; i++) {
			boolean inLastRowSegment = false;
			boolean inNextRowSegment = false;
			for (int j = 0; j < columnCount; j++) {
				nextRow[j] = in.readCharacter() == '1';
				if (inLastRowSegment != inNextRowSegment && lastRow[j] && nextRow[j] ||
					inLastRowSegment && inNextRowSegment && lastRow[j] != nextRow[j])
				{
					out.println("No");
					return;
				}
				inLastRowSegment = lastRow[j];
				inNextRowSegment = nextRow[j];
			}
			boolean[] temp = lastRow;
			lastRow = nextRow;
			nextRow = temp;
		}
		out.println("Yes");
	}
}

