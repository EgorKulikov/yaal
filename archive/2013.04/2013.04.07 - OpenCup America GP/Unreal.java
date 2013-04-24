package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.intervaltree.MyLongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Unreal {
	static class Event {
		int y;
		int x1;
		int x2;
		int delta;

		Event(int y, int x1, int x2, int delta) {
			this.y = y;
			this.x1 = x1;
			this.x2 = x2;
			this.delta = delta;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		List<Event> events = new ArrayList<Event>();
		int[] allX = new int[2 * n];
		for (int i = 0; i < n; ++i) {
			int x1 = (int) Math.round(in.readDouble() * 100);
			int y1 = (int) Math.round(in.readDouble() * 100);
			int x2 = (int) Math.round(in.readDouble() * 100);
			int y2 = (int) Math.round(in.readDouble() * 100);
			allX[i * 2] = x1;
			allX[i * 2 + 1] = x2;
			events.add(new Event(y1, x1, x2, 1));
			events.add(new Event(y2, x1, x2, -1));
		}
		Arrays.sort(allX);
		allX = ArrayUtils.unique(allX);
		final long[] startingCosts = new long[allX.length - 1];
		for (int i = 0; i + 1 < allX.length; ++i) {
			startingCosts[i] = allX[i + 1] - allX[i];
		}
		long totalCost = allX[allX.length - 1] - allX[0];
		for (Event e : events) {
			e.x1 = Arrays.binarySearch(allX, e.x1);
			e.x2 = Arrays.binarySearch(allX, e.x2);
		}
		Collections.sort(events, new Comparator<Event>() {
			public int compare(Event o1, Event o2) {
				return o1.y - o2.y;
			}
		});
		MyLongIntervalTree tree = new MyLongIntervalTree(allX.length - 1) {
			@Override
			protected long initValue(int index) {
				return startingCosts[index];
			}

			@Override
			protected long joinValue(long left, long right) {
				return left + right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return was + delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == 0) return value; else return 0;
			}

			@Override
			protected long neutralValue() {
				return 0;  //To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			protected long neutralDelta() {
				return 0;  //To change body of implemented methods use File | Settings | File Templates.
			}
		};
		tree.init();
		long res = 0;
		long py = events.get(0).y;
		for (Event e : events) {
			long cy = e.y;
			if (cy > py) {
				res += (cy - py) * (totalCost - tree.res[0]);
			}
			py = cy;
			tree.update(e.x1, e.x2 - 1, e.delta);
		}
		out.printLine(String.format("%.2f", res / 10000.0));
    }
}
