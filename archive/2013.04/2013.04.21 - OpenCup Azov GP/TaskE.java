package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {

	private int[][] a;
	private Heap[][] bl;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = in.readInt();
			}
		}
		Heap[] h = new Heap[n + m];
		int nn = 0;
		for (int i = 0; i < n; i++) {
			h[nn] = new Heap(m);
//			h[nn].id = "r" + i;
			for (int j = 0; j < m; j++) {
				h[nn].x[j] = i;
				h[nn].y[j] = j;
			}
			h[nn].init();
			nn++;
		}
		for (int i = 0; i < m; i++) {
			h[nn] = new Heap(n);
//			h[nn].id = "c" + i;
			for (int j = 0; j < n; j++) {
				h[nn].y[j] = i;
				h[nn].x[j] = j;
			}
			h[nn].init();
			nn++;
		}
		bl = new Heap[n][m];
		long s = 0;
		for (int i = 0; i < nn; i++) {
			Heap hh = h[i];
			while (true) {
				if (hh.n == 0) throw new RuntimeException();
				int xx = hh.x[0];
				int yy = hh.y[0];
//				System.err.println(hh + " " + xx + " " + yy);
				if (bl[xx][yy] == null) {
					bl[xx][yy] = hh;
					s += a[xx][yy];
					break;
				} else {
					bl[xx][yy] = bl[xx][yy].getParent();
					if (bl[xx][yy] == hh) {
						hh.deleteMin();
					} else {
						hh = hh.merge(bl[xx][yy]);
//						return;
					}
				}
			}
//			System.err.println(s);
		}
		out.printLine(s);
    }

	int[] q = new int[2000000];
	int[] z = new int[2000000];
	int ccc;


	class Heap {
		String id;
		Heap parent;

		@Override
		public String toString() {
			return id;
		}

		int n;
		int[] x;
		int[] y;

		public Heap(int n) {
			x = new int[2 * n];
			y = new int[2 * n];
			this.n = n;
			parent = this;
		}

		public Heap getParent() {
			if (parent == this)
				return this;
			return parent = parent.getParent();
		}

		public void init() {
//			for (int i = n - 1; i >= 0; i--) {
//				siftDown(i);
//			}
			for (int i = 0; i < n; i++)
				siftUp(i);
		}

		private void siftUp(int i) {
			while (i != 0) {
				int parent = (i - 1) >> 1;
				if (a[x[i]][y[i]] < a[x[parent]][y[parent]]) {
					int t = x[i]; x[i] = x[parent]; x[parent] = t;
					t = y[i]; y[i] = y[parent]; y[parent] = t;
					i = parent;
				} else
					break;
			}
		}

		private void siftDown(int i) {
			int j = i;
			if (2 * i + 1 < n && a[x[2 * i + 1]][y[2 * i + 1]] < a[x[j]][y[j]]) {
				j = 2 * i + 1;
			}
			if (2 * i + 2 < n && a[x[2 * i + 2]][y[2 * i + 2]] < a[x[j]][y[j]]) {
				j = 2 * i + 2;
			}
			if (j == i) return;
			int t = x[i]; x[i] = x[j]; x[j] = t;
			t = y[i]; y[i] = y[j]; y[j] = t;
			siftDown(j);
		}

		public void deleteMin() {
			n--;
			x[0] = x[n];
			y[0] = y[n];
			siftDown(0);
		}

		public Heap merge(Heap b) {
//			System.err.println("merge " + id + " " + b.id);
			if (n < b.n) {
				return b.merge(this);
			}
			enlargeYourPenis(n + b.n);
			System.arraycopy(b.x, 0, x, n, b.n);
			System.arraycopy(b.y, 0, y, n, b.n);
			int h = 0;
			int t = b.n;
			ccc++;
			for (int i = 0; i < b.n; i++) {
//				if (bl[b.x[i]][b.y[i]] == b) {
//					bl[b.x[i]][b.y[i]] = this;
//				}
				siftUp(n + i);
//				q[i] = n + b.n - i - 1;
//				z[n + b.n - i - 1] = ccc;
			}
//			while (t > h) {
//				int i = q[h];
//				h++;
//				if (i == 0) break;
//				int j = ((i - 1) >> 1);
//				if (a[x[i]][y[i]] < a[x[j]][y[j]]) {
//					int tt = x[i]; x[i] = x[j]; x[j] = tt;
//					tt = y[i]; y[i] = y[j]; y[j] = tt;
//					if (z[j] != ccc) {
//						z[j] = ccc;
//						q[t++] = j;
//					}
//				}
//			}
			n += b.n;
			b.parent = this;
			return this;
		}

		private void enlargeYourPenis(int nn) {
			if (x.length < nn) {
				int[] xx = new int[nn * 2];
				System.arraycopy(x, 0, xx, 0, n);
				x = xx;
				int[] yy = new int[nn * 2];
				System.arraycopy(y, 0, yy, 0, n);
				y = yy;
			}
		}
	}
}
