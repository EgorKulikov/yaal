package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long danger = in.readInt();
		long[] x = new long[count];
		long[] y = new long[count];
		long[] z = new long[count];
		long[] dx = new long[count];
		long[] dy = new long[count];
		long[] dz = new long[count];
		IOUtils.readLongArrays(in, x, y, z, dx, dy, dz);
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < count; i++) {
			long a = dx[i] * dx[i] + dy[i] * dy[i] + dz[i] * dz[i];
			long b = 2 * x[i] * dx[i] + 2 * y[i] * dy[i] + 2 * z[i] * dz[i];
			long c = x[i] * x[i] + y[i] * y[i] + z[i] * z[i] - danger * danger;
			long d = b * b - 4 * a * c;
			if (d < 0)
				continue;
			long dd = Math.round(Math.sqrt(d));
			while (dd * dd > d)
				dd--;
			while ((dd + 1) * (dd + 1) <= d)
				dd++;
			d = dd;
			long from = (-b - d) / (2 * a);
			if (from * (2 * a) < -b - d)
				from++;
			long to = (-b + d) / (2 * a);
			if (to * (2 * a) <= d - b)
				to++;
			if (from <= to) {
				events.add(new Event(from, Event.Type.IN));
				events.add(new Event(to, Event.Type.OUT));
			}
		}
		Collections.sort(events);
		int delta = 0;
		for (Event event : events) {
			if (event.type == Event.Type.IN)
				delta++;
			else
				delta--;
			event.answer = delta;
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int time = in.readInt();
			int index = -Collections.binarySearch(events, new Event(time, Event.Type.QUERY)) - 2;
			if (index == -1)
				out.printLine(0);
			else
				out.printLine(events.get(index).answer);
		}
	}
	static class Event implements Comparable<Event> {
		final long at;
		final Type type;
		int answer;

		Event(long at, Type type) {
			this.at = at;
			this.type = type;
		}

		public int compareTo(Event o) {
			if (at != o.at)
				return IntegerUtils.longCompare(at, o.at);
			return type.ordinal() - o.type.ordinal();
		}

		enum Type {
			IN, OUT, QUERY
		}
	}
}
