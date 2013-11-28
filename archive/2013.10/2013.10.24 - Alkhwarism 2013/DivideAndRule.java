package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DivideAndRule {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IntSet xs = new IntHashSet();
		IntSet ys = new IntHashSet();
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int y = in.readInt();
			xs.add(x);
			ys.add(y);
		}
		out.printLine((long)(xs.size() + 1) * (ys.size() + 1));
    }
}
