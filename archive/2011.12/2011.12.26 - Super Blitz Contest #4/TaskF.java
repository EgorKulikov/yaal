package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		int queryCount = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(length);
		for (int i = 0; i < count; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			tree.putSegment(from, to, 1);
		}
		int[] answer = new int[queryCount];
		for (int i = 0; i < queryCount; i++)
			answer[i] = (int) tree.get(in.readInt() - 1);
		out.printLine(Array.wrap(answer).toArray());
	}
}
