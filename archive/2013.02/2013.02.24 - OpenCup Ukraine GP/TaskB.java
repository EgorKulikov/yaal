package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	static final int MODULO = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int k = in.readInt();
		int p = in.readInt();
		int t = in.readInt();
		int[] fact = new int[p + k];
		int[] rfact = new int[p + k];
		fact[0] = 1;
		long[] rev = IntegerUtils.generateReverseFactorials(p + k, MODULO);
		for (int i = 1; i < p + k; ++i) {
			fact[i] = (int) (fact[i - 1] * (long) i % MODULO);
		}
		for (int i = 0; i < p + k; ++i) {
			rfact[i] = (int) rev[i];
		}
		for (int it = 0; it < t; ++it) {
			long n = in.readLong();
			int res = 1;
			while (n > 0) {
				int rem = (int) (n % p);
				int c1 = rem + k - 1;
				int c2 = rem;
				res = (int) (res * (long) fact[c1] % MODULO * rfact[c2] % MODULO * rfact[c1 - c2] % MODULO);
				n /= p;
			}
			out.printLine(res);
		}
    }

	private int pow(int a, int k, int p) {
		if (k == 0) return 1;
		if (k % 2 == 0) return pow((int) (a * (long) a % p), k / 2, p);
		return (int) (a * (long) pow(a, k - 1, p) % p);
	}
}
