package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Task1413 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double x = 0;
		double y = 0;
		double shift = Math.sqrt(.5);
		double[] dx = {0, -shift, 0, shift, -1, 0, 1, -shift, 0, shift};
		double[] dy = {0, -shift, -1, -shift, 0, 0, 0, shift, 1, shift};
		try {
			//noinspection InfiniteLoopStatement
			while (true) {
				char move = in.readCharacter();
				if (move == '0')
					break;
				x += dx[move - '0'];
				y += dy[move - '0'];
			}
		} catch (InputMismatchException ignored) {
		}
		out.printf("%.10f %.10f\n", x, y);
	}
}

