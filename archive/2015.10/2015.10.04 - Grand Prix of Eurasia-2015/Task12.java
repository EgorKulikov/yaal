package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Task12 {
	char[][] field = {"rwwgwwwrwwwgwwr".toCharArray(),
		"wbwwwywwwywwwbw".toCharArray(),
		"wwbwwwgwgwwwbww".toCharArray(),
		"gwwbwwwgwwwbwwg".toCharArray(),
		"wwwwbwwwwwbwwww".toCharArray(),
		"wywwwwwwwwwwwyw".toCharArray(),
		"wwgwwwgwgwwwgww".toCharArray(),
		"rwwgwwwswwwgwwr".toCharArray(),
		"wwgwwwgwgwwwgww".toCharArray(),
		"wywwwwwwwwwwwyw".toCharArray(),
		"wwwwbwwwwwbwwww".toCharArray(),
		"gwwbwwwgwwwbwwg".toCharArray(),
		"wwbwwwgwgwwwbww".toCharArray(),
		"wbwwwywwwywwwbw".toCharArray(),
		"rwwgwwwrwwwgwwr".toCharArray()
	};

	int[] coefs = {
		'w', 1, 1,
		's', 1, 1,
		'g', 2, 1,
		'y', 3, 1,
		'b', 1, 2,
		'r', 1, 3
	};

	int[] price = {
		1,
		3,
		2,
		3,
		2,
		1,
		5,
		5,
		1,
		2,
		2,
		2,
		2,
		1,
		1,
		2,
		2,
		2,
		2,
		3,
		10,
		5,
		10,
		5,
		10,
		10,
		10,
		5,
		5,
		10,
		10,
		3

	};

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		boolean[][] occupied = new boolean[15][15];
		int[] score = new int[n];
		for (int i = 0; i < m; i++) {
			int w = in.readInt();
			int used = 0;
			for (int j = 0; j < w; j++) {
				int l = in.readInt();
				char dir = in.readCharacter();
				int dx = dir == 'h' ? 0 : 1;
				int dy = dir == 'h' ? 1 : 0;
				int sy = in.readInt() - 1;
				int sx = in.readInt() - 1;
				int pts = 0;
				int cw = 1;
				int x = sx;
				int y = sy;
				for (int k = 0; k < l; k++) {
					int id = in.readInt() - 1;
					if (!occupied[x][y]) {
						used++;
					}
					occupied[x][y] = true;
					pts += price[id] * getLetterCoef(x, y);
					cw *= getWordCoef(x, y);
					x += dx;
					y += dy;
				}
				score[i % n] += pts * cw;
			}
			if (used == 7) {
				score[i % n] += 15;
			}
		}
		for (int i : score) {
			out.printLine(i);
		}
	}

	private int getLetterCoef(int x, int y) {
		char color = field[x][y];
		for (int i = 0; ; i += 3) {
			if (color == coefs[i]) {
				return coefs[i + 1];
			}
		}
	}

	private int getWordCoef(int x, int y) {
		char color = field[x][y];
		for (int i = 0; ; i += 3) {
			if (color == coefs[i]) {
				return coefs[i + 2];
			}
		}
	}
}
