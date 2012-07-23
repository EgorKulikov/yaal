package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[][] c = IntegerUtils.generateBinomialCoefficients(31);
		int boyCount = in.readInt();
		int girlCount = in.readInt();
		int totalCount = in.readInt();
		long answer = 0;
		for (int i = 4; i <= boyCount; i++) {
			for (int j = 1; j <= girlCount; j++) {
				if (totalCount != i + j)
					continue;
				answer += c[boyCount][i] * c[girlCount][j];
			}
		}
		out.printLine(answer);
	}
}
