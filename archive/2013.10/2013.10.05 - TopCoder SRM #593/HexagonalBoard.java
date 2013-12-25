package net.egork;

import net.egork.misc.MiscUtils;

public class HexagonalBoard {
	int[] dx = {-1, -1, 0, 0, 1, 1};
	int[] dy = {0, 1, -1, 1, -1, 0};

    public int minColors(String[] board) {
		int count = board.length;
		char[][] map = new char[count][];
		for (int i = 0; i < count; i++)
			map[i] = board[i].toCharArray();
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (map[i][j] != 'X')
					continue;
				answer = Math.max(answer, 1);
				for (int k = 0; k < 6; k++) {
					int row = i + dx[k];
					int column = j + dy[k];
					if (MiscUtils.isValidCell(row, column, count, count) && map[row][column] == 'X') {
						answer = Math.max(answer, 2);
						break;
					}
				}
				if (color(i, j, 0, map))
					return 3;
			}
		}
		return answer;
    }

	private boolean color(int row, int column, int color, char[][] map) {
		if (!MiscUtils.isValidCell(row, column, map.length, map.length))
			return false;
		if (map[row][column] == '-')
			return false;
		if (map[row][column] != 'X')
			return map[row][column] != color;
		map[row][column] = (char) color;
		for (int i = 0; i < 6; i++) {
			if (color(row + dx[i], column + dy[i], 1 - color, map))
				return true;
		}
		return false;
	}
}
