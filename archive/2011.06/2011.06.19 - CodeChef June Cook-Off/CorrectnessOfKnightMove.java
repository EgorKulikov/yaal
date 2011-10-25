import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class CorrectnessOfKnightMove implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String s = in.readLine(false);
		if (s.equals(""))
			s = in.readLine(false);
		if (s.length() != 5 || s.charAt(2) != '-') {
			out.println("Error");
			return;
		}
		int row = s.charAt(0) - 'a';
		int column = s.charAt(1) - '1';
		int otherRow = s.charAt(3) - 'a';
		int otherColumn = s.charAt(4) - '1';
		if (row < 0 || row > 7 || column < 0 || column > 7 || otherRow < 0 || otherRow > 7 || otherColumn < 0 || otherColumn > 7) {
			out.println("Error");
			return;
		}
		if (Math.abs(row - otherRow) + Math.abs(column - otherColumn) == 3 && row != otherRow && column != otherColumn)
			out.println("Yes");
		else
			out.println("No");
	}
}

