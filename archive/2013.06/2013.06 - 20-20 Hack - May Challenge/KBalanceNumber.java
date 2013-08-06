package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KBalanceNumber {
	static final long MOD = 1000000007;

	int balance;
	long answer;
	long[][] qty = new long[19][18 * 9 + 1];
	long[][] sum = new long[19][18 * 9 + 1];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		qty[0][0] = 1;
		for (int i = 1; i <= 18; i++) {
			for (int j = 0; j <= (i - 1) * 9; j++) {
				for (int k = 0; k <= 9; k++) {
					qty[i][j + k] += qty[i - 1][j];
					sum[i][j + k] += sum[i - 1][j] * 10 + qty[i - 1][j] * k;
					qty[i][j + k] %= MOD;
					sum[i][j + k] %= MOD;
				}
			}
		}
		long from = in.readLong();
		long to = in.readLong();
		balance = in.readInt();
		answer = 0;
		new NumberIterator() {
			@Override
			protected void process(long prefix, int remainingDigits) {
				int prefixLength = 0;
				long prefixCopy = prefix;
				int sumPrefix = 0;
				while (prefixCopy != 0) {
					prefixLength++;
					sumPrefix += prefixCopy % 10;
					prefixCopy /= 10;
				}
				if (prefixLength + remainingDigits <= balance) {
					prefix *= IntegerUtils.power(10, remainingDigits);
					prefix %= MOD;
					for (int i = 0; i <= remainingDigits * 9; i++) {
						answer += prefix * qty[remainingDigits][i];
						answer += sum[remainingDigits][i];
						answer %= MOD;
					}
					return;
				}
				int middle;
				int same;
				if (prefixLength + remainingDigits >= 2 * balance) {
					same = balance;
					middle = prefixLength + remainingDigits - 2 * balance;
				} else {
					same = prefixLength + remainingDigits - balance;
					middle = balance - same;
				}
				if (same + middle <= prefixLength) {
					prefixCopy = prefix;
					int rightSum = 0;
					for (int i = 0; i < prefixLength - same - middle; i++) {
						rightSum += prefixCopy % 10;
						prefixCopy /= 10;
					}
					for (int i = 0; i < middle; i++)
						prefixCopy /= 10;
					int leftSum = 0;
					for (int i = 0; i < same; i++) {
						leftSum += prefixCopy % 10;
						prefixCopy /= 10;
					}
					int delta = leftSum - rightSum;
					if (delta < 0 || delta >= qty[remainingDigits].length)
						return;
					prefix *= IntegerUtils.power(10, remainingDigits);
					prefix %= MOD;
					answer += prefix * qty[remainingDigits][delta] + sum[remainingDigits][delta];
					answer %= MOD;
				} else if (same <= prefixLength) {
					prefixCopy = prefix;
					int leftSum = 0;
					for (int i = 0; i < prefixLength - same; i++)
						prefixCopy /= 10;
					for (int i = 0; i < same; i++) {
						leftSum += prefixCopy % 10;
						prefixCopy /= 10;
					}
					long multiplier = IntegerUtils.power(10, same, MOD);
					int middleRemaining = remainingDigits - same;
					prefix *= IntegerUtils.power(10, remainingDigits);
					prefix %= MOD;
					for (int i = 0; i <= 9 * middleRemaining; i++) {
						answer += prefix * qty[middleRemaining][i] % MOD * qty[same][leftSum];
						answer += sum[middleRemaining][i] * multiplier % MOD * qty[same][leftSum];
						answer += sum[same][leftSum] * qty[middleRemaining][i];
						answer %= MOD;
					}
				} else {
					prefix *= IntegerUtils.power(10, remainingDigits);
					prefix %= MOD;
					long multiplier = IntegerUtils.power(10, same, MOD);
					long anotherMultiplier = IntegerUtils.power(10, same + middle, MOD);
					for (int i = sumPrefix; i <= 9 * same; i++) {
						for (int j = 0; j <= 9 * middle; j++) {
							answer += prefix * qty[middle][j] % MOD * qty[same - prefixLength][i - sumPrefix] % MOD * qty[same][i];
							answer += sum[same - prefixLength][i - sumPrefix] * anotherMultiplier % MOD * qty[middle][j] % MOD * qty[same][i];
							answer += sum[middle][j] * multiplier % MOD * qty[same - prefixLength][i - sumPrefix] % MOD * qty[same][i];
							answer += sum[same][i] * qty[same - prefixLength][i - sumPrefix] % MOD * qty[middle][j];
							answer %= MOD;
						}
					}
				}
			}
		}.run(from, to);
		out.printLine(answer);
    }
}
