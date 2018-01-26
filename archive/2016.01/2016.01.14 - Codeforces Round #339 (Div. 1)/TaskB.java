package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int A = in.readInt();
		int cf = in.readInt();
		int cm = in.readInt();
		long m = in.readLong();
		int[] a = IOUtils.readIntArray(in, n);
		long total = 0;
		for (int i : a) {
			total += A - i;
		}
		if (total <= m) {
			out.printLine((long)cf * n + (long)cm * A);
			out.printLine(ArrayUtils.createArray(n, A));
			return;
		}
		int[] order = ArrayUtils.order(a);
		int cMin = 0;
		int price = 0;
		int cFull = ArrayUtils.count(a, A);
		long current = (long)cFull * cf;
		for (int i : order) {
			long delta = (long)(a[i] - cMin) * price;
			if (delta <= m) {
				m -= delta;
				current += (long)(a[i] - cMin) * cm;
				cMin = a[i];
				price++;
			} else {
				break;
			}
		}
		cMin += m / price;
		current += m / price * cm;
		m %= price;
		int bestFull = cFull;
		int bestMin = cMin;
		long best = current;
		for (int i = n - 1; i >= 0; i--) {
			int j = order[i];
			if (a[j] == A) {
				continue;
			}
			int delta = A - Math.max(cMin, a[j]);
			m -= delta;
			current += cf;
			price = Math.min(price, i);
			cFull++;
			while (m < 0) {
				if (price == 0) {
					break;
				}
				int floor = a[order[price - 1]];
				long t = (long)(cMin - floor) * price;
				if (t <= -m) {
					m += t;
					current -= (long)(cMin - floor) * cm;
					cMin = floor;
					price--;
				} else {
					long qty = (-m + price - 1) / price;
					m += qty * price;
					current -= qty * cm;
					cMin -= qty;
				}
			}
			if (m < 0) {
				break;
			}
			if (current > best) {
				best = current;
				bestFull = cFull;
				bestMin = cMin;
			}
		}
		out.printLine(best);
		for (int i = n - 1; i >= n - bestFull; i--) {
			m -= A - a[order[i]];
			a[order[i]] = A;
		}
		for (int i = 0; i < n; i++) {
			a[i] = Math.max(a[i], bestMin);
		}
		out.printLine(a);
	}
}
