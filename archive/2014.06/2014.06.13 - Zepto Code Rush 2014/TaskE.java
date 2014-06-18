package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] sngl = new int[count];
		int[] dbl = new int[count];
		IOUtils.readIntArrays(in, sngl, dbl);
		int[] delta = new int[count];
		for (int i = 0; i < count; i++) {
			delta[i] = dbl[i] - sngl[i];
		}
		int[] order = ArrayUtils.order(delta);
		int bestAt = -1;
		int bestDoubles = -1;
		long best = Long.MAX_VALUE;
		TreapSet doubles = new TreapSet(dbl, null, null);
		TreapSet singles = new TreapSet(sngl, null, null);
		for (int i = 0; i < count; i++)
			singles.add(i);
		for (int i = 0; i <= count; i++) {
			int left = Math.max(0, (required - singles.size() + 1) / 2);
			int right = Math.min(doubles.size(), required / 2);
			if (left > right) {
				if (i != count) {
					doubles.add(order[i]);
					singles.remove(order[i]);
				}
				continue;
			}
			while (right - left >= 3) {
				int midLeft = (left * 2 + right) / 3;
				int midRight = (right * 2 + left) / 3;
				long leftValue = value(midLeft, required, doubles, singles);
				long rightValue = value(midLeft, required, doubles, singles);
				if (leftValue < rightValue)
					right = midRight;
				else
					left = midLeft;
			}
			for (int j = left; j <= right; j++) {
				long value = value(j, required, doubles, singles);
				if (value < best) {
					bestAt = i;
					bestDoubles = j;
					best = value;
				}
			}
			if (i != count) {
				doubles.add(order[i]);
				singles.remove(order[i]);
			}
		}
		doubles = new TreapSet(dbl, null, null);
		singles = new TreapSet(sngl, null, null);
		for (int i = 0; i < count; i++)
			singles.add(i);
		for (int i = 0; i < bestAt; i++) {
			doubles.add(order[i]);
			singles.remove(order[i]);
		}
		char[] answer = new char[count];
		Arrays.fill(answer, '0');
		for (int i = 0; i < bestDoubles; i++)
			answer[doubles.get(i)] = '2';
		for (int i = 0; i < required - 2 * bestDoubles; i++)
			answer[singles.get(i)] = '1';
		out.printLine(best);
		out.printLine(answer);
	}

	private long value(int at, int required, TreapSet doubles, TreapSet singles) {
		return doubles.sum(at) + singles.sum(required - 2 * at);
	}

	static class TreapSet {
		protected static final Random rnd = new Random(239);
	
		protected final Node nullNode;
		protected final IntComparator comparator;
		protected Node root;
		int[] array;
	
		protected TreapSet(int[] array, Node root, Node nullNode) {
			this.array = array;
			this.comparator = new IntComparator() {
				public int compare(int o1, int o2) {
					if (array[o1] != array[o2])
						return array[o1] - array[o2];
					return o1 - o2;
				}
			};
			if (nullNode == null)
				nullNode = new NullNode();
			if (root == null)
				root = nullNode;
			this.root = root;
			this.nullNode = nullNode;
		}

		public int size() {
			return root.size;
		}

		@SuppressWarnings({"unchecked"})
		public boolean contains(int key) {
			return root.search(key) != nullNode;
		}

		public boolean add(int k) {
			if (contains(k))
				return false;
			root = root.insert(createNode(k));
			return true;
		}
	
		protected Node createNode(int k) {
			return new Node(k, rnd.nextLong());
		}
	
		public boolean remove(int key) {
			if (!contains(key))
				return false;
			//noinspection unchecked
			root = root.erase(key);
			return true;
		}

		public int get(int index) {
			if (index >= size() || index < 0)
				throw new IndexOutOfBoundsException(Integer.toString(index));
			return root.get(index);
		}
	
		protected int compare(int first, int second) {
			return comparator.compare(first, second);
		}

		public long sum(int index) {
			if (root == nullNode || index == 0)
				return 0;
			return root.sum(index);
		}

		protected class Node {
			protected final int key;
			protected final long priority;
			protected Node left;
			protected Node right;
			protected int size;
			protected long sum;
	
			protected Node(int key, long priority) {
				this.key = key;
				this.priority = priority;
				left = nullNode;
				right = nullNode;
				size = 1;
				if (key != -1)
					sum = array[key];
				else
					sum = 0;
			}
	
			@SuppressWarnings({"unchecked"})
			protected Object[] split(int key) {
				if (compare(key, this.key) < 0) {
					Object[] result = left.split(key);
					left = (Node) result[1];
					result[1] = this;
					updateSize();
					return result;
				}
				Object[] result = right.split(key);
				right = (Node) result[0];
				result[0] = this;
				updateSize();
				return result;
			}
	
			protected void updateSize() {
				size = left.size + right.size + 1;
				sum = left.sum + right.sum + (key == -1 ? 0 : array[key]);
			}
	
			@SuppressWarnings({"unchecked"})
			protected Node insert(Node node) {
				if (node.priority > priority) {
					Object[] result = split(node.key);
					node.left = (Node) result[0];
					node.right = (Node) result[1];
					node.updateSize();
					return node;
				}
				if (compare(node.key, this.key) < 0) {
					left = left.insert(node);
					updateSize();
					return this;
				}
				right = right.insert(node);
				updateSize();
				return this;
			}
	
			protected Node merge(Node left, Node right) {
				if (left == nullNode)
					return right;
				if (right == nullNode)
					return left;
				if (left.priority > right.priority) {
					left.right = left.right.merge(left.right, right);
					left.updateSize();
					return left;
				}
				right.left = right.left.merge(left, right.left);
				right.updateSize();
				return right;
			}
	
			protected Node erase(int key) {
				int value = compare(key, this.key);
				if (value == 0)
					return merge(left, right);
				if (value < 0) {
					left = left.erase(key);
					updateSize();
					return this;
				}
				right = right.erase(key);
				updateSize();
				return this;
			}
	
			protected Node lower(int key) {
				if (compare(key, this.key) <= 0)
					return left.lower(key);
				Node result = right.lower(key);
				if (result == nullNode)
					return this;
				return result;
			}
	
			protected Node floor(int key) {
				if (compare(key, this.key) < 0)
					return left.floor(key);
				Node result = right.floor(key);
				if (result == nullNode)
					return this;
				return result;
			}
	
			protected Node higher(int key) {
				if (compare(key, this.key) >= 0)
					return right.higher(key);
				Node result = left.higher(key);
				if (result == nullNode)
					return this;
				return result;
			}
	
			protected Node ceil(int key) {
				if (compare(key, this.key) > 0)
					return right.ceil(key);
				Node result = left.ceil(key);
				if (result == nullNode)
					return this;
				return result;
			}
	
			protected Node first() {
				if (left == nullNode)
					return this;
				return left.first();
			}
	
			protected Node last() {
				if (right == nullNode)
					return this;
				return right.last();
			}
	
			protected Node search(int key) {
				int value = compare(key, this.key);
				if (value == 0)
					return this;
				if (value < 0)
					return left.search(key);
				return right.search(key);
			}
	
			public int indexOf(Node node) {
				if (this == node)
					return left.size;
				if (compare(node.key, this.key) > 0)
					return left.size + 1 + right.indexOf(node);
				return left.indexOf(node);
			}
	
			public int get(int index) {
				if (index < left.size)
					return left.get(index);
				else if (index == left.size)
					return key;
				else
					return right.get(index - left.size - 1);
			}

			public long sum(int index) {
				if (index >= size)
					return sum;
				if (index < left.size)
					return left.sum(index);
				if (index == left.size)
					return left.sum;
				if (index == left.size + 1)
					return left.sum + array[key];
				return left.sum + array[key] + right.sum(index - left.size - 1);
			}
		}
	
		private class NullNode extends Node {
	        private Object[] splitResult = new Object[2];
	
			private NullNode() {
				super(-1, Long.MIN_VALUE);
				left = this;
				right = this;
				size = 0;
			}
	
			@Override
			protected Object[] split(int key) {
	            splitResult[0] = splitResult[1] = this;
				return splitResult;
			}
	
			@Override
			protected Node insert(Node node) {
				return node;
			}
	
			@Override
			protected Node erase(int key) {
				return this;
			}
	
			@Override
			protected Node lower(int key) {
				return this;
			}
	
			@Override
			protected Node floor(int key) {
				return this;
			}
	
			@Override
			protected Node higher(int key) {
				return this;
			}
	
			@Override
			protected Node ceil(int key) {
				return this;
			}
	
			@Override
			protected Node first() {
				throw new NoSuchElementException();
			}
	
			@Override
			protected Node last() {
				throw new NoSuchElementException();
			}
	
			@Override
			protected void updateSize() {
			}
	
			@Override
			protected Node search(int key) {
				return this;
			}
	
	
		}
	}
	
}
