package net.egork;

import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int time = in.readInt();
		int distance = in.readInt();
		long[][] data = new long[distance][distance];
		for (int i = 0; i < distance; i++) {
			long ways = 1;
			for (int j = distance - i - 1; j < distance; j++) {
				data[i][j] = ways;
				ways <<= 1;
				if (ways >= MOD)
					ways -= MOD;
			}
		}
		long[] matrix = Matrix.convert(data);
		long[] answer = Matrix.sumPowers(matrix, time, MOD, distance);
		long result = 0;
		for (int i = distance * (distance - 1); i < answer.length; i++)
			result += answer[i];
		result %= MOD;
		result *= result;
		result %= MOD;
		out.printLine(result);
    }
}
