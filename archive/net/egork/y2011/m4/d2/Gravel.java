package net.egork.y2011.m4.d2;

import net.egork.collections.intervaltree.sumintervaltree.SumIntervalTree;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Gravel implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int queryCount = in.readInt();
		int startQuantity = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(size);
		tree.put(0, size, startQuantity);
		for (int it = 0; it < queryCount; it++) {
			char type = in.readCharacter();
			if (type == 'Q') {
				int position = in.readInt() - 1;
				out.println(tree.get(position));
			} else {
				int from = in.readInt() - 1;
				int to = in.readInt();
				int quantity = in.readInt();
				tree.put(from, to, quantity);
			}
		}
	}
}

