package net.egork;

public class RobotOnMoon {
    public int longestSafeCommand(String[] board) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		int answer = rowCount - 1 + columnCount - 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == 'S') {
					for (int k = 0; k < rowCount; k++) {
						if (board[k].charAt(j) == '#') {
							return -1;
						}
					}
					for (int k = 0; k < columnCount; k++) {
						if (board[i].charAt(k) == '#') {
							return -1;
						}
					}
				}
			}
		}
		return answer;
    }
}
