package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.*;

/**
 * @author egor@egork.net
 */
public class Solution {
	Node[] roots;

	static class Node {
		int from;
		int to;
		int value;
		Node left;
		Node right;

		Node(int from, int to) {
			this(from, to, from == to ? null : new Node(from, (from + to) >> 1),
				from == to ? null : new Node(((from + to) >> 1) + 1, to));
		}

		Node(int from, int to, Node left, Node right) {
			this.from = from;
			this.to = to;
			this.left = left;
			this.right = right;
			if (left != null) {
				value = left.value + right.value;
			}
		}

		final Node add(int at) {
			if (from > at || at > to) {
				return this;
			}
			if (from == to) {
				Node node = new Node(from, to);
				node.value = value + 1;
				return node;
			}
			return new Node(from, to, left.add(at), right.add(at));
		}

		final int query(int right) {
			if (right < from) {
				return 0;
			}
			if (to <= right) {
				return value;
			}
			return this.left.query(right) + this.right.query(right);
		}
	}

	int binarySearch(Node top, Node bottom, int delta) {
		if (delta <= 0) {
			return top.to + 1;
		}
		delta = top.value - bottom.value - delta + 1;
//		delta += bottom.query(delta - 1) - top.query(delta - 1);
		while (top.from != top.to) {
			int current = top.left.value - bottom.left.value;
			if (current < delta) {
				delta -= current;
				top = top.right;
				bottom = bottom.right;
			} else {
				top = top.left;
				bottom = bottom.left;
			}
		}
		return top.from + 1;
	}

	enum Type {
		REMOVE,
		ADD
	}

	static class Event implements Comparable<Event> {
		int index;
		int at;
		Type type;

		public Event(int index, int at, Type type) {
			this.index = index;
			this.at = at;
			this.type = type;
		}

		@Override
		public int compareTo(Event o) {
			if (at != o.at) {
				return at - o.at;
			}
			if (type.ordinal() != o.type.ordinal()) {
				return type.ordinal() - o.type.ordinal();
			}
			return index - o.index;
		}
	}

	public void init(int n, int[] a, int[] b) {
		ArrayUtils.orderBy(a, b);
		roots = new Node[n + 1];
		Node current = roots[0] = new Node(0, n);
		for (int i = 0; i < n; i++) {
			current = roots[a[i]] = current.add(b[i]);
		}
		current = roots[0];
		for (int i = 1; i <= n; i++) {
			if (roots[i] == null) {
				roots[i] = current;
			} else {
				current = roots[i];
			}
		}
	}

	public String can(int m, int[] k) {
		k = Arrays.copyOf(k, ++m);
		Arrays.sort(k);
		int[] d = new int[m];
		NavigableSet<Integer> set = new TreeSet<>();
		PriorityQueue<Event> queue = new PriorityQueue<>();
		for (int i = 1; i < m; i++) {
			queue.add(new Event(i, k[i], Type.ADD));
		}
		set.add(0);
		while (!queue.isEmpty()) {
			Event event = queue.poll();
			int at = event.index;
			if (event.type == Type.ADD) {
				int last = set.last();
				d[at] = d[last] + roots[k[at]].value - roots[k[last]].value -
					(roots[k[at]].query(k[at] - 1) - roots[k[last]].query(k[at] - 1)) - k[at];
				queue.add(new Event(at, binarySearch(roots[k[at]], roots[k[last]], d[at] - d[last]), Type.REMOVE));
				set.add(at);
			} else {
				if (!set.contains(at)) {
					continue;
				}
				Integer next = set.higher(at);
				int last = set.lower(at);
				set.remove(at);
				if (next != null) {
					queue.add(new Event(next, binarySearch(roots[k[next]], roots[k[last]], d[next] - d[last]), Type.REMOVE));
				}
			}
		}
		if (ArrayUtils.minElement(d) < 0) {
			return "NO";
		} else {
			return "YES";
		}
	}
}

