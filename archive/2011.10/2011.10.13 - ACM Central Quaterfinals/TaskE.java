import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[][] table = IOUtils.readTable(in, 16, 16);
		int minBlack = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (table[i][j] == '#')
					minBlack++;
			}
		}
		int dRow = 0;
		int dColumn = 0;
		for (int i = 0; i <= 15; i++) {
			for (int j = (i == 0 ? 1 : -15); j <= 15; j++) {
				boolean good = true;
				boolean[][] accounted = new boolean[16][16];
				int black = 0;
				for (int k = 0; k < 16 && good; k++) {
					for (int l = 0; l < 16 && good; l++) {
						if (accounted[k][l])
							continue;
						int row = k;
						int column = l;
						int length = 0;
						while (row < 16 && column >= 0 && column < 16 && good) {
							accounted[row][column] = true;
							boolean current = table[row][column] == '#';
							if (current)
								length++;
							else if (length == 1)
								good = false;
							else {
								black += (length + 1) / 2;
								length = 0;
							}
							row += i;
							column += j;
						}
						if (length == 1)
							good = false;
						else {
							black += (length + 1) / 2;
							length = 0;
						}
					}
				}
				if (!good)
					continue;
				if (black < minBlack) {
					minBlack = black;
					dRow = i;
					dColumn = j;
				}
			}
		}
		if (dRow != 0 || dColumn != 0) {
			boolean[][] accounted = new boolean[16][16];
			for (int k = 0; k < 16; k++) {
				for (int l = 0; l < 16; l++) {
					if (accounted[k][l])
						continue;
					int row = k;
					int column = l;
					int length = 0;
					while (row < 16 && column >= 0 && column < 16) {
						accounted[row][column] = true;
						boolean current = table[row][column] == '#';
						if (current)
							length++;
						else if (length != 0) {
							int r = row - dRow;
							int c = column - dColumn;
							while (length >= 2) {
								table[r][c] = '.';
								r -= 2 * dRow;
								c -= 2 * dColumn;
								length -= 2;
							}
							length = 0;
						}
						row += dRow;
						column += dColumn;
					}
					if (length != 0) {
							int r = row - dRow;
							int c = column - dColumn;
							while (length >= 2) {
								table[r][c] = '.';
								r -= 2 * dRow;
								c -= 2 * dColumn;
								length -= 2;
							}
					}
				}
			}
		}
		out.println(dColumn + " " + dRow);
		for (char[] row : table)
			out.println(row);
	}
}

