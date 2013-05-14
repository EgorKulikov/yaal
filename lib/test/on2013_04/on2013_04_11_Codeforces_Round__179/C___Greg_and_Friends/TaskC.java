package on2013_04.on2013_04_11_Codeforces_Round__179.C___Greg_and_Friends;


import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int[][] moves;
	long[][] ways;
	int size;
	long[][] c;
	static final long MOD = (long) (1e9 + 7);
	int maxHeavy, maxLight;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		c = IntegerUtils.generateBinomialCoefficients(count + 1, MOD);
		size = in.readInt() / 50;
		int[] types = IOUtils.readIntArray(in, count);
		int light = 0;
		int heavy = 0;
		for (int i : types) {
			if (i == 100)
				heavy++;
			else
				light++;
		}
		maxHeavy = heavy;
		maxLight = light;
		if (count == 1) {
			if (types[0] <= 50 * size) {
				out.printLine(1);
				out.printLine(1);
			} else {
				out.printLine(-1);
				out.printLine(0);
			}
			return;
		}
		moves = new int[heavy + 1][light + 1];
		ways = new long[heavy + 1][light + 1];
		ArrayUtils.fill(moves, -1);
		ArrayUtils.fill(ways, -1);
		int moves = calculateMoves(heavy, light);
		if (moves == Integer.MAX_VALUE / 2) {
			out.printLine(-1);
			out.printLine(0);
			return;
		}
		out.printLine(moves);
		out.printLine(calculateWays(heavy, light));
    }

	private long calculateWays(int heavy, int light) {
		if (ways[heavy][light] != -1)
			return ways[heavy][light];
		if (2 * heavy + light <= size)
			return ways[heavy][light] = 1;
		ways[heavy][light] = 0;
		for (int i = 0; i <= heavy && 2 * i <= size; i++) {
			for (int j = 0; j <= light && 2 * i + j <= size; j++) {
				if (i + j == 0)
					continue;
				for (int k = 0; k <= maxHeavy - heavy + i && 2 * k <= size; k++) {
					for (int l = 0; l <= maxLight - light + j && 2 * k + l <= size; l++) {
						if (k + l == 0)
							continue;
						if (calculateMoves(heavy - i + k, light - j + l) == moves[heavy][light] - 2)
						{
							ways[heavy][light] += calculateWays(heavy - i + k, light - j + l) * c[heavy][i] % MOD *
								c[light][j] % MOD * c[maxHeavy - heavy + i][k] % MOD * c[maxLight - light + j][l] % MOD;
						}
					}
				}
			}
		}
		return ways[heavy][light] %= MOD;
	}

	private int calculateMoves(int heavy, int light) {
		if (heavy > maxHeavy || light > maxLight)
			return Integer.MAX_VALUE / 2;
		if (moves[heavy][light] != -1)
			return moves[heavy][light];
		if (2 * heavy + light <= size)
			return moves[heavy][light] = 1;
		moves[heavy][light] = Integer.MAX_VALUE / 2;
		for (int i = 0; i <= heavy && 2 * i <= size; i++) {
			for (int j = 0; j <= light && 2 * i + j <= size; j++) {
				if (i == 0 && j <= 1)
					continue;
				if (i != 1 || j != 0) {
					int result = calculateMoves(heavy - i + 1, light - j);
					moves[heavy][light] = Math.min(moves[heavy][light], result + 2);
				}
				int result = calculateMoves(heavy - i, light - j + 1);
				moves[heavy][light] = Math.min(moves[heavy][light], result + 2);
			}
		}
		return moves[heavy][light];
	}
}
