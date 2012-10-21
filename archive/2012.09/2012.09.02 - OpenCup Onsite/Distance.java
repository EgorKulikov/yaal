package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Distance {
	int[] closest;
	int[] closest2;
	int[] dist;
	int[] dist2;
	int[] firstFactor;
	int[] factors;
	int numFactors;
	int UNIT;
	int INF;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int mx = 1;
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
			mx = Math.max(mx, a[i]);
		}
		firstFactor = new int[mx + 1];
		for (int i = 2; i <= mx; ++i) {
			if (firstFactor[i] == 0) {
				firstFactor[i] = i;
				for (int j = 2 * i; j <= mx; j += i)
					if (firstFactor[j] == 0) firstFactor[j] = i;
			}
		}
		closest = new int[mx + 1];
		closest2 = new int[mx + 1];
		dist = new int[mx + 1];
		dist2 = new int[mx + 1];
		INF = (int) 1e9;
		UNIT = 2 * n + 3;
		Arrays.fill(dist, INF);
		Arrays.fill(dist2, INF);
		Arrays.fill(closest, -1);
		Arrays.fill(closest2, -1);
		for (int i = 0; i < n; ++i) {
			reach(a[i], i, i + 1);
		}
		for (int i = mx; i >= 1; --i) {
			int tmp = i;
			while (tmp > 1) {
				int z = firstFactor[tmp];
				int y = i / z;
				if (closest[i] >= 0) {
					reach(y, closest[i], dist[i] + UNIT);
				}
				if (closest2[i] >= 0) {
					reach(y, closest2[i], dist2[i] + UNIT);
				}
				while (tmp % z == 0) tmp /= z;
			}
		}
		factors = new int[100];
		for (int i = 0; i < n; ++i) {
			numFactors = 0;
			int tmp = a[i];
			while (tmp > 1) {
				int z = firstFactor[tmp];
				factors[numFactors++] = z;
				while (tmp % z == 0) tmp /= z;
			}
			int bd = tryAllDivisors(0, a[i], i);
			out.printLine(bd % UNIT);
		}
	}

	private int tryAllDivisors(int at, int rem, int badClosest) {
		if (at >= numFactors) {
			if (closest[rem] == badClosest)
				return dist2[rem];
			else
				return dist[rem];
		}
		int res = tryAllDivisors(at + 1, rem, badClosest);
		if (rem % factors[at] == 0) {
			res = Math.min(res, UNIT + tryAllDivisors(at, rem / factors[at], badClosest));
		}
		return res;
	}

	private void reach(int y, int nc, int nd) {
		if (nd < dist[y]) {
			if (closest[y] == nc) {
				dist[y] = nd;
			} else {
				closest2[y] = closest[y];
				dist2[y] = dist[y];
				closest[y] = nc;
				dist[y] = nd;
			}
		} else if (nd < dist2[y] && nc != closest[y]) {
			dist2[y] = nd;
			closest2[y] = nc;
		}
	}
}
