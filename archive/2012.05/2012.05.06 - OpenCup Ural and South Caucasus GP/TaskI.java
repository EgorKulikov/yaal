package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskI {
	static final long MOD = (long) (1e9 + 7);
	static final BigInteger BIG_MOD = BigInteger.valueOf(MOD);
	long[] factorial = new long[5001];
	long[] reverse = new long[5001];

	{
		factorial[0] = reverse[0] = 1;
		for (int i = 1; i <= 5000; i++) {
			factorial[i] = factorial[i - 1] * i % MOD;
			reverse[i] = BigInteger.valueOf(factorial[i]).modInverse(BIG_MOD).longValue();
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int after = in.readInt();
		int[] classes = new int[count];
		for (int i = 0; i < count; i++)
			classes[i] = in.readInt();
		int index = in.readInt() - 1;
		int position = in.readInt() - 1;
		int forward = position;
		int backward = after - position - 1;
		int curClass = classes[index];
		int sameClass = -1;
		int lessClass = 0;
		int moreClass = 0;
		for (int i : classes) {
			if (i < curClass)
				lessClass++;
			else if (i == curClass)
				sameClass++;
			else
				moreClass++;
		}
		long answer = 0;
		int iFrom = Math.max(0, forward - lessClass);
		int iTo = Math.min(sameClass, forward);
		for (int i = iFrom; i <= iTo; i++) {
			int jFrom = Math.max(0, backward - moreClass);
			int jTo = Math.min(sameClass - i, backward);
			for (int j = jFrom; j <= jTo; j++)
				answer += c(sameClass, i + j) * c(lessClass, forward - i) % MOD * c(moreClass, backward - j) % MOD;
		}
		answer %= MOD;
		out.printLine(answer);
	}

	long c(int n, int k) {
		if (n < k || k < 0 || n < 0)
			throw new RuntimeException();
		return factorial[n] * reverse[k] % MOD * reverse[n - k] % MOD;
	}
}
