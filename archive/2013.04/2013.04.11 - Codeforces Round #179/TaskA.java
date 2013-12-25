package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryTypeCount = in.readInt();
		int queryCount = in.readInt();
		long[] array = IOUtils.readLongArray(in, count);
		int[] from = new int[queryTypeCount];
		int[] to = new int[queryTypeCount];
		int[] delta = new int[queryTypeCount];
		IOUtils.readIntArrays(in, from, to, delta);
		MiscUtils.decreaseByOne(from, to);
		SumIntervalTree queryTree = new SumIntervalTree(queryTypeCount);
		for (int i = 0; i < queryCount; i++) {
			int currentFrom = in.readInt() - 1;
			int currentTo = in.readInt() - 1;
			queryTree.update(currentFrom, currentTo, 1);
		}
		SumIntervalTree result = new SumIntervalTree(count);
		for (int i = 0; i < queryTypeCount; i++)
			result.update(from[i], to[i], delta[i] * queryTree.query(i, i));
		for (int i = 0; i < count; i++)
			array[i] += result.query(i, i);
		out.printLine(array);
    }
}
