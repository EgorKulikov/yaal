package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class BestSumSpanClassacmchallengeballoonlargeStylebackgroundcolorNullspan {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] a = IOUtils.readIntArray(in, count);
		int[] b = IOUtils.readIntArray(in, count);
		IntPair[] points = new IntPair[count + 1];
		for (int i = 0; i < count; i++) {
			if (a[i] > 0 || a[i] == 0 && b[i] < 0)
				points[i] = new IntPair(-b[i], a[i]);
			else if (a[i] != 0 || b[i] != 0)
				points[i] = new IntPair(b[i], -a[i]);
			else
				points[i] = new IntPair(0, 1);
		}
		Arrays.sort(points, 0, count, new Comparator<IntPair>() {
			public int compare(IntPair o1, IntPair o2) {
				return Long.compare((long)o1.first * o2.second, (long)o1.second * o2.first);
			}
		});
		points[count] = new IntPair(-points[0].first, -points[0].second);
		long answer = 0;
		for (int i = 1; i <= count; i++)
			answer = Math.max(answer, process(a, b, points[i - 1].first + points[i].first, points[i - 1].second + points[i].second));
		out.printLine(answer);
	}

	private long process(int[] a, int[] b, long am, long bm) {
		long a1 = 0;
		long b1 = 0;
		long a2 = 0;
		long b2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] * am + b[i] * bm > 0) {
				a1 += a[i];
				b1 += b[i];
			} else {
				a2 += a[i];
				b2 += b[i];
			}
		}
		return Math.max(a1 * a1 + b1 * b1, a2 * a2 + b2 * b2);
	}
}
