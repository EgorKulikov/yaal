package April2011.CodeforcesBetaRound68;

import net.egork.collections.Pair;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		NavigableSet<Pair<Long, Long>> byLength = new TreeSet<Pair<Long, Long>>(new Comparator<Pair<Long, Long>>() {
			public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
				long length1 = o1.second() - o1.first();
				long length2 = o2.second() - o2.first();
				int value = IntegerUtils.longCompare(length1, length2);
				if (value != 0)
					return -value;
				return IntegerUtils.longCompare(o2.first(), o1.first());
			}
		});
		NavigableSet<Pair<Long, Long>> byOrder = new TreeSet<Pair<Long, Long>>();
		Pair<Long, Long> all = new Pair<Long, Long>(1L, (long)count);
		byLength.add(all);
		byOrder.add(all);
		Map<Integer, Integer> atWork = new HashMap<Integer, Integer>();
		int[] employee = new int[queryCount];
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		NavigableMap<Integer, Integer> possibleLocations = new TreeMap<Integer, Integer>();
		for (int it = 0; it < queryCount; it++) {
			employee[it] = in.readInt();
			if (employee[it] == 0) {
				from[it] = in.readInt();
				to[it] = in.readInt();
			} else {
				if (!atWork.containsKey(employee[it])) {
					int position = add(byLength, byOrder);
					atWork.put(employee[it], position);
					possibleLocations.put(position, 0);
				} else {
					remove(byLength, byOrder, atWork.get(employee[it]));
					atWork.remove(employee[it]);
				}
			}
		}
		int index = 0;
		for (Map.Entry<Integer, Integer> entry : possibleLocations.entrySet())
			entry.setValue(index++);
		SumIntervalTree tree = new SumIntervalTree(index);
		byLength.clear();
		byOrder.clear();
		atWork.clear();
		byLength.add(all);
		byOrder.add(all);
		for (int it = 0; it < queryCount; it++) {
			if (employee[it] == 0) {
				NavigableMap<Integer, Integer> segment = possibleLocations.tailMap(from[it], true).headMap(to[it], true);
				if (segment.isEmpty())
					out.println(0);
				else
					out.println(tree.getSegment(segment.firstEntry().getValue(), segment.lastEntry().getValue() + 1));
			} else {
				if (!atWork.containsKey(employee[it])) {
					int position = add(byLength, byOrder);
					atWork.put(employee[it], position);
					tree.put(possibleLocations.get(position), 1);
				} else {
					int position = atWork.get(employee[it]);
					remove(byLength, byOrder, position);
					tree.put(possibleLocations.get(position), -1);
					atWork.remove(employee[it]);
				}
			}
		}
	}

	private void remove(NavigableSet<Pair<Long, Long>> byLength, NavigableSet<Pair<Long, Long>> byOrder, long position) {
		Pair<Long, Long> toTest = new Pair<Long, Long>(position, position);
		Pair<Long, Long> left = null;
		SortedSet<Pair<Long, Long>> headSet = byOrder.headSet(toTest);
		if (!headSet.isEmpty())
			left = headSet.last();
		if (left != null && left.second() + 1 != position)
			left = null;
		Pair<Long, Long> right = null;
		SortedSet<Pair<Long, Long>> tailSet = byOrder.tailSet(toTest);
		if (!tailSet.isEmpty())
			right = tailSet.first();
		if (right != null && right.first() - 1 != position)
			right = null;
		if (left != null) {
			byLength.remove(left);
			byOrder.remove(left);
		}
		if (right != null) {
			byLength.remove(right);
			byOrder.remove(right);
		}
		Pair<Long, Long> toAdd = new Pair<Long, Long>(left != null ? left.first() : position, right != null ? right.second() : position);
		byLength.add(toAdd);
		byOrder.add(toAdd);
	}

	private int add(NavigableSet<Pair<Long, Long>> byLength, NavigableSet<Pair<Long, Long>> byOrder) {
		Pair<Long, Long> toDivide = byLength.first();
		byLength.remove(toDivide);
		byOrder.remove(toDivide);
		long position = (toDivide.first() + toDivide.second() + 1) / 2;
		Pair<Long, Long> left = new Pair<Long, Long>(toDivide.first(), position - 1);
		Pair<Long, Long> right = new Pair<Long, Long>(position + 1, toDivide.second());
		if (left.first() <= left.second()) {
			byLength.add(left);
			byOrder.add(left);
		}
		if (right.first() <= right.second()) {
			byLength.add(right);
			byOrder.add(right);
		}
		return (int) position;
	}
}

