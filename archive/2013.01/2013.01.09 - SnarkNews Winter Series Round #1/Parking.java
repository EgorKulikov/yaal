package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Parking {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int levelCount = in.readInt();
		int[] levels = IOUtils.readIntArray(in, levelCount);
		int queryCount = in.readInt();
		IntList[] present = new IntList[levelCount];
		int total = queryCount;
		int[] lvl = new int[(int) Math.min(queryCount, ArrayUtils.sumArray(levels))];
		int j = 0;
		for (int i = 0; i < levelCount; i++) {
			int delta = Math.min(total, levels[i]);
			present[i] = new IntArrayList(delta);
			total -= delta;
			for (int k = 0; k < delta; k++)
				lvl[j++] = i;
		}
		NavigableSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < lvl.length; i++)
			set.add(i);
		IntList answer = new IntArrayList(queryCount);
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt() - 1;
			if (type == -1) {
				int curLevel = set.pollFirst();
				answer.add(lvl[curLevel] + 1);
				present[lvl[curLevel]].add(curLevel);
			} else {
				set.add(present[type].popBack());
			}
		}
		out.printLine(answer);
    }
}
