package net.egork;

import net.egork.numbers.IntegerUtils;

public class LotsOfLines {
    public long countDivisions(int A, int B) {
		long answer = 1L * A * (A - 1) * B * B / 2 + 1 + A * B;
		answer -= B * (A - 2L) * (A - 1) / 2;
		for (int da = 1; da * 2 < A; da++) {
			for (int b1 = 1; b1 * 2 < B; b1++) {
				int g = IntegerUtils.gcd(da, b1);
				if (g != 1)
					continue;
				for (int k = 2; da * k < A && b1 * k < B; k++) {
					int dda = A - da * k;
					int ddb = B - b1 * k;
					answer -= 2L * dda * ddb;
				}
			}
		}
		return answer;
    }
}
