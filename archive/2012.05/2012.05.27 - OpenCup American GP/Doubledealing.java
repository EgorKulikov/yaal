package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Doubledealing {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (n == 0 && k == 0) throw new UnknownError();
		int[] p = new int[n];
		int[] x = new int[k];
		for (int i = 0; i < n; i++) {
			x[i % k]++;
		}
		for (int i = 1; i < k; i++) {
			x[i] += x[i - 1];
		}

		for (int i = 0; i < n; i++) {
			int j = i % k;
			p[--x[j]] = i;
		}

		long res = 1;
		boolean[] z = new boolean[n];
		for (int i = 0; i < n; i++) if (!z[i]) {
			int j = i;
			long l = 0;
			while (!z[j]) {
				z[j] = true;
				l++;
				j = p[j];
			}
			long gcd = gcd(res, l);
			res = res / gcd * l;
		}
		out.printLine(res);
	}

	long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
