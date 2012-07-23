package net.egork;

import net.egork.misc.MiscUtils;

import java.util.Arrays;

public class AlphabetPaths {
	private long[] halfCount;
	private int[] index, letterIndex;
	private char[][] maze;

	public long count(String[] letterMaze) {
		char[] letters = "A B C D E F Z H I K L M N O P Q R S T V X ".toCharArray();
		letterIndex = new int[256];
		for (int i = 0; i < letters.length; i += 2)
			letterIndex[letters[i]] = i / 2;
		index = new int[1 << 21];
		int count = 0;
		for (int i = 0; i < index.length; i++) {
			if (Integer.bitCount(i) == 11)
				index[i] = count++;
		}
		int[] mask = new int[count];
		count = 0;
		for (int i = 0; i < index.length; i++) {
			if (Integer.bitCount(i) == 11)
				mask[count++] = i;
		}
		int rowCount = letterMaze.length;
		int columnCount = letterMaze[0].length();
		halfCount = new long[count];
		maze = new char[rowCount + 2][];
		maze[0] = new char[columnCount + 2];
		Arrays.fill(maze[0], '.');
		maze[rowCount + 1] = new char[columnCount + 2];
		Arrays.fill(maze[rowCount + 1], '.');
		for (int i = 1; i <= rowCount; i++)
			maze[i] = ("" + letterMaze[i - 1] + "").toCharArray();
		for (int i = 0; i <= rowCount + 1; i++) {
			for (int j = 0; j <= columnCount + 1; j++) {
				if (maze[i][j] != '.')
					maze[i][j] = (char) letterIndex[maze[i][j]];
				else
					maze[i][j] = 22;
			}
		}
		long answer = 0;
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 1; j <= columnCount; j++) {
				if (maze[i][j] == 22)
					continue;
				Arrays.fill(halfCount, 0);
				go(i, j, 10, (1 << maze[i][j]));
				int all = (1 << 21) - 1 + (1 << maze[i][j]);
				for (int k = 0; k < count; k++) {
					if (halfCount[k] == 0)
						continue;
					int opposite = index[all - mask[k]];
					answer += halfCount[k] * halfCount[opposite];
				}
			}
		}
		return answer;
	}

	private void go(int row, int column, int steps, int mask) {
		if (steps == 0) {
			halfCount[index[mask]]++;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int newRow = row + MiscUtils.DX4[i];
			int newColumn = column + MiscUtils.DY4[i];
			char letter = maze[newRow][newColumn];
			if (letter != 22 && (mask >> letter & 1) == 0)
				go(newRow, newColumn, steps - 1, mask + (1 << letter));
		}
	}


}

