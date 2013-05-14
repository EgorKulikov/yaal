package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class HillWalk {
	int currentPosition;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Segment[] segments = new Segment[count];
		Event[] events = new Event[2 * count];
		for (int i = 0; i < count; i++) {
			int x1 = in.readInt();
			int y1 = in.readInt();
			int x2 = in.readInt();
			int y2 = in.readInt();
			segments[i] = new Segment(x1, y1, x2, y2);
			events[i << 1] = new Event(segments[i], Event.Type.OPEN);
			events[(i << 1) + 1] = new Event(segments[i], Event.Type.CLOSE);
		}
		Arrays.sort(events);
		int answer = 1;
		Segment current = segments[0];
		NavigableSet<Segment> set = new TreeSet<Segment>(new Comparator<Segment>() {
			public int compare(Segment o1, Segment o2) {
				return Double.compare(o1.value(currentPosition), o2.value(currentPosition));
			}
		});
		for (Event event : events) {
			if (event.value() > current.x2) {
				current = set.floor(current);
				if (current == null)
					break;
				answer++;
			}
			currentPosition = event.value();
			if (event.type == Event.Type.OPEN)
				set.add(event.segment);
			else
				set.remove(event.segment);
		}
		out.printLine(answer);
    }

	static class Segment {
		final int x1, y1, x2, y2;

		Segment(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		double value(int at) {
			if (at < x1 || at > x2)
				throw new IllegalArgumentException();
			return ((double)(at - x1) * y2 + (double)(x2 - at) * y1) / (x2 - x1);
		}
	}

	static class Event implements Comparable<Event> {
		final Segment segment;
		final Type type;

		Event(Segment segment, Type type) {
			this.segment = segment;
			this.type = type;
		}

		int value() {
			return type == Type.OPEN ? segment.x1 : segment.x2;
		}

		public int compareTo(Event o) {
			return value() - o.value();
		}

		enum Type {
			OPEN,
			CLOSE
		}
	}
}
