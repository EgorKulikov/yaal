import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskI implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int l = in.readInt();
		int n = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt();
		}
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < n; i++) {
			events.add(new Event(a[i], 1));
			events.add(new Event(a[i] * 2, 2));
			events.add(new Event(a[i] * 2 - l, 3));
		}
		Collections.sort(events);
		int x = 0;
		int ln = 0;
		int rn = n;
		int lln = 0;
		int rrn = n;
		boolean bad = true;
		int res = 0;
		for (Event event : events) {
			if (bad)
				res += norm(event.time, 0, l) - norm(x, 0, l);
			x = event.time;
			switch (event.type) {
			case 1:
				ln++;
				rn--;
				break;
			case 2:
				lln++;
				break;
			case 3:
				rrn--;
				break;
			}
			bad = (lln == 0 || lln == ln || rrn == 0 || rrn == rn);
		}
		out.println(res);
	}

	private int norm(int x, int min, int max) {
		return Math.min(Math.max(x, min), max);
	}

	class Event implements Comparable<Event> {
		int time;
		int type;

		Event(int time, int type) {
			this.time = time;
			this.type = type;
		}

		public int compareTo(Event o) {
			return time - o.time;
		}
	}
}

