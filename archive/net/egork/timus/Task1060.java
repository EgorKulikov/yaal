package net.egork.timus;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Task1060 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[][] table = new int[4][4];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				table[i][j] = index++;
		}
		int[] moves = new int[16];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int mask = 1 << table[i][j];
				for (int k = 0; k < 4; k++) {
					int row = i + MiscUtils.DX_4_CONNECTED[k];
					int column = j + MiscUtils.DY_4_CONNECTED[k];
					if (MiscUtils.isValidCell(row, column, 4, 4))
						mask += 1 << table[row][column];
				}
				moves[table[i][j]] = mask;
			}
		}
		int[] moveCount = new int[1 << 16];
		Arrays.fill(moveCount, -1);
		moveCount[0] = 0;
		moveCount[(1 << 16) - 1] = 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(0);
		queue.add((1 << 16) - 1);
		while (!queue.isEmpty()) {
			int position = queue.poll();
			for (int move : moves) {
				int nextPosition = position ^ move;
				if (moveCount[nextPosition] == -1) {
					moveCount[nextPosition] = moveCount[position] + 1;
					queue.add(nextPosition);
				}
			}
		}
		int startPosition = 0;
		for (int i = 0; i < 16; i++) {
			if (in.readCharacter() == 'b')
				startPosition += 1 << i;
		}
		if (moveCount[startPosition] == -1)
			out.println("Impossible");
		else
			out.println(moveCount[startPosition]);
	}
}

