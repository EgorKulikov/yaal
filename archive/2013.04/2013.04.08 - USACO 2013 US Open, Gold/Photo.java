package net.egork;

import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Photo {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int photoCount = in.readInt();
		final int[] from = new int[photoCount];
		final int[] to = new int[photoCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		boolean[] valid = new boolean[count];
		int[] couldBe = new int[count];
		int[] shouldBe = new int[count];
		int[][] start = GraphUtils.buildOrientedGraph(count, from, to);
		int[][] end = GraphUtils.buildOrientedGraph(count, to, from);
		NavigableSet<Integer> toRight = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (to[o1] != to[o2])
					return to[o1] - to[o2];
				return o1 - o2;
			}
		});
		NavigableSet<Integer> currentRight = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (to[o1] != to[o2])
					return to[o1] - to[o2];
				return o1 - o2;
			}
		});
		NavigableSet<Integer> toLeft = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (from[o1] != from[o2])
					return from[o1] - from[o2];
				return o1 - o2;
			}
		});
		NavigableSet<Integer> currentLeft = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (from[o1] != from[o2])
					return from[o1] - from[o2];
				return o1 - o2;
			}
		});
		for (int i = 0; i < photoCount; i++)
			toRight.add(i);
		for (int i = 0; i < count; i++) {
			for (int j : start[i]) {
				currentLeft.add(j);
				currentRight.add(j);
				toRight.remove(j);
			}
			if (valid[i] = currentLeft.isEmpty() || (toLeft.isEmpty() || from[toLeft.last()] < from[currentLeft.first()]) &&
				(toRight.isEmpty() || to[toRight.first()] > to[currentRight.last()]))
			{
				if (currentLeft.isEmpty())
					couldBe[i] = i - 1;
				else
					couldBe[i] = from[currentLeft.first()] - 1;
				if (toLeft.isEmpty())
					shouldBe[i] = -1;
				else
					shouldBe[i] = from[toLeft.last()];
			}
			for (int j : end[i]) {
				currentLeft.remove(j);
				currentRight.remove(j);
				toLeft.add(j);
			}
		}
		LongIntervalTree tree = new LongIntervalTree(count + 1) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return Math.max(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return Math.max(value, delta);
			}

			@Override
			protected long neutralValue() {
				return -1;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}
		};
		tree.init();
		tree.update(0, 0, 0);
		for (int i = 0; i < count; i++) {
			if (valid[i]) {
				long result = tree.query(shouldBe[i] + 1, couldBe[i] + 1);
				if (result != -1)
					tree.update(i + 1, i + 1, result + 1);
			}
		}
		out.printLine(tree.query(from[toLeft.last()] + 1, count));
    }
}
