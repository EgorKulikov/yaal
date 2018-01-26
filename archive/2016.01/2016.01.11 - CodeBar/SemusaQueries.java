package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SemusaQueries {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int c = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(n);
		for (int i = 0; i < c; i++) {
			int type = in.readInt();
			int p = in.readInt() - 1;
			int q = in.readInt() - 1;
			if (type == 1) {
				tree.update(p, q, in.readInt());
			} else {
				out.printLine(tree.query(p, q));
			}
		}
	}
}
