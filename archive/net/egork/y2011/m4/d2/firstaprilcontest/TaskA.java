package net.egork.y2011.m4.d2.firstaprilcontest;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int antCount = in.readInt();
		int side = in.readInt();
		boolean[] rows = new boolean[side];
		boolean[] columns = new boolean[side];
		for (int i = 0; i < antCount; i++) {
			int x = in.readInt() - 1;
			int y = in.readInt() - 1;
			char direction = in.readCharacter();
			if (direction == 'N' || direction == 'S')
				columns[x] = true;
			else
				rows[y] = true;
		}
		long freeRows = 0;
		for (int i = 0; i < side; i++) {
			if (!rows[i])
				freeRows++;
		}
		long freeColumns = 0;
		for (int i = 0; i < side; i++) {
			if (!columns[i])
				freeColumns++;
		}
		long result = 2 * freeRows * freeColumns + 2 * side * (freeRows + freeColumns);
		out.println(result);
	}
}

