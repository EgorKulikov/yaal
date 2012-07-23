package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskH {
	int gcd(int a, int b) {
		while (b > 0) {
			int t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
	
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = new int[k];
		for (int i = 0; i < k; ++i) a[i] = in.readInt();
		Arrays.sort(a);
		int z = 0;
		for (int i = 1; i < k; ++i)
			z = gcd(z, a[i] - a[0]);
		long res = 0;
		if (z == 0) {
			int diff = Math.max(a[0] - 1, n - a[0]);
			++res;
			//double dres = 1.0;
			while (diff > 0) {
				int cleft = (a[0] - 1) / diff;
				int cright = (n - a[0]) / diff;
				int lastLeft = (a[0] - 1) / (cleft + 1) + 1;
				int lastRight = (n - a[0]) / (cright + 1) + 1;
				int lastDiff = Math.max(1, Math.max(lastLeft, lastRight));
				res += (diff - lastDiff + 1) * ((long) (cleft + 1) * (cright + 1) - 1);
				//dres += (double) (diff - lastDiff + 1) * (long) (cleft + 1) * (cright + 1) - 1;
				diff = lastDiff - 1;
			}
			//System.out.println(dres);
		} else {
			for (int i = 1; i * i <= z; ++i) if (z % i == 0) {
				res += solveFor(n, a[0], a[k - 1], i);
				int other = z / i;
				if (other > i)
					res += solveFor(n, a[0], a[k - 1], other);
			}
		}
		out.printLine(res);
	}

	private long solveFor(int n, int first, int last, int diff) {
		int before = (first - 1) / diff + 1;
		int after = (n - last) / diff + 1;
		return before * (long) after;
	}
}
