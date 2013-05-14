package net.egork;

public class FlipGame {
	public int minOperations(String[] board) {
		boolean[][] map = new boolean[board.length][board[0].length()];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++)
				map[i][j] = board[i].charAt(j) == '1';
		}
		for (int i = 0; ; i++) {
			int curWidth = 0;
			for (int j = 0; j < map.length; j++) {
				for (int k = curWidth; k < map[j].length; k++) {
					if (map[j][k])
						curWidth = k + 1;
				}
				for (int k = 0; k < curWidth; k++)
					map[j][k] ^= true;
			}
			if (curWidth == 0)
				return i;
		}
	}


}

