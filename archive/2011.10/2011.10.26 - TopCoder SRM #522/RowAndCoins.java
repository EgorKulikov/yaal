package net.egork;

public class RowAndCoins {
	public String getWinner(String cells) {
		int[][] winner = new int[2][1 << cells.length()];
		if (go(0, 0, cells, winner) == 1)
			return "Alice";
		else
			return "Bob";
	}

	private int go(int player, int mask, String cells, int[][] winner) {
		if (winner[player][mask] != 0)
			return winner[player][mask];
		if (Integer.bitCount(mask) == cells.length() - 1) {
			for (int i = 0; i < cells.length(); i++) {
				if ((mask >> i & 1) == 0) {
					if (cells.charAt(i) == 'A' + player)
						return winner[player][mask] = 1;
					else
						return winner[player][mask] = -1;
				}
			}
		}
		for (int i = 0; i < cells.length(); i++) {
			for (int j = i + 1; j <= cells.length(); j++) {
				int curMask = ((1 << j) - (1 << i));
				if ((mask & curMask) == 0 && Integer.bitCount(mask + curMask) != cells.length() && go(1 - player, mask + curMask, cells, winner) == -1)
					return winner[player][mask] = 1;
			}
		}
		return winner[player][mask] = -1;
	}

}

