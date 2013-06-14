package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int blockCount = in.readInt();
		int[] from = new int[blockCount];
		int[] to = new int[blockCount];
		int[] appear = new int[blockCount];
		IOUtils.readIntArrays(in, from, to, appear);
		int[] start = IOUtils.readIntArray(in, count);
		List<Event> events = new ArrayList<Event>();
//		for (int i = 0; i < blockCount; i++) {
//			events[2 * i] = new Event(Event.Type.ADD, i, appear[i] - to[i]);
//			events[2 * i + 1] = new Event(Event.Type.FIX, i, appear[i] - from[i]);
//		}
		for (int i = 0; i < count; i++)
			events.add(new Event(Event.Type.TOURIST, i, start[i]));
		int[] order = ArrayUtils.order(appear);
		NavigableSet<Pair<Integer, Integer>> occupied = new TreeSet<Pair<Integer, Integer>>();
		for (int i : order) {
			Pair<Integer, Integer> key = Pair.makePair(from[i], to[i]);
			Pair<Integer, Integer> last = occupied.floor(key);
			int left = from[i];
			int current = from[i];
			if (last != null && last.second >= from[i]) {
				left = last.first;
				current = last.second;
				occupied.remove(last);
			}
			NavigableSet<Pair<Integer, Integer>> subSet = occupied.subSet(key, false, Pair.makePair(to[i], Integer.MAX_VALUE), false);
			Iterator<Pair<Integer, Integer>> iterator = subSet.iterator();
			while (iterator.hasNext()) {
				Pair<Integer, Integer> next = iterator.next();
				if (next.first > current) {
					events.add(new Event(Event.Type.ADD, i, appear[i] - next.first));
					events.add(new Event(Event.Type.FIX, i, appear[i] - current));
				}
				current = Math.max(current, next.second);
				iterator.remove();
			}
			if (current < to[i]) {
				events.add(new Event(Event.Type.ADD, i, appear[i] - to[i]));
				events.add(new Event(Event.Type.FIX, i, appear[i] - current));
				current = to[i];
			}
			occupied.add(Pair.makePair(left, current));
		}
		Collections.sort(events);
		long[] answer = new long[count];
		long current = 0;
		long delta = 0;
		int last = Integer.MIN_VALUE;
		for (Event event : events) {
			current += delta * (event.value - last);
			last = event.value;
			if (event.type == Event.Type.ADD)
				delta++;
			else if (event.type == Event.Type.FIX)
				delta--;
			else
				answer[event.index] = current;
		}
		for (long i : answer)
			out.printLine(i);
	}

	static class Event implements Comparable<Event> {
		public int compareTo(Event o) {
			if (value == o.value)
				return 0;
			else if (value < o.value)
				return -1;
			else
				return 1;
		}

		enum Type {
			ADD, FIX, TOURIST
		}

		final Type type;
		final int index;
		final int value;

		Event(Type type, int index, int value) {
			this.type = type;
			this.index = index;
			this.value = value;
		}


	}
}
