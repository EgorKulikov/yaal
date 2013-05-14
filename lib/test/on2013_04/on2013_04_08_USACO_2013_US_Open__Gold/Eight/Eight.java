package on2013_04.on2013_04_08_USACO_2013_US_Open__Gold.Eight;


import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Eight {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] map = IOUtils.readTable(in, size, size);
		int[][][] up = new int[size][size][];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				up[i][j] = new int[j];
		}
		boolean[][] valid = new boolean[size][size];
		ArrayUtils.fill(up, -1);
		for (int i = 0; i < size; i++) {
			valid[i][i] = map[0][i] == '.';
			for (int j = i - 1; j >= 0; j--) {
				if (valid[j][i] = (valid[j][i - 1] && valid[j + 1][i]))
					up[0][i][j] = 0;
			}
		}
		for (int i = 1; i < size; i++) {
			for (int j = 0; j < size; j++) {
				valid[j][j] = map[i][j] == '.';
				for (int k = j - 1; k >= 0; k--) {
					valid[k][j] = (valid[k][j - 1] && valid[k + 1][j]);
					if (up[i - 1][j][k] != -1 && map[i][k] == '.' && map[i][j] == '.')
						up[i][j][k] = up[i - 1][j][k] + j - k - 1;
					else if (valid[k][j])
						up[i][j][k] = 0;
				}
			}
		}
		long[][] down = new long[size][size];
		ArrayUtils.fill(down, -1);
		int[][] maxUp = new int[size][size];
		long answer = 0;
		for (int i = size - 1; i > 0; i--) {
			for (int j = 0; j < size; j++) {
				valid[j][j] = map[i][j] == '.';
				for (int k = j - 1; k >= 0; k--) {
					valid[k][j] = (valid[k][j - 1] && valid[k + 1][j]);
					maxUp[k][j] = Math.max(Math.max(maxUp[k][j - 1], maxUp[k + 1][j]), up[i - 1][j][k]);
					if (valid[k][j] && down[k][j] != -1)
						answer = Math.max(answer, down[k][j] * maxUp[k][j]);
					if (down[k][j] != -1 && map[i][k] == '.' && map[i][j] == '.')
						down[k][j] += j - k - 1;
					else if (valid[k][j])
						down[k][j] = 0;
					else
						down[k][j] = -1;
				}
			}
		}
		if (answer == 0)
			answer = -1;
		out.printLine(answer);
    }
}
