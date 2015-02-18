package net.egork;


import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Task8 {

	public static final int SIZE = 10000000;
	int nn = 0;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		nn = n * 2 + 2;
		Colony[] col = new Colony[nn];
		for (int i = 0; i < n; i++) {
			col[i] = new Colony(i, 1);
		}
		Heap q = new Heap(new IntComparator() {
			@Override
			public int compare(int first, int second) {
				return Double.compare(key[first], key[second]);
			}
		}, SIZE);
//		PriorityQueue<Dist> q = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			String s = in.readString();
			for (int j = 0; j < i; j++) {
				int d = s.charAt(j) - '0';
//				Dist dist = new Dist(col[i], col[j], d);
				newDist(col[i], col[j], d);
				col[i].distances[j] = dd - 1;
				col[j].distances[i] = dd - 1;
				q.add(dd - 1);
			}
		}
		while (q.getSize() > 0) {
			int min = q.poll();
//			while (removed[min]) {
//				if (q.getSize() == 0) return;
//				min = q.poll();
//			}
			Colony ff = distX[min];
			Colony ss = distY[min];
			ff.distances[ss.id] = -1;
			ss.distances[ff.id] = -1;
			Colony merged = new Colony(n, ff.size + ss.size);
			col[n] = merged;
			n++;
			out.printLine(ff.id + 1, ss.id + 1, sumdist[min] * 1.0 / (1.0 * ff.size * ss.size));
			for (int i = 0; i < nn; i++) {
				int aa = ff.distances[i];
				int bb = ss.distances[i];
				if (aa == -1) continue;
				q.remove(aa);
				q.remove(bb);
				col[i].distances[ff.id] = -1;
				col[i].distances[ss.id] = -1;
				int zz = newDist(merged, col[i], sumdist[aa] + sumdist[bb]);
				merged.distances[i] = zz;
				col[i].distances[merged.id] = zz;
				q.add(zz);
			}
		}

	}

	private int newDist(Colony x, Colony y, int d) {
		distX[dd] = x;
		distY[dd] = y;
		sumdist[dd] = d;
		key[dd] = d / (1.0 * x.size * y.size);
		return dd++;
	}

	class Colony {
		int id;
		int size;
		int[] distances = new int[nn];

		Colony(int id, int size) {
			this.id = id;
			this.size = size;
			Arrays.fill(distances, -1);
		}
	}

	int dd = 0;
	Colony[] distX = new Colony[SIZE];
	Colony[] distY = new Colony[SIZE];
	int[] sumdist = new int[SIZE];
	double[] key = new double[SIZE];

//	class Dist implements Comparable<Dist> {
//		Colony x, y;
//		long sumdist;
//		int id;
//		boolean removed;
//
//		Dist(Colony x, Colony y, long sumdist) {
//			this.x = x;
//			this.y = y;
//			this.sumdist = sumdist;
//			this.id = dd++;
//		}
//
//		@Override
//		public int compareTo(Dist o) {
//			long a = sumdist;
//			long b = 1l * x.size * y.size;
//			long c = o.sumdist;
//			long d = 1l * o.x.size * o.y.size;
//			int res = Long.compare(a * d, b * c);
//			if (res == 0) {
//				return Integer.compare(id, o.id);
//			} else {
//				return res;
//			}
//		}
//	}
}
