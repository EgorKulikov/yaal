package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleshipOnePlayer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String type = in.readString();
		if ("INIT".equals(type)) {
			Random random = new Random();
			boolean[][] occupied = new boolean[10][10];
			occupied[9][0] = occupied[9][1] = true;
			occupied[0][9] = occupied[1][9] = true;
			occupied[4][4] = occupied[4][5] = occupied[4][6] = true;
			for (int i = 3; i <= 6; i++)
				occupied[5][i] = true;
			for (int i = 3; i <= 7; i++)
				occupied[6][i] = true;
			List<IntPair> variants = new ArrayList<IntPair>();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!occupied[i][j])
						variants.add(new IntPair(i, j));
				}
			}
			int index = random.nextInt(variants.size());
			out.printLine(variants.get(index).first, variants.get(index).second);
			int second;
			while ((second = random.nextInt(variants.size())) == index);
			out.printLine(variants.get(second).first, variants.get(second).second);
			out.printLine("9 0:9 1");
			out.printLine("0 9:1 9");
			out.printLine("4 4:4 6");
			out.printLine("5 3:5 6");
			out.printLine("6 3:6 7");
			return;
		}
		Random random = new Random(239);
		char[][] board = IOUtils.readTable(in, 10, 10);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == 'h') {
					if (i > 0 && board[i - 1][j] == '-') {
						out.printLine(i - 1, j);
						return;
					}
					if (j > 0 && board[i][j - 1] == '-') {
						out.printLine(i, j - 1);
						return;
					}
					if (i < 9 && board[i + 1][j] == '-') {
						out.printLine(i + 1, j);
						return;
					}
					if (j < 9 && board[i][j + 1] == '-') {
						out.printLine(i, j + 1);
						return;
					}
				}
			}
		}
		int[][] score = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 5 && i + k < 10; k++) {
					if (board[i + k][j] == '-') {
						for (int l = 0; l <= k; l++)
							score[i + l][j] += k < 2 ? 2 : 1;
					} else
						break;
				}
				for (int k = 0; k < 5 && j + k < 10; k++) {
					if (board[i][j + k] == '-') {
						for (int l = 0; l <= k; l++)
							score[i][j + l] += k < 2 ? 2 : 1;
					} else
						break;
				}
			}
		}
		int maxScore = 0;
		for (int[] row : score) {
			for (int i : row)
				maxScore = Math.max(maxScore, i);
		}
		List<IntPair> variants = new ArrayList<IntPair>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (score[i][j] == maxScore)
					variants.add(new IntPair(i, j));
			}
		}
		int index = random.nextInt(variants.size());
		out.printLine(variants.get(index).first, variants.get(index).second);
    }
}
