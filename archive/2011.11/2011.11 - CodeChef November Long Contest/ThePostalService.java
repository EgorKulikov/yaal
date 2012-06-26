package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class ThePostalService {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] distances = IOUtils.readIntArray(in, count);
		int[] directions = IOUtils.readIntArray(in, count);
		List<Event> events = new ArrayList<Event>();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int index = in.readInt();
			int time = in.readInt();
			events.add(new Event(Event.EventType.QUERY, 2 * time, index, i));
		}
		for (int i = 0; i < count; i++) {
			if (directions[i] == 0)
				continue;
			int index = i;
			for (int j = i + 1; j < count; j++) {
				if (directions[j] == 0)
					events.add(new Event(Event.EventType.MEETING, distances[j] - distances[i], index++));
			}
		}
		Collections.sort(events, new Comparator<Event>() {
			public int compare(Event o1, Event o2) {
				int value = IntegerUtils.longCompare(o1.time, o2.time);
				if (value != 0)
					return value;
				if (o1.type != o2.type) {
					if (o1.type == Event.EventType.MEETING)
						return -1;
					else
						return 1;
				}
				return 0;
			}
		});
		long[] position = new long[count];
		for (int i = 0; i < count; i++)
			position[i] = 2 * distances[i];
		int[] meetings = new int[count];
		long lastTime = 0;
		long[] sortedPosition = new long[count];
		long[] time = new long[queryCount];
		int[] meetingCount = new int[queryCount];
		for (Event event : events) {
			if (event.type == Event.EventType.MEETING) {
				meetings[event.index]++;
				meetings[event.index + 1]++;
			} else {
				for (int i = 0; i < count; i++)
					position[i] += (2 * directions[i] - 1) * (event.time - lastTime);
				lastTime = event.time;
				System.arraycopy(position, 0, sortedPosition, 0, count);
				Arrays.sort(sortedPosition);
				time[event.orderIndex] = sortedPosition[event.index] / 2;
				meetingCount[event.orderIndex] = meetings[event.index];
			}
		}
		for (int i = 0; i < queryCount; i++)
			out.printLine(time[i], meetingCount[i]);
	}
}

class Event {
	final EventType type;
	final long time;
	final int index;
	final int orderIndex;

	Event(EventType type, long time, int index) {
		this(type, time, index, -1);
	}

	Event(EventType type, long time, int index, int orderIndex) {
		this.type = type;
		this.time = time;
		this.index = index;
		this.orderIndex = orderIndex;
	}

	enum EventType {
		MEETING, QUERY
	}
}