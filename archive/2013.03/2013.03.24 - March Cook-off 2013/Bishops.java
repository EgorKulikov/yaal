package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.map.EHashMap;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Bishops {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		final int size = in.readInt();
		int bishopCount = in.readInt();
		int otherCount = in.readInt();
		final int[] row = new int[bishopCount + otherCount];
		final int[] column = new int[bishopCount + otherCount];
		IOUtils.readIntArrays(in, row, column);
		final int[] sum = new int[bishopCount + otherCount];
		final int[] diff = new int[bishopCount + otherCount];
		for (int i = 0; i < bishopCount + otherCount; i++) {
			sum[i] = row[i] + column[i];
			diff[i] = row[i] - column[i];
		}
		int[] order = ArrayUtils.createOrder(bishopCount + otherCount);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return sum[first] - sum[second];
			}
		});
		Map<Integer, Integer> current = new EHashMap<Integer, Integer>();
		List<Event> events = new ArrayList<Event>();
		long answer = bishopCount;
		for (int i : order) {
			int position = diff[i];
			if (current.containsKey(position)) {
				int last = current.get(position);
				if ((last < bishopCount || i < bishopCount) && sum[last] + 2 != sum[i]) {
					events.add(new Event(Event.Type.VERTICAL_START, sum[last] + 2, position, position));
					events.add(new Event(Event.Type.VERTICAL_END, sum[i] - 2, position, position));
					answer += (sum[i] - sum[last] - 2) >> 1;
				}
			} else if (i < bishopCount) {
				int start = sum[i] - Math.min(row[i], column[i]) * 2 + 2;
				if (start != sum[i]) {
					events.add(new Event(Event.Type.VERTICAL_START, start, position, position));
					events.add(new Event(Event.Type.VERTICAL_END, sum[i] - 2, position, position));
					answer += (sum[i] - start) >> 1;
				}
			}
			current.put(position, i);
		}
		for (Map.Entry<Integer, Integer> entry : current.entrySet()) {
			int i = entry.getValue();
			if (i >= bishopCount)
				continue;
			int position = entry.getKey();
			int end = sum[i] + 2 * (size - Math.max(row[i], column[i]));
			if (end != sum[i]) {
				events.add(new Event(Event.Type.VERTICAL_START, sum[i] + 2, position, position));
				events.add(new Event(Event.Type.VERTICAL_END, end, position, position));
				answer += (end - sum[i]) >> 1;
			}
		}
		current.clear();
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return diff[first] - diff[second];
			}
		});
		for (int i : order) {
			int position = sum[i];
			if (current.containsKey(position)) {
				int last = current.get(position);
				if ((last < bishopCount || i < bishopCount) && diff[last] + 2 != diff[i]) {
					events.add(new Event(Event.Type.HORIZONTAL, position, diff[last] + 2, diff[i] - 2));
					answer += (diff[i] - diff[last] - 2) >> 1;
				}
			} else if (i < bishopCount) {
				int start = diff[i] - Math.min(row[i] - 1, size - column[i]) * 2;
				if (start != diff[i]) {
					events.add(new Event(Event.Type.HORIZONTAL, position, start, diff[i] - 2));
					answer += (diff[i] - start) >> 1;
				}
			}
			current.put(position, i);
		}
		for (Map.Entry<Integer, Integer> entry : current.entrySet()) {
			int i = entry.getValue();
			if (i >= bishopCount)
				continue;
			int position = entry.getKey();
			int end = diff[i] + 2 * Math.min(size - row[i], column[i] - 1);
			if (end != diff[i]) {
				events.add(new Event(Event.Type.HORIZONTAL, position, diff[i] + 2, end));
				answer += (end - diff[i]) >> 1;
			}
		}
		Collections.sort(events);
		NavigableSet<Integer> odd = new TreapSet<Integer>();
		NavigableSet<Integer> even = new TreapSet<Integer>();
		for (Event event : events) {
			NavigableSet<Integer> curSet = (event.at & 1) == 1 ? odd : even;
			if (event.type == Event.Type.VERTICAL_START)
				curSet.add(event.from);
			else if (event.type == Event.Type.VERTICAL_END)
				curSet.remove(event.from);
			else
				answer -= curSet.subSet(event.from, true, event.to, true).size();
		}
		out.printLine(answer);
	}

	static class Event implements Comparable<Event> {
		public int compareTo(Event o) {
			if (at != o.at)
				return at - o.at;
			return type.ordinal() - o.type.ordinal();
		}

		enum Type {
			VERTICAL_START, HORIZONTAL, VERTICAL_END
		}

		final Type type;
		final int at;
		final int from;
		final int to;

		Event(Type type, int at, int from, int to) {
			this.type = type;
			this.at = at;
			this.from = from;
			this.to = to;
		}
	}
}
