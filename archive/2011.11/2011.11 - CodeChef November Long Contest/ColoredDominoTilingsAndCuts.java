package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ColoredDominoTilingsAndCuts {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (rowCount * columnCount % 2 == 1) {
			out.printLine("IMPOSSIBLE");
			return;
		}
		int min = Math.min(rowCount, columnCount);
		int max = Math.max(rowCount, columnCount);
		char[][] table = new char[min][max];
		int cuts;
		int colors;
		if (min == 1) {
			if (max == 2) {
				cuts = 0;
				colors = 1;
				table[0] = "aa".toCharArray();
			} else {
				cuts = max / 2 - 1;
				colors = 2;
				for (int i = 0; i < max; i++) {
					if (i % 4 < 2)
						table[0][i] = 'a';
					else
						table[0][i] = 'b';
				}
			}
		} else if (min == 2) {
			if (max % 2 == 0) {
				cuts = max / 2;
				colors = 2;
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < max; j++) {
						if ((i == 0) ^ (j % 4 < 2))
							table[i][j] = 'b';
						else
							table[i][j] = 'a';
					}
				}
			} else {
				cuts = max / 2;
				colors = 3;
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < max - 1; j++) {
						if ((i == 0) ^ (j % 4 < 2))
							table[i][j] = 'b';
						else
							table[i][j] = 'a';
					}
				}
				table[0][max - 1] = table[1][max - 1] = 'c';
			}
		} else if (min == 3) {
			cuts = 1;
			colors = 3;
			if (max == 4) {
				table[0] = "aabc".toCharArray();
				table[1] = "bcbc".toCharArray();
				table[2] = "bcaa".toCharArray();
			} else {
				for (int i = 0; i < max; i++)
					table[0][i] = (char) ('a' + i / 2 % 3);
				for (int i = 1; i < max - 1; i++)
					table[1][i] = (char) ('a' + (i + 3) / 2 % 3);
				for (int i = 1; i < max - 1; i++)
					table[2][i] = (char) ('a' + (i - 1) / 2 % 3);
				table[1][0] = table[2][0] = 'b';
				table[2][max - 2] = table[2][max - 3] = table[1][max - 5];
				table[1][max - 1] = table[2][max - 1] = table[0][max - 3];
			}
		} else if (min == 4) {
			if (max % 2 == 0) {
				cuts = 2;
				colors = 3;
				for (int i = 2; i < max; i++)
					table[0][i] = (char) ('a' + (i - 2) / 2 % 3);
				for (int i = 2; i < max; i++)
					table[1][i] = (char) ('a' + (i + 2) / 2 % 3);
				for (int i = 1; i < max - 1; i++)
					table[2][i] = (char) ('a' + (i - 1) / 2 % 3);
				for (int i = 1; i < max - 1; i++)
					table[3][i] = (char) ('a' + (i + 3) / 2 % 3);
				table[0][0] = table[1][0] = 'a';
				table[0][1] = table[1][1] = 'b';
				table[2][0] = table[3][0] = 'b';
				table[2][max - 1] = table[3][max - 1] = (char) ('a' + (max - 2) / 2 % 3);
			} else {
				cuts = 1;
				colors = 3;
				for (int i = 1; i < max; i++)
					table[0][i] = (char) ('a' + (i - 1) / 2 % 3);
				for (int i = 1; i < max; i++)
					table[1][i] = (char) ('a' + (i + 3) / 2 % 3);
				for (int i = 0; i < max - 1; i++)
					table[2][i] = (char) ('a' + i / 2 % 3);
				for (int i = 0; i < max - 1; i++)
					table[3][i] = (char) ('a' + (i + 4) / 2 % 3);
				table[0][0] = table[1][0] = 'b';
				table[2][max - 1] = table[3][max - 1] = (char) ('a' + (max - 1) / 2 % 3);
			}
		} else if (min == 6 && max == 6) {
			cuts = 1;
			colors = 3;
			table[0] = "aabbab".toCharArray();
			table[1] = "cbccab".toCharArray();
			table[2] = "cbabba".toCharArray();
			table[3] = "bcacca".toCharArray();
			table[4] = "bcbabb".toCharArray();
			table[5] = "aabacc".toCharArray();
		} else {
			cuts = 0;
			colors = 3;
			if (min % 2 == 1 || max % 2 == 1) {
				if (min % 2 == 0 && max % 2 == 1) {
					min = max;
					max = rowCount + columnCount - min;
					table = new char[min][max];
				}
				System.arraycopy("aabaa".toCharArray(), 0, table[0], 0, 5);
				System.arraycopy("bcbcc".toCharArray(), 0, table[1], 0, 5);
				System.arraycopy("bcaabc".toCharArray(), 0, table[2], 0, 6);
				System.arraycopy("cbbcbc".toCharArray(), 0, table[3], 0, 6);
				System.arraycopy("caac".toCharArray(), 0, table[4], 0, 4);
				for (int i = 5; i < max - 1; i += 2)
					table[0][i] = table[0][i + 1] = (char) ('a' + 2 * ((i / 2 + 1) % 2));
				for (int i = 5; i < max - 1; i += 2)
					table[1][i] = table[1][i + 1] = (char) ('a' + 2 * ((i / 2) % 2));
				table[0][max - 1] = table[1][max - 1] = 'b';
				for (int i = 6; i < max; i += 2) {
					table[2][i] = table[3][i] = 'b';
					table[2][i + 1] = table[3][i + 1] = (char) ('a' + 2 * ((i / 2 + 1) % 2));
				}
				for (int i = 6; i < max; i += 2)
					table[4][i] = table[4][i + 1] = (char) ('a' + 2 * ((i / 2) % 2));
				for (int i = 4; i < min - 1; i += 2) {
					table[i][4] = table[i + 1][4] = (char) ('a' + (2 * (i / 2 + 1) % 3));
					table[i][5] = table[i + 1][5] = (char) ('a' + (2 * (i / 2) % 3));
				}
				table[min - 1][4] = table[min - 1][5] = (char) ('a' + (2 * (min / 2 + 1) % 3));
				for (int i = 5; i < min; i += 2) {
					table[i][0] = table[i + 1][0] = (char) ('a' + (2 * (i / 2 + 1) % 3));
					table[i][1] = table[i + 1][1] = (char) ('a' + (2 * (i / 2) % 3));
					table[i][2] = table[i + 1][2] = (char) ('a' + (2 * (i / 2 + 2) % 3));
					table[i][3] = table[i + 1][3] = (char) ('a' + (2 * (i / 2) % 3));
				}
				for (int i = 5; i < min; i++) {
					for (int j = 6; j < max; j += 2) {
						for (char c = 'a'; c <= 'c'; c++) {
							if (table[i][j - 1] != c && table[i - 1][j] != c) {
								table[i][j] = table[i][j + 1] = c;
								break;
							}
						}
					}
				}
				/*
				table[0][0] = table[0][1] = 'b';
				table[0][2] = table[0][3] = 'a';
				table[1][0] = table[2][0] = 'd';
				table[1][1] = table[1][2] = 'c';
				table[1][3] = table[2][3] = 'b';
				table[2][1] = table[3][1] = 'b';
				table[2][2] = table[3][2] = 'd';
				table[3][0] = table[4][0] = 'a';
				for (int i = 4; i < max; i++)
					table[0][i] = table[1][i] = (char) ('b' + (i + 1) % 2);
				for (int i = 4; i < max; i += 2)
					table[2][i] = table[2][i + 1] = (char) ('a' + 3 * ((i / 2 + 1) % 2));
				for (int i = 3; i < max - 1; i += 2)
					table[3][i] = table[3][i + 1] = (char) ('b' + (i / 2) % 2);
				for (int i = 3; i < max - 1; i += 2)
					table[4][i] = table[4][i + 1] = (char) ('b' + (i / 2 + 1) % 2);
				if (table[2][max - 1] == 'a')
					table[3][max - 1] = table[4][max - 1] = 'd';
				else
					table[3][max - 1] = table[4][max - 1] = 'a';
				for (int i = 5; i < min; i += 2)
					table[i][0] = table[i + 1][0] = (char) ('a' + ((i + 1) / 2) % 2);
				for (int i = 4; i < min - 1; i += 2)
					table[i][1] = table[i + 1][1] = (char) ('c' + (i / 2) % 2);
				for (int i = 4; i < min - 1; i += 2)
					table[i][2] = table[i + 1][2] = (char) ('a' + (i / 2) % 2);
				for (int i = 5; i < min; i += 2)
					table[i][3] = table[i + 1][3] = (char) ('c' + (i / 2) % 2);
				if (min % 4 == 1)
					table[min - 1][1] = table[min - 1][2] = 'c';
				else
					table[min - 1][1] = table[min - 1][2] = 'd';
				for (int i = 5; i < min; i += 2) {
					for (int j = 4; j < max - 1; j++)
						table[i][j] = table[i + 1][j] = (char) ('a' + 3 * ((i / 2 + j + 1) % 2));
				}
				for (int i = 5; i < min; i += 2)
					table[i][max - 1] = table[i + 1][max - 1] = (char) ('b' + (i / 2 % 2));*/
			} else {
				System.arraycopy("baaccbb".toCharArray(), 0, table[0], 0, 7);
				System.arraycopy("bccabcc".toCharArray(), 0, table[1], 0, 7);
				System.arraycopy("aabababb".toCharArray(), 0, table[2], 0, 8);
				System.arraycopy("bcbccacc".toCharArray(), 0, table[3], 0, 8);
				System.arraycopy("bcaabbab".toCharArray(), 0, table[4], 0, 8);
				System.arraycopy("aabbccab".toCharArray(), 0, table[5], 0, 8);
				for (int i = 7; i < max - 1; i += 2) {
					table[0][i] = table[0][i + 1] = (char) ('a' + ((i / 2 + 2) % 3));
					table[1][i] = table[1][i + 1] = (char) ('a' + ((i / 2) % 3));
				}
				table[0][max - 1] = table[1][max - 1] = (char) ('a' + ((max / 2 + 2) % 3));
				for (int i = 8; i < max; i += 2)
					table[2][i] = table[2][i + 1] = (char) ('a' + ((i / 2 + 1) % 3));
				for (int i = 5; i < min - 1; i += 2) {
					table[i][0] = table[i + 1][0] = (char) ('a' + ((i / 2) % 3));
					table[i][1] = table[i + 1][1] = (char) ('a' + ((i / 2 + 1) % 3));
				}
				table[min - 1][0] = table[min - 1][1] = (char) ('a' + ((min / 2) % 3));
				for (int i = 6; i < min; i += 2) {
					table[i][2] = table[i + 1][2] = (char) ('a' + ((i / 2 + 2) % 3));
					table[i][3] = table[i + 1][3] = (char) ('a' + ((i / 2) % 3));
					table[i][4] = table[i + 1][4] = (char) ('a' + ((i / 2 + 1) % 3));
					table[i][5] = table[i + 1][5] = (char) ('a' + ((i / 2) % 3));
					table[i][6] = table[i + 1][6] = (char) ('a' + ((i / 2 + 1) % 3));
					table[i][7] = table[i + 1][7] = (char) ('a' + ((i / 2 + 2) % 3));
				}
				for (int i = 3; i < min; i++) {
					for (int j = 8; j < max; j += 2) {
						for (char c = 'a'; c <= 'c'; c++) {
							if (table[i][j - 1] != c && table[i - 1][j] != c) {
								table[i][j] = table[i][j + 1] = c;
								break;
							}
						}
					}
				}
				/*table[0][0] = table[1][0] = 'a';
				table[0][1] = table[0][2] = 'b';
				table[1][1] = table[2][1] = 'c';
				table[1][2] = table[2][2] = 'a';
				table[2][0] = table[3][0] = 'b';
				table[2][3] = table[3][3] = 'c';
				table[2][4] = table[2][5] = 'a';
				table[3][1] = table[3][2] = 'd';
				table[3][4] = table[4][4] = 'd';
				table[3][5] = table[4][5] = 'b';
				table[4][0] = table[4][1] = 'a';
				table[4][2] = table[4][3] = 'b';
				table[4][6] = table[5][6] = 'd';
				table[4][7] = table[5][7] = 'b';
				table[5][2] = table[5][3] = 'a';
				table[5][4] = table[5][5] = 'c';
				for (int i = 3; i < max - 1; i += 2)
					table[0][i] = table[0][i + 1] = (char) ('b' + (i / 2) % 2);
				for (int i = 3; i < max - 1; i += 2)
					table[1][i] = table[1][i + 1] = (char) ('b' + (i / 2 + 1) % 2);
				if (max % 4 == 0)
					table[0][max - 1] = table[1][max - 1] = 'a';
				else
					table[0][max - 1] = table[1][max - 1] = 'd';
				for (int i = 6; i < max; i += 2)
					table[2][i] = table[2][i + 1] = (char) ('a' + 3 * (i / 2 % 2));
				for (int i = 6; i < max; i += 2)
					table[3][i] = table[3][i + 1] = (char) ('a' + 3 * ((i / 2 + 1) % 2));
				for (int i = 8; i < max; i += 2)
					table[4][i] = table[4][i + 1] = (char) ('a' + 3 * (i / 2 % 2));
				for (int i = 8; i < max; i += 2)
					table[5][i] = table[5][i + 1] = (char) ('a' + 3 * ((i / 2 + 1) % 2));
				for (int i = 5; i < min - 1; i += 2)
					table[i][0] = table[i + 1][0] = (char) ('b' + (i / 2) % 2);
				for (int i = 5; i < min - 1; i += 2)
					table[i][1] = table[i + 1][1] = (char) ('b' + (i / 2 + 1) % 2);
				if (min % 4 == 0)
					table[min - 1][0] = table[min - 1][1] = 'a';
				else
					table[min - 1][0] = table[min - 1][1] = 'd';
				for (int i = 6; i < min; i += 2)
					table[i][2] = table[i + 1][2] = (char) ('a' + 3 * ((i / 2) % 2));
				for (int i = 6; i < min; i += 2)
					table[i][3] = table[i + 1][3] = (char) ('b' + ((i / 2 + 1) % 2));
				for (int i = 6; i < min; i += 2)
					table[i][4] = table[i + 1][4] = (char) ('a' + 3 * ((i / 2 + 1) % 2));
				for (int i = 6; i < min; i += 2)
					table[i][5] = table[i + 1][5] = (char) ('a' + 3 * ((i / 2) % 2));
				for (int i = 6; i < min; i += 2) {
					for (int j = 6; j < max; j++)
						table[i][j] = table[i + 1][j] = (char) ('b' + (i / 2 + j + 1) % 2);
				}*/
			}
		}
		out.printLine(cuts, colors);
		if (min != rowCount) {
			char[][] reverted = new char[max][min];
			for (int i = 0; i < min; i++) {
				for (int j = 0; j < max; j++)
					reverted[j][i] = table[i][j];
			}
			table = reverted;
		}
		for (char[] row : table)
			out.printLine(new String(row));
	}
}
