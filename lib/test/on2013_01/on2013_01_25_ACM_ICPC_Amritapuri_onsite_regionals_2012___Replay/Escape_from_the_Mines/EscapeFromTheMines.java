package on2013_01.on2013_01_25_ACM_ICPC_Amritapuri_onsite_regionals_2012___Replay.Escape_from_the_Mines;



import net.egork.misc.ArrayUtils;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EscapeFromTheMines {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		int[] x2 = new int[count];
		int[] y2 = new int[count];
		IOUtils.readIntArrays(in, x1, y1, x2, y2);
		ArrayUtils.compress(x1, x2);
		ArrayUtils.compress(y1, y2);
		LongIntervalTree tree = new LongIntervalTree(2 * count) {
			@Override
			protected long joinValue(long left, long right) {
				if (left == -2)
					return right;
				return left;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (delta == -2)
					return was;
				return delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == -2)
					return value;
				return delta;
			}

			@Override
			protected long neutralValue() {
				return -2;
			}

			@Override
			protected long neutralDelta() {
				return -2;
			}

			@Override
			protected long initValue(int index) {
				return -1;
			}
		};
		tree.init();
		List<Event> events = new ArrayList<Event>(2 * count);
		for (int i = 0; i < count; i++) {
			events.add(new Event(x1[i], y1[i], y2[i], i, true));
			events.add(new Event(x2[i], y1[i], y2[i], i, false));
		}
		Collections.sort(events);
		long[] answer = new long[count];
		for (Event event : events) {
			if (event.isOpen) {
				answer[event.type] = tree.query(event.y0, event.y0);
				tree.update(event.y0, event.y1, event.type);
			} else
				tree.update(event.y0, event.y1, answer[event.type]);
		}
		for (long i : answer)
			out.printLine(i);
    }

	static class Event implements Comparable<Event> {
		final int x, y0, y1, type;
		final boolean isOpen;

		Event(int x, int y0, int y1, int type, boolean open) {
			this.x = x;
			this.y0 = y0;
			this.y1 = y1;
			this.type = type;
			isOpen = open;
		}

		public int compareTo(Event o) {
			return x - o.x;
		}
	}
}
