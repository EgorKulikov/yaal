package net.egork;

import net.egork.collections.ArrayUtils;

public class TwoConvexShapes {
	long[][] result;
	char[][] map;
	private static final long MOD = (long) (1e9 + 7);

	public int countWays(String[] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length();
		map = new char[rowCount][];
		for (int i = 0; i < rowCount; i++)
			map[i] = grid[i].toCharArray();
		result = new long[rowCount + 1][columnCount + 1];
		long answer = 0;
		ArrayUtils.fill(result, -1);
		answer += go(0, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '?')
					map[i][j] = (char) ('W' + 'B' - map[i][j]);
			}
		}
		ArrayUtils.fill(result, -1);
		answer += go(0, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount / 2; j++) {
				char temp = map[i][j];
				map[i][j] = map[i][columnCount - j - 1];
				map[i][columnCount - j - 1] = temp;
			}
		}
		ArrayUtils.fill(result, -1);
		answer += go(0, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '?')
					map[i][j] = (char) ('W' + 'B' - map[i][j]);
			}
		}
		ArrayUtils.fill(result, -1);
		answer += go(0, columnCount);
		boolean allWhite = true;
		boolean allBlack = true;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'W')
					allBlack = false;
				else if (map[i][j] == 'B')
					allWhite = false;
			}
		}
		if (allWhite)
			answer -= 3;
		if (allBlack)
			answer -= 3;
		for (int i = 1; i < rowCount; i++) {
			boolean topAllWhite = true;
			boolean topAllBlack = true;
			boolean bottomAllWhite = true;
			boolean bottomAllBlack = true;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == 'W')
						topAllBlack = false;
					else if (map[j][k] == 'B')
						topAllWhite = false;
				}
			}
			for (int j = i; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == 'W')
						bottomAllBlack = false;
					else if (map[j][k] == 'B')
						bottomAllWhite = false;
				}
			}
			if (topAllWhite && bottomAllBlack)
				answer--;
			if (topAllBlack && bottomAllWhite)
				answer--;
		}
		for (int i = 1; i < columnCount; i++) {
			boolean topAllWhite = true;
			boolean topAllBlack = true;
			boolean bottomAllWhite = true;
			boolean bottomAllBlack = true;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < rowCount; k++) {
					if (map[k][j] == 'W')
						topAllBlack = false;
					else if (map[k][j] == 'B')
						topAllWhite = false;
				}
			}
			for (int j = i; j < columnCount; j++) {
				for (int k = 0; k < rowCount; k++) {
					if (map[k][j] == 'W')
						bottomAllBlack = false;
					else if (map[k][j] == 'B')
						bottomAllWhite = false;
				}
			}
			if (topAllWhite && bottomAllBlack)
				answer--;
			if (topAllBlack && bottomAllWhite)
				answer--;
		}
		return (int) ((answer + MOD) % MOD);
	}

	private long go(int row, int to) {
		if (result[row][to] != -1)
			return result[row][to];
		if (row == map.length)
			return result[row][to] = 1;
		int originalTo = to;
		for (int i = 0; i < to; i++) {
			if (map[row][i] == 'B')
				to = i;
		}
		for (int i = to; i < map[row].length; i++) {
			if (map[row][i] == 'W')
				return result[row][originalTo] = 0;
		}
		int from = -1;
		for (int i = to - 1; i > from; i--) {
			if (map[row][i] == 'W')
				from = i;
		}
		result[row][originalTo] = 0;
		for (int i = from + 1; i <= to; i++)
			result[row][originalTo] += go(row + 1, i);
		return result[row][originalTo] %= MOD;
	}


}

