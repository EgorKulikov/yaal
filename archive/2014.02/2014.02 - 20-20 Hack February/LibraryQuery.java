package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.set.TreapSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class LibraryQuery {
	Shelf query;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Shelf[] shelves = new Shelf[count];
		for (int i = 0; i < count; i++)
			shelves[i] = new Shelf(i, in.readInt());
		Tree tree = new Tree(shelves);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int at = in.readInt() - 1;
				int value = in.readInt();
				tree.update(at, at, value);
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt() - 1;
				int required = in.readInt();
				int left = 1;
				int right = 1000;
				while (right > left) {
					int middle = (left + right) >> 1;
					query = new Shelf(count, middle);
					if (tree.query(from, to) >= required)
						right = middle;
					else
						left = middle + 1;
				}
				out.printLine(left);
			}
		}
    }

	class Tree extends IntervalTree {
		Shelf[] shelves;
		NavigableSet<Shelf>[] sets;

		protected Tree(Shelf[] shelves) {
			super(shelves.length, false);
			this.shelves = shelves;
			init();
		}

		@Override
		protected void initData(int size, int nodeCount) {
			sets = new NavigableSet[nodeCount];
		}

		@Override
		protected void initAfter(int root, int left, int right, int middle) {
			sets[root] = new TreapSet<Shelf>(sets[2 * root + 1]);
			sets[root].addAll(sets[2 * root + 2]);
		}

		@Override
		protected void initBefore(int root, int left, int right, int middle) {
		}

		@Override
		protected void initLeaf(int root, int index) {
			sets[root] = new TreapSet<Shelf>();
			sets[root].add(shelves[index]);
		}

		@Override
		protected void updatePostProcess(int root, int left, int right, int from, int to, long delta, int middle) {
			sets[root].add(shelves[from]);
		}

		@Override
		protected void updatePreProcess(int root, int left, int right, int from, int to, long delta, int middle) {
			sets[root].remove(shelves[from]);
		}

		@Override
		protected void updateFull(int root, int left, int right, int from, int to, long delta) {
			sets[root].remove(shelves[left]);
			shelves[left] = new Shelf(left, (int)delta);
			sets[root].add(shelves[left]);
		}

		@Override
		protected long queryPostProcess(int root, int left, int right, int from, int to, int middle, long leftResult, long rightResult) {
			return leftResult + rightResult;
		}

		@Override
		protected void queryPreProcess(int root, int left, int right, int from, int to, int middle) {

		}

		@Override
		protected long queryFull(int root, int left, int right, int from, int to) {
			return sets[root].headSet(query, false).size();
		}

		@Override
		protected long emptySegmentResult() {
			return 0;
		}
	}

	static class Shelf implements Comparable<Shelf> {
		int index;
		int books;

		Shelf(int index, int books) {
			this.index = index;
			this.books = books;
		}

		public int compareTo(Shelf o) {
			if (books != o.books)
				return books - o.books;
			return index - o.index;
		}
	}
}
