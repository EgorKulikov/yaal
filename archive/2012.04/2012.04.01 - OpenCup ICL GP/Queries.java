package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Queries {
	static class Heap {
		int num;
		PointFunc op;
		final int heapIndex;
		final Point[] heap;

		public Heap(int heapIndex, int max) {
			this.heapIndex = heapIndex;
			heap = new Point[max];
		}

		public void init(PointFunc op, Point[] p) {
			this.op = op;
			Arrays.fill(heap, null);
			num = 0;
			for (Point pp : p) {
				pp.inHeap[heapIndex] = -1;
				pp.heapVal[heapIndex] = op.apply(pp);
			}
		}

		public void add(Point point) {
			if (point.inHeap[heapIndex] >= 0) throw new RuntimeException();
			heap[num] = point;
			point.inHeap[heapIndex] = num;
			++num;
			heapUp(num - 1);
		}

		private void heapUp(int at) {
			while (at > 0) {
				int i = (at - 1) / 2;
				if (heap[i].heapVal[heapIndex] < heap[at].heapVal[heapIndex]) {
					swap(at, i);
					at = i;
				} else break;
			}
		}

		private void swap(int at, int i) {
			Point pi = heap[at];
			Point pat = heap[i];
			heap[i] = pi;
			heap[at] = pat;
			pi.inHeap[heapIndex] = i;
			pat.inHeap[heapIndex] = at;
		}

		public void remove(Point point) {
			int pos = point.inHeap[heapIndex];
			if (pos < 0) throw new RuntimeException();
			if (pos != num - 1) {
				swap(pos, num - 1);
				heap[--num] = null;
				heapUp(pos);
				heapDown(pos);
			} else {
				heap[--num] = null;
			}
			point.inHeap[heapIndex] = -1;
		}

		private void heapDown(int at) {
			while (true) {
				int i = at;
				if (2 * at + 1 < num && heap[2 * at + 1].heapVal[heapIndex] > heap[i].heapVal[heapIndex])
					i = 2 * at + 1;
				if (2 * at + 2 < num && heap[2 * at + 2].heapVal[heapIndex] > heap[i].heapVal[heapIndex])
					i = 2 * at + 2;
				if (at == i) break;
				swap(at, i);
				at = i;
			}
		}

		public int getMax() {
			if (num == 0)
				return Integer.MIN_VALUE;
			else
				return heap[0].heapVal[heapIndex];
		}
	}
	
	static class Point implements Comparable<Point> {
		final int index;
		final int x;
		final int y;
		int evaled;
		final int[] inHeap = new int[]{-1, -1};
		final int[] heapVal = new int[2];
		
		Point(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}

		public int compareTo(Point o) {
			return evaled - o.evaled;
		}
	}
	
	static interface PointFunc {
		int apply(Point what);
	}
	
	PointFunc negate(final PointFunc other) {
		return new PointFunc() {
			public int apply(Point what) {
				return -other.apply(what);
			}
		};
	}
	
	enum Direction {
		X_CONST(0, new PointFunc() {
			public int apply(Point what) {
				return what.x;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return what.y;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return what.x - what.y;
			}
		}),
		Y_CONST(1, new PointFunc() {
			public int apply(Point what) {
				return what.y;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return what.x;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return what.y - what.x;
			}
		}),
		XMY_CONST(2, new PointFunc() {
			public int apply(Point what) {
				return what.x - what.y;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return what.x;
			}
		}, new PointFunc() {
			public int apply(Point what) {
				return -what.y;
			}
		});
		
		final int value;
		final PointFunc eval; 
		final PointFunc ort1;
		final PointFunc ort2;

		Direction(int value, PointFunc eval, PointFunc ort1, PointFunc ort2) {
			this.value = value;
			this.eval = eval;
			this.ort1 = ort1;
			this.ort2 = ort2;
		}
	}
	
	static class Line implements Comparable<Line> {
		final Direction dir;
		final int value;
		final int index;
		long answer = 0;

		Line(Direction dir, int value, int index) {
			this.dir = dir;
			this.value = value;
			this.index = index;
		}

		public int compareTo(Line o) {
			int z = dir.value - o.dir.value;
			if (z != 0) return z;
			return value - o.value;
		}
	}
	
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		n *= 3;
		Point[] p = new Point[n];
		for (int i = 0; i < n; ++i) {
			int x = in.readInt();
			int y = in.readInt();
			p[i] = new Point(x, y, i);
		}
		Point[] pOriginal = p.clone();
		Line[] l = new Line[k];
		for (int i = 0; i < k; ++i) {
			int x1 = in.readInt();
			int y1 = in.readInt();
			int x2 = in.readInt();
			int y2 = in.readInt();
			if (x1 == x2) {
				if (y1 == y2) throw new RuntimeException();
				l[i] = new Line(Direction.X_CONST, x1, i);
			} else if (y1 == y2) {
				l[i] = new Line(Direction.Y_CONST, y1, i);
			} else {
				if (x1 - y1 != x2 - y2) throw new RuntimeException();
				l[i] = new Line(Direction.XMY_CONST, x1 - y1, i);
			}
		}
		Arrays.sort(l);
		Heap[] heap = new Heap[2];
		for (int i = 0; i < 2; ++i) {
			heap[i] = new Heap(i, n);
		}
		int[] toHandle = new int[n];
		int[] counts = new int[n / 3];
		for (Direction dir : Direction.values()) {
			int first = -1;
			int last = -2;
			for (int i = 0; i < l.length; ++i) {
				Line ll = l[i];
				if (ll.dir == dir) {
					last = i;
					if (first < 0) first = i;
				}
			}
			for (Point pp : p) {
				pp.evaled = dir.eval.apply(pp);
			}
			Arrays.sort(p);
			heap[0].init(negate(dir.ort1), p);
			heap[1].init(negate(dir.ort2), p);
			Arrays.fill(counts, 0);
			int ptr = 0;
			for (int i = first; i <= last;) {
				Line ll = l[i];
				int sameI = i;
				while (sameI + 1 <= last && l[sameI + 1].value == ll.value) {
					++sameI;
				}
				int toHandleCnt = 0;
				while (ptr < p.length && p[ptr].evaled <= ll.value) {
					int blockId = p[ptr].index / 3;
					if (counts[blockId] == 0 && p[ptr].evaled == ll.value) {
						toHandle[toHandleCnt++] = ptr;
						++ptr;
						continue;
					}
					++counts[blockId];
					if (counts[blockId] == 3) {
						for (int j = 0; j < 3; ++j) {
							Point ppp = pOriginal[blockId * 3 + j];
							if (ppp != p[ptr]) {
								heap[0].remove(ppp);
								heap[1].remove(ppp);
							}
						}
					} else {
						heap[0].add(p[ptr]);
						heap[1].add(p[ptr]);
					}
					++ptr;
				}
				int max1 = heap[0].getMax();
				int max2 = heap[1].getMax();
				if (max1 > Integer.MIN_VALUE) { 
					int size = ll.value - (-max1 + -max2);
					if (size <= 0) throw new RuntimeException();
					for (int ii = i; ii <= sameI; ++ii)
						l[ii].answer += size * (long) size;
				}
				for (int iii = 0; iii < toHandleCnt; ++iii) {
					int savePtr = ptr;
					ptr = toHandle[iii];
					int blockId = p[ptr].index / 3;
					heap[0].add(p[ptr]);
					heap[1].add(p[ptr]);
					++counts[blockId];
					if (counts[blockId] == 3) {
						throw new RuntimeException();
					}
					ptr = savePtr;
				}
				i = sameI + 1;
			}
			ptr = p.length - 1;
			heap[0].init(dir.ort1, p);
			heap[1].init(dir.ort2, p);
			Arrays.fill(counts, 0);
			for (int i = last; i >= first;) {
				Line ll = l[i];
				int sameI = i;
				while (sameI - 1 >= first && l[sameI - 1].value == ll.value) {
					--sameI;
				}
				int toHandleCnt = 0;
				while (ptr >= 0 && p[ptr].evaled >= ll.value) {
					int blockId = p[ptr].index / 3;
					if (counts[blockId] == 0 && p[ptr].evaled == ll.value) {
						toHandle[toHandleCnt++] = ptr;
						--ptr;
						continue;
					}
					++counts[blockId];
					if (counts[blockId] == 3) {
						for (int j = 0; j < 3; ++j) {
							Point ppp = pOriginal[blockId * 3 + j];
							if (ppp != p[ptr]) {
								heap[0].remove(ppp);
								heap[1].remove(ppp);
							}
						}
					} else {
						heap[0].add(p[ptr]);
						heap[1].add(p[ptr]);
					}
					--ptr;
				}
				int max1 = heap[0].getMax();
				int max2 = heap[1].getMax();
				if (max1 > Integer.MIN_VALUE) { 
					int size = max1 + max2 - ll.value;
					if (size <= 0) throw new RuntimeException();
					for (int ii = i; ii >= sameI; --ii)
						l[ii].answer += size * (long) size;
				}
				for (int iii = 0; iii < toHandleCnt; ++iii) {
					int savePtr = ptr;
					ptr = toHandle[iii];
					int blockId = p[ptr].index / 3;
					heap[0].add(p[ptr]);
					heap[1].add(p[ptr]);
					++counts[blockId];
					if (counts[blockId] == 3) {
						throw new RuntimeException();
					}
					ptr = savePtr;
				}
				i = sameI - 1;
			}
		}
		long[] answers = new long[l.length];
		for (Line ll : l)
			answers[ll.index] = ll.answer;
		for (long x : answers) {
			out.printLine(x);
		}
	}
}
