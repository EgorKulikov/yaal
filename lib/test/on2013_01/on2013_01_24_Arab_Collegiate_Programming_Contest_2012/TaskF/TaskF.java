package on2013_01.on2013_01_24_Arab_Collegiate_Programming_Contest_2012.TaskF;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	int[][] state = new int[3][4];
	long[][][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int distance = in.readInt();
		if (distance > 55) {
			out.printLine("BAD MEMORY");
			return;
		}
		int badCount = in.readInt();
		for (int i = 0; i < badCount; i++) {
			int x = in.readInt() - 1;
			int y = in.readInt() - 1;
			state[x][y] = 2;
		}
		long answer = 0;
		result = new long[3][4][distance + 1][1 << 12];
		ArrayUtils.fill(result, -1);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] == 0) {
					state[i][j] = 1;
					answer += go(i, j, distance, 1 << (4 * i + j));
					state[i][j] = 0;
				}
			}
		}
		if (answer == 0)
			out.printLine("BAD MEMORY");
		else
			out.printLine(answer);
    }

	private long go(int x, int y, int distance, int mask) {
		if (result[x][y][distance][mask] != -1)
			return result[x][y][distance][mask];
		if (distance == 0)
			return result[x][y][distance][mask] = 1;
		result[x][y][distance][mask] = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] != 0 || Math.abs(i - x) + Math.abs(j - y) > distance)
					continue;
				boolean good = true;
				if (i == x) {
					for (int k = Math.min(j, y) + 1; k < Math.max(j, y); k++) {
						if (state[i][k] != 1)
							good = false;
					}
				} else if (j == y) {
					for (int k = Math.min(i, x) + 1; k < Math.max(i, x); k++) {
						if (state[k][j] != 1)
							good = false;
					}
				} else if (Math.abs(i - x) == 2 && Math.abs(j - y) == 2 && state[(i + x) >> 1][(j + y) >> 1] != 1)
					good = false;
				if (good) {
					state[i][j] = 1;
					result[x][y][distance][mask] += go(i, j, distance - Math.abs(i - x) - Math.abs(j - y), mask + (1 << (4 * i + j)));
					state[i][j] = 0;
				}
			}
		}
		return result[x][y][distance][mask];
	}
}
