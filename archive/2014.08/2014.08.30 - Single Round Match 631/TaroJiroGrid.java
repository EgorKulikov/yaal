package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class TaroJiroGrid {
	char[][] map;
	int count;

    public int getNumber(String[] grid) {
		count = grid.length;
		map = new char[count][];
		for (int i = 0; i < count; i++) {
			map[i] = grid[i].toCharArray();
		}
		if (check(map)) {
			return 0;
		}
		char[][] copy = new char[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				System.arraycopy(map[j], 0, copy[j], 0, count);
			}
			Arrays.fill(copy[i], 'W');
			if (check(copy)) {
				return 1;
			}
			Arrays.fill(copy[i], 'B');
			if (check(copy)) {
				return 1;
			}
		}
		return 2;
    }

	private boolean check(char[][] map) {
		for (int i = 0; i < count; i++) {
			int lastWhite = -1;
			int lastBlack = -1;
			for (int j = 0; j < count; j++) {
				if (map[j][i] == 'W') {
					lastWhite = j;
				} else {
					lastBlack = j;
				}
				if (j - Math.min(lastWhite, lastBlack) > (count >> 1)) {
					return false;
				}
			}
		}
		return true;
	}
}
