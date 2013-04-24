package net.egork;

import net.egork.collections.intervaltree.MyOtherIntegerIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
	static class Event {
		long at;
		int what;
		int delta;

		Event(long at, int what, int delta) {
			this.at = at;
			this.what = what;
			this.delta = delta;
		}
	}

	static class Can {
		long x;
		long r;
		int index;
		int left;
		int right;

		Can(long x, long r, int index) {
			this.x = x;
			this.r = r;
			this.index = index;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		final Can[] cans = new Can[n];
		for (int i = 0; i < n; ++i) {
			int x = in.readInt();
			int r = in.readInt();
			cans[i] = new Can(x, r, i);
		}
		Arrays.sort(cans, new Comparator<Can>() {
			public int compare(Can o1, Can o2) {
				return Long.signum(o1.x - o2.x);
			}
		});
		Event[] ev = new Event[2 * n];
		for (int i = 0; i < n; ++i) {
			ev[i * 2] = new Event(cans[i].x - cans[i].r, i, 0);
			ev[i * 2 + 1] = new Event(cans[i].x + cans[i].r + 1, i, 1);
		}
		Arrays.sort(ev, new Comparator<Event>() {
			public int compare(Event o1, Event o2) {
				return Long.signum(o1.at - o2.at);
			}
		});
		int at = 0;
		for (Event e : ev) {
			while (at < cans.length && cans[at].x < e.at) ++at;
			if (e.delta == 0) cans[e.what].left = at; else cans[e.what].right = at - 1;
		}
		int len = 1;
		while (len < n) {
			MyOtherIntegerIntervalTree tree = new MyOtherIntegerIntervalTree(n) {
				@Override
				protected int initValueMin(int index) {
					return cans[index].left;
				}

				@Override
				protected int initValueMax(int index) {
					return cans[index].right;
				}
			};
			tree.init();
			len *= 2;
			for (int i = 0; i < n; ++i) {
				int l = cans[i].left;
				int r = cans[i].right;
				tree.query(l, r);
				cans[i].left = tree.queryMin;
				cans[i].right = tree.queryMax;
			}
		}
		int[] res = new int[n];
		for (Can can : cans) {
			res[can.index] = can.right - can.left + 1;
		}
		out.printLine(res);
    }
}
