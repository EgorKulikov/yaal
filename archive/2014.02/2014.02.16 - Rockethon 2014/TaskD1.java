package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;

public class TaskD1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int verticalCount = in.readInt();
		int horizontalCount = in.readInt();
		int[] xv = new int[verticalCount];
		int[] yv = new int[verticalCount];
		int[] lv = new int[verticalCount];
		IOUtils.readIntArrays(in, xv, yv, lv);
		int[] xh = new int[horizontalCount];
		int[] yh = new int[horizontalCount];
		int[] lh = new int[horizontalCount];
		IOUtils.readIntArrays(in, xh, yh, lh);
		Event[] events = new Event[2 * verticalCount + horizontalCount];
		int left = 0;
		int right = (int) 5e7;
		while (left < right) {
			int middle = (left + right + 1) >> 1;
			int size = 0;
			for (int i = 0; i < verticalCount; i++) {
				if (lv[i] < 2 * middle)
					continue;
				events[size++] = new Event(yv[i] + middle, xv[i], xv[i], i, Type.OPEN);
				events[size] = new Event(yv[i] + lv[i] - middle, xv[i], xv[i], i, Type.CLOSE);
				size++;
			}
			for (int i = 0; i < horizontalCount; i++) {
				if (lh[i] < 2 * middle)
					continue;
				events[size++] = new Event(yh[i], xh[i] + middle, xh[i] + lh[i] - middle, i, Type.QUERY);
			}
			Arrays.sort(events, 0, size, new Comparator<Event>() {
				public int compare(Event o1, Event o2) {
					if (o1.y != o2.y)
						return o1.y - o2.y;
					return o1.type.ordinal() - o2.type.ordinal();
				}
			});
			boolean found = false;
			NavigableSet<Event> set = new TreapSet<Event>();
			for (int i = 0; i < size; i++) {
				Event event = events[i];
				if (event.type == Type.OPEN)
					set.add(event);
				else if (event.type == Type.CLOSE)
					set.remove(new Event(yv[event.index] + middle, xv[event.index], xv[event.index], event.index, Type.OPEN));
				else {
					Event from = new Event(event.y, event.x0, event.x0, -1, Type.QUERY);
					Event to = new Event(event.y, event.x1, event.x1, verticalCount, Type.QUERY);
					if (!set.subSet(from, false, to, false).isEmpty()) {
						found = true;
						break;
					}
				}
			}
			if (found)
				left = middle;
			else
				right = middle - 1;
		}
		out.printLine(left);
    }

	enum Type {
		OPEN, QUERY, CLOSE
	}

	static class Event implements Comparable<Event> {
		int y, x0, x1, index;
		Type type;

		Event(int y, int x0, int x1, int index, Type type) {
			this.y = y;
			this.x0 = x0;
			this.x1 = x1;
			this.index = index;
			this.type = type;
		}

		public int compareTo(Event o) {
			if (x0 != o.x0)
				return x0 - o.x0;
			return index - o.index;
		}
	}
}
