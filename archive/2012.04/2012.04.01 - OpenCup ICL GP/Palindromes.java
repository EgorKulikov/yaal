package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palindromes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		StringBuffer sb = new StringBuffer(".");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append(".");
		}
		s = sb.toString();
		int n = s.length();
		int m = in.readInt();
		int[] l = new int[m];
		int[] r = new int[m];
		for (int i = 0; i < m; i++) {
			l[i] = in.readInt() * 2 - 2;
			r[i] = in.readInt() * 2 + 1;
		}
		
		int ll = 0;
		int rr = 0;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			if (rr > i) {
				a[i] = Math.min(a[ll + ll - i], rr - i); 
			} else {
				a[i] = 1;
			}
			while (i - a[i] >= 0 && i + a[i] < n &&
				s.charAt(i - a[i]) == s.charAt(i + a[i])) {
				a[i]++;
			}
			if (i + a[i] > rr) {
				ll = i;
				rr = i + a[i];
			}
		}
//		for (int i = 0; i < n; i++) {
//			a[i] /= 2;
//		}
		
		Request[][] res = new Request[m][2];
		for (int i = 0; i < m; i++) {
			int mm = (l[i] + r[i]) / 2;
			res[i][0] = new Request(l[i], mm, 1 - l[i]);
			res[i][1] = new Request(mm, r[i], r[i]);
		}

		List<Operation> l1 = new ArrayList<Operation>();
		List<Operation> l2 = new ArrayList<Operation>();
		for (int i = 0; i < m; i++) {
			l1.add(res[i][0]);
			l2.add(res[i][1]);
		}
		
		for (int i = 0; i < n; i++) {
			l1.add(new Add(i, a[i] - i));
			l2.add(new Add(i, a[i] + i));
		}		
		Collections.sort(l1);
		Collections.sort(l2);

		Tree tree = new Tree(n);
		for (Operation operation : l1) {
			tree.perform(operation);
		}
		tree = new Tree(n);
		for (Operation operation : l2) {
			tree.perform(operation);
		}

//		System.out.println(Arrays.toString(a));
//		System.out.println(l1);
//		System.out.println(l2);

		for (int i = 0; i < m; i++) {
			long s1 = res[i][0].result + sum(res[i][0].l, res[i][0].r);
			long s2 = res[i][1].result - sum(res[i][1].l, res[i][1].r);
			long result = (s1 + s2 - (res[i][1].r - res[i][0].l + 1) / 2);
			if (result % 2 == 1) throw new RuntimeException();
			result /= 2;
//			System.out.println(s1 + " " + s2);
			out.printLine(result);
		}
	}

	private long sum(int l, int r) {
		return 1L * (r - l) * (l + r - 1) / 2;
	}

	class Tree {
		int n;
		long[] a;
		int[] b;

		Tree(int n) {
			this.n = n;
			int nn = 1;
			while (nn < n) nn *= 2;
			this.n = nn;
			
			a = new long[2 * nn];
			b = new int[2 * nn];
		}

		public void perform(Operation operation) {
			if (operation instanceof Add) {
				Add o = (Add) operation;
				add(o.i, o.x, 0, 0, n);
			} else {
				Request o = (Request) operation;
				o.result = get(o.l, o.r, o.x, 0, 0, n);				
			}
		}

		private long get(int ll, int rr, int x, int n, int l, int r) {
			if (ll >= r || l >= rr) return 0;
			if (ll <= l && r <= rr) return a[n] + 1L * x * (r - l - b[n]);
			int m = (l + r) / 2;
			return get(ll, rr, x, n * 2 + 1, l, m) + 
				get(ll, rr, x, n * 2 + 2, m, r);
		}

		private void add(int i, int x, int n, int l, int r) {
			if (r == l + 1) {
				a[n] = x;
				b[n] = 1;
			} else {
				int m = (l + r) / 2;
				if (i < m) {
					add(i, x, n * 2 + 1, l, m);					
				} else {
					add(i, x, n * 2 + 2, m, r);
				}
				a[n] = a[n * 2 + 1] + a[n * 2 + 2];
				b[n] = b[n * 2 + 1] + b[n * 2 + 2];
			}
		}
	}
	
	class Operation implements Comparable<Operation> {
		int x;
		public int compareTo(Operation o) {
			if (x < o.x) return -1;
			if (x > o.x) return 1;
			return 0;				
		}
	}
	
	class Request extends Operation {
		int l;
		int r;

		Request(int l, int r, int x) {
			this.l = l;
			this.r = r;
			this.x = x;
		}

		long result;

		@Override
		public String toString() {
			return "Request{" +
				"l=" + l +
				", r=" + r +
				", x=" + x +
				", result=" + result +
				'}';
		}
	}
	
	class Add extends Operation {
		int i;

		Add(int i, int x) {
			this.i = i;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Add{" +
				"i=" + i +
				"x=" + x +
				'}';
		}
	}
}
