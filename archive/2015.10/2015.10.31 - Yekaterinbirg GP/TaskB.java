package net.egork;

import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = IOUtils.readIntArray(in, n);
		IntSet all = new IntHashSet();
		for (int i : x) {
			for (long l : IntegerUtils.getDivisors(i)) {
				all.add((int)l);
			}
		}
		for (int i = 1; ; i++) {
			if (!all.contains(i)) {
				out.printLine(i);
				return;
			}
		}
	}
}
