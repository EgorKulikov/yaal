package net.egork.y2011.m4.d2.firstaprilcontest;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = in.readTable(rowCount, columnCount);
		int[][] maxMountain = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			maxMountain[i][columnCount - 1] = table[i][columnCount - 1] == '0' ? 1 : 0;
			for (int j = columnCount - 2; j >= 0; j--)
				maxMountain[i][j] = table[i][j] == '0' ? 1 + maxMountain[i][j + 1] : 0;
		}
		int answer = 0;
		for (int i = 0; i < columnCount; i++) {
			int currentSystem = 0;
			for (int j = 0; j < rowCount; j++) {
				if (maxMountain[j][i] == 0) {
					answer = Math.max(answer, currentSystem);
					currentSystem = 0;
				} else
					currentSystem += maxMountain[j][i];
			}
			answer = Math.max(answer, currentSystem);
		}
		out.println(answer);
	}
}

