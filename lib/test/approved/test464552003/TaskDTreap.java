package approved.test464552003;

import net.egork.collections.Pair;
import net.egork.collections.set.TreapSet;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskDTreap implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		NavigableSet<Pair<Long, Long>> byLength = new TreeSet<Pair<Long, Long>>(new Comparator<Pair<Long, Long>>() {
			public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
				long length1 = o1.second - o1.first;
				long length2 = o2.second - o2.first;
				int value = IntegerUtils.longCompare(length1, length2);
				if (value != 0)
					return -value;
				return IntegerUtils.longCompare(o2.first, o1.first);
			}
		});
		NavigableSet<Pair<Long, Long>> byOrder = new TreeSet<Pair<Long, Long>>(new Pair.Comparator<Long, Long>());
		Pair<Long, Long> all = Pair.makePair(1L, (long)count);
		byLength.add(all);
		byOrder.add(all);
		Map<Integer, Integer> atWork = new HashMap<Integer, Integer>();
		NavigableSet<Integer> occupied = new TreapSet<Integer>();
		for (int it = 0; it < queryCount; it++) {
			int employee = in.readInt();
			if (employee == 0) {
				int from = in.readInt();
				int to = in.readInt();
				out.println(occupied.subSet(from, true, to, true).size());
			} else {
				if (!atWork.containsKey(employee)) {
					int position = add(byLength, byOrder);
					atWork.put(employee, position);
					occupied.add(position);
				} else {
					int position = atWork.get(employee);
					remove(byLength, byOrder, position);
					atWork.remove(employee);
					occupied.remove(position);
				}
			}
		}
	}

	private void remove(NavigableSet<Pair<Long, Long>> byLength, NavigableSet<Pair<Long, Long>> byOrder, long position) {
		Pair<Long, Long> toTest = Pair.makePair(position, position);
		Pair<Long, Long> left = null;
		SortedSet<Pair<Long, Long>> headSet = byOrder.headSet(toTest);
		if (!headSet.isEmpty())
			left = headSet.last();
		if (left != null && left.second + 1 != position)
			left = null;
		Pair<Long, Long> right = null;
		SortedSet<Pair<Long, Long>> tailSet = byOrder.tailSet(toTest);
		if (!tailSet.isEmpty())
			right = tailSet.first();
		if (right != null && right.first - 1 != position)
			right = null;
		if (left != null) {
			byLength.remove(left);
			byOrder.remove(left);
		}
		if (right != null) {
			byLength.remove(right);
			byOrder.remove(right);
		}
		Pair<Long, Long> toAdd = Pair.makePair(left != null ? left.first : position, right != null ? right.second : position);
		byLength.add(toAdd);
		byOrder.add(toAdd);
	}

	private int add(NavigableSet<Pair<Long, Long>> byLength, NavigableSet<Pair<Long, Long>> byOrder) {
		Pair<Long, Long> toDivide = byLength.first();
		byLength.remove(toDivide);
		byOrder.remove(toDivide);
		long position = (toDivide.first + toDivide.second + 1) / 2;
		Pair<Long, Long> left = Pair.makePair(toDivide.first, position - 1);
		Pair<Long, Long> right = Pair.makePair(position + 1, toDivide.second);
		if (left.first <= left.second) {
			byLength.add(left);
			byOrder.add(left);
		}
		if (right.first <= right.second) {
			byLength.add(right);
			byOrder.add(right);
		}
		return (int) position;
	}

}



