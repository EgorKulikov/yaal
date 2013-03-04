package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Ice {
	static class Balloon {
		int x;
		int y;
		int r;
		int inHeap = -1;
	}

	static class Heap {
		int num;
		final Balloon[] heap;

		public Heap(int max) {
			heap = new Balloon[max];
		}

		public void add(Balloon balloon) {
			if (balloon.inHeap >= 0) throw new RuntimeException();
			heap[num] = balloon;
			balloon.inHeap = num;
			++num;
			heapUp(num - 1);
		}

		private void heapUp(int at) {
			while (at > 0) {
				int i = (at - 1) / 2;
				if (heap[i].y < heap[at].y) {
					swap(at, i);
					at = i;
				} else break;
			}
		}

		private void swap(int at, int i) {
			Balloon pi = heap[at];
			Balloon pat = heap[i];
			heap[i] = pi;
			heap[at] = pat;
			pi.inHeap = i;
			pat.inHeap = at;
		}

		public void remove(Balloon balloon) {
			int pos = balloon.inHeap;
			if (pos < 0) throw new RuntimeException();
			if (pos != num - 1) {
				swap(pos, num - 1);
				heap[--num] = null;
				heapUp(pos);
				heapDown(pos);
			} else {
				heap[--num] = null;
			}
			balloon.inHeap = -1;
		}

		private void heapDown(int at) {
			while (true) {
				int i = at;
				if (2 * at + 1 < num && heap[2 * at + 1].y > heap[i].y)
					i = 2 * at + 1;
				if (2 * at + 2 < num && heap[2 * at + 2].y > heap[i].y)
					i = 2 * at + 2;
				if (at == i) break;
				swap(at, i);
				at = i;
			}
		}

		public Balloon getMax() {
			if (num == 0)
				return null;
			else
				return heap[0];
		}
	}
	
	static class Event implements Comparable<Event> {
		int when;
		Balloon what;
		boolean appears;

		Event(int when, Balloon what, boolean appears) {
			this.when = when;
			this.what = what;
			this.appears = appears;
		}

		public int compareTo(Event o) {
			if (when < o.when) return -1;
			if (when > o.when) return 1;
			return 0;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		Balloon[] balloons = new Balloon[m];
		for (int i = 0; i < m; ++i) {
			balloons[i] = new Balloon();
			balloons[i].x = in.readInt();
			balloons[i].y = in.readInt();
			balloons[i].r = in.readInt();
		}
		Heap visible = new Heap(m);
		Event[] ev = new Event[2 * m];
		for (int i = 0; i < m; ++i) {
			Balloon b = balloons[i];
			ev[2 * i] = new Event(b.x - b.r, b, true);
			ev[2 * i + 1] = new Event(b.x + b.r + 1, b, false);
		}
		Arrays.sort(ev);
		int ptr = 0;
		double[] crashTime = new double[n + 1];
		for (int x = 0; x <= n; ++x) {
			while (ptr < ev.length && ev[ptr].when <= x) {
				Event e = ev[ptr];
				if (e.appears) {
					visible.add(e.what);
				} else {
					visible.remove(e.what);
				}
				++ptr;
			}
			Balloon top = visible.getMax();
			if (top == null) {
				crashTime[x] = -1e100;
			} else {
				double start = catet(top.r, Math.abs(x - top.x)) + top.y;
				crashTime[x] = start;
			}
		}
		for (int i = 0; i < n; ++i) {
			crashTime[i] = Math.max(crashTime[i], crashTime[i + 1]);
		}
		double max = -1e100;
		for (int i = 0; i < n; ++i)
			max = Math.max(max, crashTime[i]);
		for (int i = 0; i < n; ++i)
			crashTime[i] = max - crashTime[i];
		Arrays.sort(crashTime, 0, n);
		int cnt = 0;
		double sum = 0;
		for (int i = 0; i < n; ++i) {
			if (crashTime[i] > 1e50) break;
			sum += crashTime[i];
			++cnt;
		}
		out.printLine(sum / cnt);
	}

	private double catet(int r, int x) {
		return Math.sqrt(r * (long) r - x * (long) x);
	}
}
