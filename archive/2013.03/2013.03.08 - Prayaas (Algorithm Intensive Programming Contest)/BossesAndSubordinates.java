package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.map.EHashMap;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class BossesAndSubordinates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		String[] ids = new String[count];
		Map<String, Integer> resolve = new EHashMap<String, Integer>();
		final int[] experience = new int[count];
		final int[] salary = new int[count];
		for (int i = 0; i < count; i++) {
			ids[i] = in.readString();
			resolve.put(ids[i], i);
			experience[i] = in.readInt();
			salary[i] = in.readInt();
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (experience[first] != experience[second])
					return experience[second] - experience[first];
				return salary[second] - salary[first];
			}
		});
		NavigableSet<Integer> map = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return salary[o1] - salary[o2];
			}
		});
		int[] boss = new int[count];
		for (int i : order) {
			Integer currentBoss = map.ceiling(i);
			if (currentBoss == null)
				boss[i] = -1;
			else
				boss[i] = currentBoss;
			map.add(i);
		}
		int[] subordinateCount = new int[count];
		ArrayUtils.reverse(order);
		for (int i : order) {
			if (boss[i] != -1)
				subordinateCount[boss[i]] += subordinateCount[i] + 1;
		}
		for (int i = 0; i < queryCount; i++) {
			int id = resolve.get(in.readString());
			String currentBoss;
			if (boss[id] == -1)
				currentBoss = "0";
			else
				currentBoss = ids[boss[id]];
			out.printLine(currentBoss, subordinateCount[id]);
		}
    }
}
