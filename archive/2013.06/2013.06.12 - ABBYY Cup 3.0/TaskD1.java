package net.egork;

import net.egork.collections.intcollection.IntSet;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD1 {
	long[][] answer;
	long[][] c;
	long[] f;

	static final long MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int a = 0;
		int b = 0;
		for (int i = 0; i < count; i++) {
			if (in.readInt() == 1)
				a++;
			else
				b++;
		}
		answer = new long[a + 1][b + 1];
		ArrayUtils.fill(answer, -1);
		answer[0][0] = 1;
		for (int i = 1; i <= b; i++) {
			answer[0][i] = answer[0][i - 1] * i % MOD;
		}
		c = IntegerUtils.generateBinomialCoefficients(a + b + 1, MOD);
		f = IntegerUtils.generateFactorial(a + b + 1, MOD);
		out.printLine(go(a, b));
    }

	private long go(int a, int b) {
		if (answer[a][b] != -1)
			return answer[a][b];
		answer[a][b] = 0;
		for (int i = 1; i <= a && i <= 2; i++) {
			for (int j = 0; j <= b; j++) {
				answer[a][b] += c[a - 1][i - 1] * c[b][j] % MOD * f[i + j - 1] % MOD * go(a - i, b - j) % MOD;
			}
		}
		return answer[a][b] %= MOD;
	}

	private void go(int total, int[] order, int[] remaining, IntSet was) {
		if (total == 0) {
			int key = 0;
			for (int i : order) {
				key <<= 3;
				key += i;
			}
			was.add(key);
			return;
		}
		for (int i = 0; i < order.length; i++) {
			if (remaining[i] == 0)
				continue;
			for (int j = i + 1; j < order.length; j++) {
				if (remaining[j] != 0) {
					remaining[i]--;
					remaining[j]--;
					int t = order[i];
					order[i] = order[j];
					order[j] = t;
					go(total - 1, order, remaining, was);
					remaining[i]++;
					remaining[j]++;
					t = order[i];
					order[i] = order[j];
					order[j] = t;
				}
			}
		}
	}
}
