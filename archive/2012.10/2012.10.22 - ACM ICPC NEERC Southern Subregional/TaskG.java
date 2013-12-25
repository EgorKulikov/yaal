package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() - 1;
		char[] type = new char[count];
		final int[] value = new int[count];
		for (int i = 0; i < count; i++) {
			type[i] = in.readCharacter();
			value[i] = in.readInt();
		}
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (value[o1] != value[o2])
					return value[o2] - value[o1];
				return o1 - o2;
			}
		};
		NavigableSet<Integer> answer = new TreeSet<Integer>(comparator);
		NavigableSet<Integer> dragons = new TreeSet<Integer>(comparator);
		int minBeauty = Integer.MAX_VALUE;
		for (int i = count - 2; i >= 0; i--) {
			if (type[i] == 'p') {
				minBeauty = Math.min(minBeauty, value[i]);
				value[i] = minBeauty;
			}
		}
		for (int i = 0; i < count - 1; i++) {
			if (type[i] == 'd')
				dragons.add(i);
			else {
				while (answer.size() + 1 < value[i] && !dragons.isEmpty()) {
					int dragon = dragons.pollFirst();
					answer.add(dragon);
				}
				while (!dragons.isEmpty() && !answer.isEmpty()) {
					if (comparator.compare(dragons.first(), answer.last()) < 0) {
						answer.pollLast();
						answer.add(dragons.pollFirst());
					} else
						break;
				}
				dragons.clear();
			}
		}
		while (!dragons.isEmpty()) {
			int dragon = dragons.pollFirst();
			answer.add(dragon);
		}
		dragons.clear();
		if (answer.size() < value[count - 1]) {
			out.printLine(-1);
			return;
		}
		Integer[] array = answer.toArray(new Integer[answer.size()]);
		Arrays.sort(array);
		int gold = 0;
		for (int i = 0; i < array.length; i++) {
			gold += value[array[i]];
			array[i] += 2;
		}
		out.printLine(gold);
		out.printLine(answer.size());
		out.printLine(array);
	}
}
