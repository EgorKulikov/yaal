import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		boolean[] valid = new boolean[26];
		int[] x = new int[26];
		int[] y = new int[26];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (Character.isLetter(map[i][j])) {
					valid[map[i][j] - 'A'] = true;
					x[map[i][j] - 'A'] = i;
					y[map[i][j] - 'A'] = j;
				}
			}
		}
		int[][] west = new int[rowCount][columnCount];
		int[][] east = new int[rowCount][columnCount];
		int[][] north = new int[rowCount][columnCount];
		int[][] south = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '#')
					west[i][j] = 1 + (j == 0 ? 0 : west[i][j - 1]);
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = columnCount - 1; j >= 0; j--) {
				if (map[i][j] != '#')
					east[i][j] = 1 + (j == columnCount - 1 ? 0 : east[i][j + 1]);
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '#')
					north[i][j] = 1 + (i == 0 ? 0 : north[i - 1][j]);
			}
		}
		for (int i = rowCount - 1; i >= 0; i--) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '#')
					south[i][j] = 1 + (i == rowCount - 1 ? 0 : south[i + 1][j]);
			}
		}
		int commandCount = in.readInt();
		for (int i = 0; i < commandCount; i++) {
			char direction = in.readCharacter();
			int length = in.readInt();
			for (int j = 0; j < 26; j++) {
				if (valid[j]) {
					if (direction == 'N') {
						if (north[x[j]][y[j]] > length)
							x[j] -= length;
						else
							valid[j] = false;
					} else if (direction == 'S') {
						if (south[x[j]][y[j]] > length)
							x[j] += length;
						else
							valid[j] = false;
					} else if (direction == 'W') {
						if (west[x[j]][y[j]] > length)
							y[j] -= length;
						else
							valid[j] = false;
					} else if (direction == 'E') {
						if (east[x[j]][y[j]] > length)
							y[j] += length;
						else
							valid[j] = false;
					}
				}
			}
		}
		StringBuilder result = new StringBuilder(26);
		for (int i = 0; i < 26; i++) {
			if (valid[i])
				result.append((char)(i + 'A'));
		}
		if (result.length() == 0)
			out.println("no solution");
		else
			out.println(result);
	}
}

