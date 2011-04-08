package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Task1638 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int bookWidth = in.readInt();
		int coverWidth = in.readInt();
		int startBook = in.readInt();
		int endBook = in.readInt();
		int result;
		if (endBook > startBook)
			result = 2 * (endBook - startBook) * coverWidth + (endBook - startBook - 1) * bookWidth;
		else
			result = bookWidth * (startBook - endBook + 1) + 2 * coverWidth * (startBook - endBook);
		out.println(result);
	}
}

