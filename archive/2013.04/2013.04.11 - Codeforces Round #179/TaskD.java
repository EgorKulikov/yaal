package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long[][] strict = new long[rowCount][columnCount];
		long[][] nonStrict = new long[rowCount][columnCount];
		Arrays.fill(strict[0], 1, columnCount, 1);
		Arrays.fill(nonStrict[0], 1, columnCount, 1);
		for (int i = 1; i < rowCount; i++) {
			long sum = 0;
			long result = 0;
			for (int j = 0; j < columnCount; j++) {
				result += sum;
				if (result >= MOD)
					result -= MOD;
				strict[i][j] = result;
				result += nonStrict[i - 1][j];
				sum += nonStrict[i - 1][j];
				if (result >= MOD)
					result -= MOD;
				if (sum >= MOD)
					sum -= MOD;
				nonStrict[i][j] = result;
			}
		}
		long answer = 0;
		for (int i = 0; i < columnCount; i++) {
			long sum = 0;
			long result = 0;
			long current = 0;
			for (int j = 0; j < rowCount; j++) {
				sum += nonStrict[j][i];
				if (sum >= MOD)
					sum -= MOD;
				result += sum;
				if (result >= MOD)
					result -= MOD;
				current += strict[rowCount - j - 1][i] * result % MOD;
			}
			current %= MOD;
			answer += current * (columnCount - i);
		}
		answer %= MOD;
		out.printLine(answer);
    }
}
