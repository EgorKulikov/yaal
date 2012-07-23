import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		System.err.println(testNumber);
		int planetCount = in.readInt();
		boolean[][] connected = new boolean[planetCount + 1][planetCount + 1];
		int wormholeCount = in.readInt();
		for (int i = 0; i < planetCount; i++)
			connected[i][i] = true;
		for (int i = 0; i < wormholeCount; i++) {
			String[] wormhole = in.readString().split(",");
			int from = Integer.parseInt(wormhole[0]);
			int to = Integer.parseInt(wormhole[1]);
			connected[from][to] = connected[to][from] = true;
		}
		int[] level = new int[planetCount];
		Arrays.fill(level, -1);
		int[] queue = new int[planetCount];
		int size = 1;
		level[0] = 0;
		for (int i = 0; i < size; i++) {
			int vertex = queue[i];
			for (int j = 0; j < planetCount; j++) {
				if (connected[vertex][j] && level[j] == -1) {
					level[j] = level[vertex] + 1;
					queue[size++] = j;
				}
			}
		}
		int[][] bitConnected = new int[planetCount + 1][planetCount / 30 + 1];
		for (int i = 0; i < planetCount; i++) {
			for (int j = 0; j < planetCount; j++) {
				if (connected[i][j])
					bitConnected[i][j / 30] += 1 << (j % 30);
			}
		}
		int[][][] shift = new int[planetCount + 1][planetCount + 1][planetCount];
		ArrayUtils.fill(shift, -1);
		for (int i = 0; i <= planetCount; i++) {
			for (int j = 0; j <= planetCount; j++) {
				if (i != planetCount && (j == planetCount || level[j] != level[i] + 1 || !connected[i][j]) || i == 1 || j == 1)
					continue;
				for (int k = 0; k < planetCount; k++) {
					if (j != planetCount && (level[k] != level[j] + 1 || !connected[k][j]))
						continue;
					if (i == planetCount && j == planetCount && k != 0)
						continue;
					shift[i][j][k] = 0;
					for (int l = 0; l < bitConnected[k].length; l++)
						shift[i][j][k] += Integer.bitCount(bitConnected[k][l] & (~bitConnected[i][l]) & (~bitConnected[j][l]));
				}
			}
		}
		int[][] result = new int[planetCount + 1][planetCount + 1];
		ArrayUtils.fill(result, Integer.MIN_VALUE);
		int answer = go(result, shift, planetCount, planetCount);
		out.println("Case #" + testNumber + ": " + (level[1] - 1) + " " + (answer - level[1]));
	}

	private int go(int[][] result, int[][][] shift, int first, int second) {
		if (result[first][second] != Integer.MIN_VALUE)
			return result[first][second];
		result[first][second] = Integer.MIN_VALUE + 1;
		if (shift[first][second][1] != -1)
			return result[first][second] = 0;
		for (int i = 0; i < result.length - 1; i++) {
			if (i != 1 && shift[first][second][i] != -1)
				result[first][second] = Math.max(result[first][second], go(result, shift, second, i) + shift[first][second][i]);
		}
		return result[first][second];
	}
}

