package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Teams {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		teams solution = new teams();
		int N = in.readInt();
		int[] A = new int[N];
		int[] B = new int[N];
		IOUtils.readIntArrays(in, A, B);
		solution.init(N, A, B);
		int Q = in.readInt();
		for (int i = 0; i < Q; i++) {
			int M = in.readInt();
			int[] K = IOUtils.readIntArray(in, M);
			out.printLine(solution.can(M, K));
		}
	}

	static class teams {
		int[] tree = new int[300];
		int next;
		int size;
		int[] roots;

		int create(int from, int to) {
			return create(from, to, -1, -1);
		}

		int create(int from, int to, int left, int right) {
			if (next == tree.length) {
				tree = Arrays.copyOf(tree, tree.length * 2);
			}
			int at = next;
			next += 3;
			if (from != to) {
				int middle = (from + to) >> 1;
				if (left == -1) {
					left = create(from, middle, -1, -1);
				}
				tree[at] = left;
				if (right == -1) {
					right = create(middle + 1, to, -1, -1);
				}
				tree[at + 1] = right;
				tree[at + 2] = tree[left + 2] + tree[right + 2];
			}
			return at;
		}

		int add(int node, int at) {
			return add(node, at, 0, size - 1);
		}

		int add(int node, int at, int from, int to) {
			if (from > at || at > to) {
				return node;
			}
			if (from == to) {
				int value = tree[node + 2];
				node = create(from, from, -1, -1);
				tree[node + 2] = value + 1;
				return node;
			}
			int middle = (from + to) >> 1;
			int left = add(tree[node], at, from, middle);
			int right = add(tree[node + 1], at, middle + 1, to);
			return create(from, to, left, right);
		}

		int query(int node, int right) {
			return query(node, right, 0, size - 1);
		}

		int query(int node, int right, int from, int to) {
			if (right < from) {
				return 0;
			}
			if (to <= right) {
				return tree[node + 2];
			}
			int middle = (from + to) >> 1;
			return query(tree[node], right, from, middle) + query(tree[node + 1], right, middle + 1, to);
		}

		int binarySearch(int top, int bottom, int delta) {
			if (delta <= 0) {
				return size;
			}
			int from = 0;
			int to = size - 1;
			delta = tree[top + 2] - tree[bottom + 2] - delta + 1;
//		delta += bottom.query(delta - 1) - top.query(delta - 1);
			while (from != to) {
				int current = tree[tree[top] + 2] - tree[tree[bottom] + 2];
				if (current < delta) {
					delta -= current;
					top = tree[top + 1];
					bottom = tree[bottom] + 1;
					from = ((from + to) >> 1) + 1;
				} else {
					top = tree[top];
					bottom = tree[bottom];
					to = (from + to) >> 1;
				}
			}
			return from + 1;
		}

		public void init(int n, int[] a, int[] b) {
			ArrayUtils.orderBy(a, b);
			size = n + 1;
			roots = new int[n + 1];
			Arrays.fill(roots, -1);
			int current = roots[0] = create(0, n);
			for (int i = 0; i < n; i++) {
				current = roots[a[i]] = add(current, b[i]);
			}
			current = roots[0];
			for (int i = 1; i <= n; i++) {
				if (roots[i] == -1) {
					roots[i] = current;
				} else {
					current = roots[i];
				}
			}
		}

		public int can(int m, int[] k) {
			k = Arrays.copyOf(k, ++m);
			Arrays.sort(k);
			int[] d = new int[m];
			NavigableSet<Integer> set = new TreeSet<>();
			PriorityQueue<Event> queue = new PriorityQueue<>();
			for (int i = 1; i < m; i++) {
				queue.add(new teams.Event(i, k[i], teams.Type.ADD));
			}
			set.add(0);
			while (!queue.isEmpty()) {
				teams.Event event = queue.poll();
				int at = event.index;
				if (event.type == teams.Type.ADD) {
					int last = set.last();
					d[at] = d[last] + tree[roots[k[at]] + 2] - tree[roots[k[last]] + 2] -
						(query(roots[k[at]], k[at] - 1) - query(roots[k[last]], k[at] - 1)) - k[at];
					queue.add(new teams.Event(at, binarySearch(roots[k[at]], roots[k[last]], d[at] - d[last]), teams.Type.REMOVE));
					set.add(at);
				} else {
					if (!set.contains(at)) {
						continue;
					}
					Integer next = set.higher(at);
					int last = set.lower(at);
					set.remove(at);
					if (next != null) {
						queue.add(new teams.Event(next, binarySearch(roots[k[next]], roots[k[last]], d[next] - d[last]), teams.Type.REMOVE));
					}
				}
			}
			if (ArrayUtils.minElement(d) < 0) {
				return 0;
			} else {
				return 1;
			}
		}

		enum Type {
			REMOVE,
			ADD,;
		}

		static class Event implements Comparable<teams.Event> {
			int index;
			int at;
			teams.Type type;

			public Event(int index, int at, teams.Type type) {
				this.index = index;
				this.at = at;
				this.type = type;
			}


			public int compareTo(teams.Event o) {
				if (at != o.at) {
					return at - o.at;
				}
				if (type.ordinal() != o.type.ordinal()) {
					return type.ordinal() - o.type.ordinal();
				}
				return index - o.index;
			}

			@Override
			public String toString() {
				return index + " " + at + " " + type;
			}
		}

	}

}
