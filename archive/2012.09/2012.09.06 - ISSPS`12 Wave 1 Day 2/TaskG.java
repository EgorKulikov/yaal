package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(count);
		tree.init();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (type == 0) {
				int delta = in.readInt();
				tree.update(from, to, delta);
			} else
				out.printLine(tree.query(from, to));
		}
	}
}
