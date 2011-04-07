package net.egork.y2011.m4.d2.localcontestfrommexico;

import net.egork.collections.Pair;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.io.stringinputreader.StringInputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class FaceTheMaze implements Solver {
	private static final int[] D_COLUMN = {1, 0, 0, -1};
	private static final int[] D_ROW = {0, 1, -1, 0};

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		in = new StringInputReader(in.readLine());
		int n = in.readInt();
		Pair<Integer, Integer> start = readCell(in);
		Pair<Integer, Integer> target = readCell(in);
		boolean[][] map = new boolean[n][n];
		try {
			//noinspection InfiniteLoopStatement
			while (true) {
				Pair<Integer, Integer> cell = readCell(in);
				map[cell.first() - 1][cell.second() - 1] = true;
			}
		} catch (InputMismatchException ignored) {
		}
		boolean[][] visited = new boolean[n][n];
		go(start, target, map, visited, true, out);
		out.println();
	}

	private boolean go(Pair<Integer, Integer> start, Pair<Integer, Integer> target, boolean[][] map,
		boolean[][] visited, boolean first, PrintWriter out)
	{
		if (visited[start.first() - 1][start.second() - 1])
			return false;
		if (first)
			first = false;
		else
			out.print(' ');
		out.print(start);
		visited[start.first() - 1][start.second() - 1] = true;
		if (start.equals(target))
			return true;
		if (Math.abs(start.first() - target.first()) + Math.abs(start.second() - target.second()) <= 1)
			return go(target, target, map, visited, first, out);
		for (int i = 0; i < 4; i++) {
			int nextRow = start.first() + D_ROW[i];
			int nextColumn = start.second() + D_COLUMN[i];
			if (nextRow > 0 && nextRow <= map.length && nextColumn > 0 && nextColumn <= map.length && !visited[nextRow - 1][nextColumn - 1] && !map[nextRow - 1][nextColumn - 1]) {
				if (go(new Pair<Integer, Integer>(nextRow, nextColumn), target, map, visited, first, out))
					return true;
				out.print(' ');
				out.print(start);
			}
		}
		return false;
	}

	private Pair<Integer, Integer> readCell(InputReader in) {
		String cell = in.readString();
		return new Pair<Integer, Integer>(Integer.parseInt(cell.substring(1).split(",")[0]),
			Integer.parseInt(cell.substring(0, cell.length() - 1).split(",")[1]));
	}
}

