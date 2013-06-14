package on2012_01.on2012_0_16.loyal;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Loyal {
	private static final int[][][][] result = new int[16][41][41][21];

	static {
		ArrayUtils.fill(result, Integer.MIN_VALUE);
		ArrayUtils.fill(result[0], 0);
		for (int shipCount = 1; shipCount <= 15; shipCount++) {
			for (int manCount = shipCount; manCount <= 40; manCount++) {
				for (int canBeBad = 0; canBeBad <= manCount; canBeBad++) {
					for (int lastBad = 0; lastBad <= manCount / 2; lastBad++) {
						result[shipCount][manCount][canBeBad][lastBad] = Integer.MIN_VALUE;
						for (int i = 1; i <= manCount; i++) {
							for (int j = 0; i >= j + lastBad && i + j <= manCount && j <= canBeBad; j++) {
								result[shipCount][manCount][canBeBad][lastBad] = Math.max(
									result[shipCount][manCount][canBeBad][lastBad],
									result[shipCount - 1][manCount - i - j][i - j - lastBad][j] + j);
							}
						}
					}
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int shipCount = in.readInt();
		int manCount = in.readInt();
		out.printLine(result[shipCount][manCount][manCount][0]);
	}
}
