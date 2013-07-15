package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int jiroCount = in.readInt();
		int cielCount = in.readInt();
		IntList attack = new IntArrayList();
		IntList defence = new IntArrayList();
		for (int i = 0; i < jiroCount; i++) {
			if ("DEF".equals(in.readString()))
				defence.add(in.readInt());
			else
				attack.add(in.readInt());
		}
		int[] cielStrength = IOUtils.readIntArray(in, cielCount);
		attack.inPlaceSort(IntComparator.REVERSE);
		defence.inPlaceSort();
		ArrayUtils.sort(cielStrength, IntComparator.REVERSE);
		int answer = 0;
		for (int i = 1; i <= Math.min(attack.size(), cielCount); i++) {
			int current = 0;
			for (int j = 0; j < i; j++)
				current += cielStrength[j];
			for (int j = attack.size() - i; j < attack.size(); j++)
				current -= attack.get(j);
			answer = Math.max(answer, current);
		}
		boolean[] killed = new boolean[cielCount];
		ArrayUtils.reverse(cielStrength);
		int j = 0;
		for (int i : defence.toArray()) {
			while (j < cielCount && i >= cielStrength[j])
				j++;
			if (j == cielCount) {
				out.printLine(answer);
				return;
			}
			killed[j++] = true;
		}
		j = 0;
		int all = 0;
		for (int i = cielCount - 1; i >= 0; i--) {
			if (killed[i])
				continue;
			if (j == attack.size())
				all += cielStrength[i];
			else if (cielStrength[i] < attack.get(j)) {
				out.printLine(answer);
				return;
			} else
				all += cielStrength[i] - attack.get(j++);
		}
		answer = Math.max(answer, all);
		out.printLine(answer);
	}
}
