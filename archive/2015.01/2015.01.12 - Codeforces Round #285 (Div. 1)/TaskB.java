package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] p = IOUtils.readIntArray(in, count);
		int[] q = IOUtils.readIntArray(in, count);
		SumIntervalTree pTree = new SumIntervalTree(count);
		pTree.update(0, count - 1, 1);
		for (int i = 0; i < count; i++) {
			pTree.update(p[i], p[i], -1);
			p[i] = (int) pTree.query(0, p[i]);
		}
		SumIntervalTree qTree = new SumIntervalTree(count);
		qTree.update(0, count - 1, 1);
		for (int i = 0; i < count; i++) {
			qTree.update(q[i], q[i], -1);
			q[i] = (int) qTree.query(0, q[i]);
		}
		int[] result = new int[count];
		for (int i = count - 1; i >= 0; i--) {
			result[i] += p[i] + q[i];
			if (result[i] >= count - i) {
				result[i] -= count - i;
				if (i > 0) result[i - 1]++;
			}
		}
		TreapSet<Integer> treapSet = new TreapSet<>();
		for (int i = 0; i < count; i++) {
			treapSet.add(i);
		}
		for (int i = 0; i < count; i++) {
			result[i] = treapSet.get(result[i]);
			treapSet.remove(result[i]);
		}
		out.printLine(result);
	}
}
