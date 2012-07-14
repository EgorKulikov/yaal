package April2011.CodeforcesBetaRound68;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int rowCount = in.readInt() - 1;
		int columnCount = in.readInt() - 1;
		boolean[] spotted = new boolean[columnCount + 1];
		rowCount *= 2;
		int result = 0;
		int columnMod = 2 * columnCount;
		rowCount %= columnMod;
		for (int i = 0; i <= columnCount; i++) {
			if (spotted[i])
				continue;
			result++;
			int column = i;
			while (true) {
				column += rowCount;
				if (column >= columnMod)
					column -= columnMod;
				int actualColumn = column <= columnCount ? column : columnMod - column;
				spotted[actualColumn] = true;
				if (column == i)
					break;
			}
			column = i;
			while (true) {
				column -= rowCount;
				if (column < 0)
					column += columnMod;
				int actualColumn = column <= columnCount ? column : columnMod - column;
				spotted[actualColumn] = true;
				if (column == i)
					break;
			}
		}
		out.println(result);
	}
}


