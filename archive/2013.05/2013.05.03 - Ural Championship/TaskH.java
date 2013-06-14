package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		final int[] x = new int[n];
		final int[] y = new int[n];
		IOUtils.readIntArrays(in, x, y);
		long[] dx = new long[(n - 1) * n / 2 + 1];
		long[] dy = new long[(n - 1) * n / 2 + 1];
		final int[] from = new int[dx.length];
		final int[] to = new int[dy.length];
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i == j)
					continue;
				dx[k] = y[i] - y[j];
				dy[k] = x[j] - x[i];
				long g = IntegerUtils.gcd(dx[k], dy[k]);
				if (g == 0) continue;
				dx[k] /= g;
				dy[k] /= g;
				if (dx[k] < 0) {
					dx[k] = -dx[k];
					dy[k] = -dy[k];
				} else if (dx[k] == 0 && dy[k] < 0)
					dy[k] = -dy[k];
				from[k] = i;
				to[k] = j;
				k++;
			}
		}
		{
			long[] tmp = new long[k + 1];
			System.arraycopy(dx, 0, tmp, 0, k);
			dx = tmp;
		}
		{
			long[] tmp = new long[k + 1];
			System.arraycopy(dy, 0, tmp, 0, k);
			dy = tmp;
		}
		final long[] dxfinal = dx;
		final long[] dyfinal = dy;
		int[] order = ArrayUtils.createOrder(dx.length - 1);
		IntComparator comparator = new IntComparator() {
			public int compare(int first, int second) {
				int value = Long.compare(dxfinal[first] * dyfinal[second], dyfinal[first] * dxfinal[second]);
				if (value != 0)
					return value;
				return from[first] - from[second];
			}
		};
		ArrayUtils.sort(order, comparator);
		dx[dx.length - 1] = (long) (2e6 + 1);
		dy[dy.length - 1] = 1;
		int start = 0;
		while (start < dx.length - 1 && comparator.compare(order[start], dx.length - 1) < 0)
			start++;
		int[] realOrder = new int[order.length];
		System.arraycopy(order, 0, realOrder, order.length - start, start);
		System.arraycopy(order, start, realOrder, 0, order.length - start);
		order = realOrder;
		int[] at = new int[n];
		long[] sx = new long[n];
		long[] sy = new long[n];
		int[] ord = ArrayUtils.createOrder(n);
		ArrayUtils.sort(ord, new IntComparator() {
			public int compare(int first, int second) {
				long value = x[first] * dxfinal[dxfinal.length - 1] + y[first];
				long secondValue = x[second] * dxfinal[dxfinal.length - 1] + y[second];
				return IntegerUtils.longCompare(value, secondValue);
			}
		});
		long[] answer = new long[n];
		long xx = 0;
		long yy = 0;
		for (int i = 0; i < n; i++) {
			xx += x[ord[i]];
			yy += y[ord[i]];
			sx[i] = xx;
			sy[i] = yy;
			at[ord[i]] = i;
			answer[i] = xx * xx + yy * yy;
		}
		long[] lastSeen = new long[n];
		Arrays.fill(lastSeen, Long.MIN_VALUE);
		for (int uuu = 0; uuu < 2; uuu++)
		for (int i = 0; i < order.length; i++) {
			int j = order[i];
			if (lastSeen[from[j]] == (dx[j] << 32) + dy[j])
				continue;
			int minAt = Math.min(at[from[j]], at[to[j]]);
			int maxAt = Math.max(at[from[j]], at[to[j]]);
			int tot = 1;
			while (i + 1 < order.length) {
				if (dx[j] != dx[order[i + 1]] || dy[j] != dy[order[i + 1]] || from[j] != from[order[i + 1]])
					break;
				{
					lastSeen[to[j]] = (dx[j] << 32) + dy[j];
					i++;
					j = order[i];
					minAt = Math.min(minAt, at[to[j]]);
					maxAt = Math.max(maxAt, at[to[j]]);
					tot++;
				}
			}
			lastSeen[from[j]] = (dx[j] << 32) + dy[j];
			lastSeen[to[j]] = (dx[j] << 32) + dy[j];
			xx = 0;
			yy = 0;
			while (minAt > 0 && (x[ord[minAt - 1]] == x[ord[minAt]]) && (y[ord[minAt - 1]] == y[ord[minAt]]))
				--minAt;
			while (maxAt < n - 1 && (x[ord[maxAt + 1]] == x[ord[maxAt]]) && (y[ord[maxAt + 1]] == y[ord[maxAt]]))
				++maxAt;
			int length = maxAt - minAt;
			//if (length != tot)
			//	throw new RuntimeException();
			if (minAt > 0) {
				xx = sx[minAt - 1];
				yy = sy[minAt - 1];
			}
			for (int z = 0; z <= length; z++) {
				xx += x[ord[minAt + length - z]];
				yy += y[ord[minAt + length - z]];
				sx[minAt + z] = xx;
				sy[minAt + z] = yy;
				lastSeen[ord[minAt + z]] = (dx[j] << 32) + dy[j];
				answer[minAt + z] = Math.max(answer[minAt + z], xx * xx + yy * yy);
			}
			for (int z = 0, zz = length; z < zz; z++, zz--) {
				int temp = ord[minAt + z];
				ord[minAt + z] = ord[minAt + zz];
				ord[minAt + zz] = temp;
			}
			for (int z = minAt; z <= maxAt; z++)
				at[ord[z]] = z;
		}
		for (long abc : answer)
			out.printLine(Math.sqrt(abc));
	}
}
