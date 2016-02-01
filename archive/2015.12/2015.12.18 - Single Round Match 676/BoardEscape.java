package net.egork;

import net.egork.misc.MiscUtils;

public class BoardEscape {
	public String findWinner(String[] s, int k) {
		int n = s.length;
		int m = s[0].length();
		int[][] value = new int[n][m];
		int[][] next = new int[n][m];
		for (int j = 0; j < n; j++) {
			for (int l = 0; l < m; l++) {
				if (s[j].charAt(l) == '#') {
					value[j][l] = 10;
				}
			}
		}
		for (int i = 0; i < k && (i < 5000 || i % 2 != k % 2); i++) {
			for (int j = 0; j < n; j++) {
				for (int l = 0; l < m; l++) {
					if (s[j].charAt(l) == '#') {
						next[j][l] = 10;
					} else if (s[j].charAt(l) == 'E') {
						next[j][l] = 0;
					} else {
						int used = 0;
						for (int o = 0; o < 4; o++) {
							int nj = j + MiscUtils.DX4[o];
							int nl = l + MiscUtils.DY4[o];
							if (MiscUtils.isValidCell(nj, nl, n, m))
								used |= 1 << value[nj][nl];
						}
						next[j][l] = Integer.bitCount(Integer.lowestOneBit(~used) - 1);
					}
				}
			}
			int[][] temp = value;
			value = next;
			next = temp;
		}
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (s[i].charAt(j) == 'T')
					answer ^= value[i][j];
			}
		}
		return answer != 0 ? "Alice" : "Bob";
	}
}
